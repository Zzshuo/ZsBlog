package com.zs.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zshuo
 * @date 2018/10/11
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.zs.blog.controller.*"})
public class BlogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogWebApplication.class, args);
    }
}
