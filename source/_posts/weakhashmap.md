---
title: WeakHashMap-弱引用HashMap
date: 2021-05-13
typora-root-url: ../../source
coauthor: zshuo
tags: 
- java 
- HashMap
categories:
- java
banner_img: /images/general/2.jpg
index_img: /images/general/2.jpg
---

WeakHashMap，此种Map的特点是，当除了自身有对key的引用外，此key没有其他引用那么此map会自动丢弃此值，所以比较适合做缓存。

```java
public class WeakHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>
```

WeakHashMap实现了Map接口，基于hash-table实现，在这种Map中，key的类型是WeakReference。如果对应的key被回收，则这个key指向的对象会被从Map容器中移除。

## 典型使用场景： tomcat两级缓存

tomcat的源码里，实现缓存时会用到WeakHashMap

```java
package org.apache.tomcat.util.collections;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class ConcurrentCache<K,V> {

    private final int size;

    private final Map<K,V> eden;

    private final Map<K,V> longterm;

    public ConcurrentCache(int size) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longterm = new WeakHashMap<>(size);
    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            synchronized (longterm) {
                v = this.longterm.get(k);
            }
            if (v != null) {
                this.eden.put(k, v);
            }
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            synchronized (longterm) {
                this.longterm.putAll(this.eden);
            }
            this.eden.clear();
        }
        this.eden.put(k, v);
    }
}
```

源码中有eden和longterm的两个map，对jvm堆区有所了解的话，可以猜测出tomcat在这里是使用ConcurrentHashMap和WeakHashMap做了分代的缓存。

- 在put方法里，在插入一个k-v时，先检查eden缓存的容量是不是超了。没有超就直接放入eden缓存，如果超了则锁定longterm将eden中所有的k-v都放入longterm。再将eden清空并插入k-v。
- 在get方法中，也是优先从eden中找对应的v，如果没有则进入longterm缓存中查找，找到后就加入eden缓存并返回。

经过这样的设计，相对常用的对象都能在eden缓存中找到，不常用（有可能被销毁的对象）的则进入longterm缓存。而longterm的key的实际对象没有其他引用指向它时，gc就会自动回收heap中该弱引用指向的实际对象，弱引用进入引用队列。longterm调用expungeStaleEntries()方法，遍历引用队列中的弱引用，并清除对应的Entry，不会造成内存空间的浪费。

```java
/**
 * Expunges stale entries from the table.
 * 遍历引用队列中的弱引用，并清除对应的Entry
 */
private void expungeStaleEntries() {
    for (Object x; (x = queue.poll()) != null; ) {
        synchronized (queue) {
            @SuppressWarnings("unchecked")
                Entry<K,V> e = (Entry<K,V>) x;
            int i = indexFor(e.hash, table.length);

            Entry<K,V> prev = table[i];
            Entry<K,V> p = prev;
            while (p != null) {
                Entry<K,V> next = p.next;
                if (p == e) {
                    if (prev == e)
                        table[i] = next;
                    else
                        prev.next = next;
                    // Must not null out e.next;
                    // stale entries may be in use by a HashIterator
                    e.value = null; // Help GC
                    size--;
                    break;
                }
                prev = p;
                p = next;
            }
        }
    }
}
```

### 强引用（StrongReference）

被强引用指向的对象，绝对不会被垃圾收集器回收。Integer prime = 1;，这个语句中prime对象就有一个强引用。

### 软引用（SoftReference）

被SoftReference指向的对象可能会被垃圾收集器回收，但是只有在JVM内存不够的情况下才会回收；如下代码可以创建一个软引用：

```java
Integer prime = 1;
SoftReference<Integer> soft = new SoftReference<Integer>(prime);
prime = null;
```

### 弱引用（WeakReference）

当一个对象仅仅被WeakReference引用时，在下个垃圾收集周期时候该对象就会被回收。我们通过下面代码创建一个WeakReference：

```java
Integer prime = 1;
WeakReference<Integer> soft = new WeakReference<Integer>(prime);
prime = null;
```

当把prime赋值为null的时候，原prime对象会在下一个垃圾收集周期中被回收，因为已经没有强引用指向它。