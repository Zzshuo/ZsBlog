package com.zs.web.config;

import org.springframework.stereotype.Component;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zshuo
 * @date 2018/10/11
 **/
@Component
@MapperScan("com.zs.dao.mapper")
public class MybatisConfig {
}
