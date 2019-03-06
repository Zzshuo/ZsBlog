package com.zs.blog;

import com.zs.blog.util.redis.RedisUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1() {
        try {
            redisUtil.set("redisTemplate", "这是一条测试数据");
            String value = redisUtil.get("redisTemplate").toString();
            log.info("redisValue=" + value);
            log.info("读取redis成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        try {
            User user = new User();
            user.setId(123);
            user.setName("隔壁老王");
            redisUtil.set("user", user);
            User res = (User) redisUtil.get("user");
            log.info("res=" + res.toString());
            log.info("读取redis成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    private static class User {
        private Integer id;
        private String name;
    }
}
