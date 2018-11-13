package com.zs.blog.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 程序启动后通过ApplicationRunner处理一些事务
 */
@Slf4j
@Component
public class BlogApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("项目部署完成，当前时间：" + LocalDateTime.now().toString());
    }
}
