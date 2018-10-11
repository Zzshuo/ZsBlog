package com.zs.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Component
@MapperScan("com.zs.dao.mapper")
public class MybatisConfig {
}
