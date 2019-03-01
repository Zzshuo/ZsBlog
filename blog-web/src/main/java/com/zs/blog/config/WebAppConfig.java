package com.zs.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author zshuo
 * @date 2019/3/1
 **/
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /** 静态资源处理 **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //重新指定spring boot的静态资源处理前缀
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
