---
title: try-with-resource
date: 2021-05-19
typora-root-url: ../../source
coauthor: zshuo
tags: 
- java 
categories:
- java 
banner_img: /images/general/2.jpg
index_img: /images/general/2.jpg

---

# 背景

Java中包含许多需要调用close才能关闭的资源，如`InputStream`、`OutputStream`、`java.sql.Connection`

忘记关闭会造成很严重的性能后果，关闭的方法有`finalizer`、`try-catch-finally`、`try-with-resources`等

# finalizer

- finalizer（终结方法）通常是不可预测的，很危险的（详见Effective java 第8条）
- 终结方法不能保证会被及时的执行，从一个对象变得不可到达开始，到终结方法被执行，花费时间是任意长的。
- JAVA语言规范也不保证终结方法会被执行
- 终结方法有很严重性能损失

# try-catch-finally

`try-catch-finally` 是确保资源被关闭的最佳方法，就算发生异常finally也一样会执行

但是，当关闭多个资源时会出现问题，代码不够优雅，`嵌套越来越深`，并且假如代码抛出异常，finally也抛出异常，会导致第二个异常会覆盖第一个异常，`导致异常屏蔽问题`

```java
public static void main(String[] args) throws IOException {
        InputStream in= new FileInputStream("aaa");
        try {
            OutputStream out = new FileOutputStream("bbb");
            try {
                byte[] buf = new byte[1024];
                int n;
                while ((n = in.read(buf))>=0){
                    out.write(buf,0,n);
                }
            }finally {
                out.close();
            }
        }finally {
            in.close();
        }
    }
```

## try-catch-finally的执行顺序

- 代码没有异常 执行顺序：try执行完整->catch不执行->finally执行
- 代码有异常且catch进行捕获 执行顺序：try执行部分->跳转catch捕获处理->finally执行
- 代码有异常且catch不捕获：这种情况没有catch 执行顺序：try执行部分->finally执行

```
当finally有return时，会直接返回,不会再去返回try或者catch中的返回值，而finally没有return时，try和catch 的return语句并不会马上执行，而是执行完finally代码块之后再返回try和catch里面的值。
```

# try-with-resources

`try-with-resources` 是`jdk1.7`引入的语法糖，使得关闭资源操作无需层层嵌套在finally。

要使用这个功能，必须要实现`AutoCloseable`接口。

```java
public static void main(String[] args) throws IOException {
        try (InputStream in = new FileInputStream("aaa");
             OutputStream out = new FileOutputStream("bbb")) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }
```

## 原理

编译器编译时会`自动帮我们补全close()`，而且可以`避免异常屏蔽`

从`Java 1.7`开始，为 Throwable 类新增了 `addSuppressed` 方法，支持将一个异常附加到另一个异常身上，从而避免异常屏蔽。

反编译代码

```java
public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("aaa");
        Throwable var2 = null;

        try {
            OutputStream out = new FileOutputStream("bbb");
            Throwable var4 = null;

            try {
                byte[] buf = new byte[1024];

                int n;
                while((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } catch (Throwable var28) {
                var4 = var28;
                throw var28;
            } finally {
                if (out != null) {
                    if (var4 != null) {
                        try {
                            out.close();
                        } catch (Throwable var27) {
                            var4.addSuppressed(var27);
                        }
                    } else {
                        out.close();
                    }
                }

            }
        } catch (Throwable var30) {
            var2 = var30;
            throw var30;
        } finally {
            if (in != null) {
                if (var2 != null) {
                    try {
                        in.close();
                    } catch (Throwable var26) {
                        var2.addSuppressed(var26);
                    }
                } else {
                    in.close();
                }
            }

        }

    }
```

# 结论

处理必须关闭的资源时，始终要`优先考虑使用try-with-resources`，而不是try-finally。这样得到的代码将更简洁，清晰，产生的异常也更有价值，这些也是try-finally无法做到的。