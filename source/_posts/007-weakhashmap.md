---
title: WeakHashMap-弱引用 HashMap
typora-root-url: ../../source
date: 2021-05-27
tags:
  - java
  - HashMap
categories:
  - java
banner_img: /images/general/7.jpg
index_img: /images/general/7.jpg
---

WeakHashMap，此种 Map 的特点是，当除了自身有对 key 的引用外，此 key 没有其他引用那么此 map 会自动丢弃此值，所以比较适合做缓存。

```java
public class WeakHashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>
```

WeakHashMap 实现了 Map 接口，基于 hash-table 实现，在这种 Map 中，key 的类型是 WeakReference。如果对应的 key 被回收，则这个 key 指向的对象会被从 Map 容器中移除。

## 典型使用场景： tomcat 两级缓存

tomcat 的源码里，实现缓存时会用到 WeakHashMap

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

源码中有 eden 和 longterm 的两个 map，对 jvm 堆区有所了解的话，可以猜测出 tomcat 在这里是使用 ConcurrentHashMap 和 WeakHashMap 做了分代的缓存。

- 在 put 方法里，在插入一个 k-v 时，先检查 eden 缓存的容量是不是超了。没有超就直接放入 eden 缓存，如果超了则锁定 longterm 将 eden 中所有的 k-v 都放入 longterm。再将 eden 清空并插入 k-v。
- 在 get 方法中，也是优先从 eden 中找对应的 v，如果没有则进入 longterm 缓存中查找，找到后就加入 eden 缓存并返回。

经过这样的设计，相对常用的对象都能在 eden 缓存中找到，不常用（有可能被销毁的对象）的则进入 longterm 缓存。而 longterm 的 key 的实际对象没有其他引用指向它时，gc 就会自动回收 heap 中该弱引用指向的实际对象，弱引用进入引用队列。longterm 调用 expungeStaleEntries()方法，遍历引用队列中的弱引用，并清除对应的 Entry，不会造成内存空间的浪费。

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

被强引用指向的对象，绝对不会被垃圾收集器回收。Integer prime = 1;，这个语句中 prime 对象就有一个强引用。

### 软引用（SoftReference）

被 SoftReference 指向的对象可能会被垃圾收集器回收，但是只有在 JVM 内存不够的情况下才会回收；如下代码可以创建一个软引用：

```java
Integer prime = 1;
SoftReference<Integer> soft = new SoftReference<Integer>(prime);
prime = null;
```

### 弱引用（WeakReference）

当一个对象仅仅被 WeakReference 引用时，在下个垃圾收集周期时候该对象就会被回收。我们通过下面代码创建一个 WeakReference：

```java
Integer prime = 1;
WeakReference<Integer> soft = new WeakReference<Integer>(prime);
prime = null;
```

当把 prime 赋值为 null 的时候，原 prime 对象会在下一个垃圾收集周期中被回收，因为已经没有强引用指向它。
