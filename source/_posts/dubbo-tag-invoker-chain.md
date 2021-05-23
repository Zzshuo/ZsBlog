---
title: dubbo自定义标签路由
date: 2021-05-21
typora-root-url: ../../source
coauthor: zshuo
tags: 
- dubbo 
- router
categories:
- dubbo 
banner_img: /images/general/1.jpg
index_img: /images/general/1.jpg
---

# **简介**

标签路由通过将某一个或多个服务的提供者划分到同一个分组，约束流量只在指定分组中流转，从而实现流量隔离的目的，可以作为蓝绿发布、灰度发布等场景的能力基础。

官网地址：[路由规则](https://dubbo.apache.org/zh/docs/v2.7/user/examples/routing-rule/#标签路由规则)

由于dubbo官方路由规则无法满足业务需要，因此自定义标签路由实现

# 自定义路由

## 实现Router接口，重写route方法

```java
public class xxxTagRouter extends AbstractRouter {
    private static final int TAG_ROUTER_DEFAULT_PRIORITY = 150;
    private static final Logger logger = LoggerFactory.getLogger(MsfTagRouter.class);

    public xxxTagRouter(URL url) {
        this.url = url;
        this.priority = TAG_ROUTER_DEFAULT_PRIORITY;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        if (CollectionUtils.isEmpty(invokers)) {
            return invokers;
        }
        return filterTag(invokers, url, invocation);
    }
}

private <T> List<Invoker<T>> filterTag(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        List<Invoker<T>> result;

        // Tag request
        if (StringUtils.isNotEmpty(tag)) {
            // 自定义标签路由逻辑
            ...

            if (result == null) {
                logger.error("consumer tag:" + tag + "not match same tag or not match empty tag provider");
            }
        } else {
            result = filterInvoker(invokers, invoker -> StringUtils.isEmpty(invoker.getUrl().getParameter(TAG_KEY)));
        }
        return result;
    }
```

## 实现RouterFactory getRouter方法

XxxRouterFactory.java：

```java
package com.xxx;
 
import org.apache.dubbo.rpc.cluster.RouterFactory;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.RpcException;
 
public class XxxRouterFactory implements RouterFactory {
    public Router getRouter(URL url) {
        // ...
				return new xxxTagRouter(url)
    }
}
```

## 基于 SPI 机制,加载RouterFactory

在 `META-INF/dubbo/org.apache.dubbo.rpc.cluster.RouterFactory`添加

```java
xxx=com.xxx.XxxRouterFactory
```

# 其他

## Dubbo Provider 注册url添加参数

由于业务需要，provider静态规则打标需要给把标签添加到url上，实现方法如下

实现`RegistryFactory` 使用包装类`RegistryWrapper` 中的 `appendProviderTag` 方法中给url添加parameter

```java
public class xxxRegistryWrapper implements RegistryFactory {

    private final RegistryFactory registryFactory;

    public xxxRegistryWrapper(RegistryFactory registryFactory) {
        this.registryFactory = registryFactory;
    }

    @Override
    public Registry getRegistry(URL url) {
        return new RegistryWrapper(registryFactory.getRegistry(url));
    }

    static class RegistryWrapper implements Registry {
        private final Registry originRegistry;

        private URL appendProviderTag(URL url) {
            String side = url.getParameter(Constants.SIDE_KEY);
            if (Constants.PROVIDER_SIDE.equals(side)) {
                url = url.addParameter("TAG_KEY", xxx);
            }
            return url;
        }

        public RegistryWrapper(Registry originRegistry) {
            this.originRegistry = originRegistry;
        }

        @Override
        public URL getUrl() {
            return originRegistry.getUrl();
        }

        @Override
        public boolean isAvailable() {
            return originRegistry.isAvailable();
        }

        @Override
        public void destroy() {
            originRegistry.destroy();
        }

        @Override
        public void register(URL url) {
            originRegistry.register(appendProviderTag(url));
        }

        @Override
        public void unregister(URL url) {
            originRegistry.unregister(appendProviderTag(url));
        }

        @Override
        public void subscribe(URL url, NotifyListener listener) {
            originRegistry.subscribe(url, listener);
        }

        @Override
        public void unsubscribe(URL url, NotifyListener listener) {
            originRegistry.unsubscribe(url, listener);
        }

        @Override
        public List<URL> lookup(URL url) {
            return originRegistry.lookup(appendProviderTag(url));
        }
    }
}
```

## Dirctory，Router，LoadBalance和Filter调用顺序

`Dirctory`→Router→LoadBalance→Filter

```java
组件              Directory              Router             LoadBalance      Filter
    产物     Invocation ----->  List<Invoker> -----> List<Invoker> -----> Invoker -----> Result
组件中的方法             list                 route                select         invoke
```

## 解决Consumer标签当前线程多次调用dubbo失效问题

由于Dubbo的ContextFilter特性会在调用结束时移除Consumer的上下文，tag也因此移除，下次调用时没有标签因此需要在移除之前通过把tag保存下来。官方建议 也是通过filter设置
https://github.com/apache/dubbo/issues/5638 

`We do that on purpose, you need to do some extra work to make the tag passed along the invoke chain. For example, you add a filter working before ContextFilter and save tag before it get removed.`

```java
/**
 * ContextFilter set the provider RpcContext with invoker, invocation, local port it is using and host for
 * current execution thread.
 *
 * @see RpcContext
 */
@Activate(group = PROVIDER, order = -10000)
public class ContextFilter extends ListenableFilter { 
	...
	try {
	    return invoker.invoke(invocation);
	} finally {
	    // IMPORTANT! For async scenario, we must remove context from current thread, so we always create a new RpcContext for the next invoke for the same thread.
	    RpcContext.removeContext();
	    RpcContext.removeServerContext();
	}
	...
}
```

![dubbo-tag](/images/dubbo-tag-invoker-chain/dubbo-tag-1662529.jpg)

