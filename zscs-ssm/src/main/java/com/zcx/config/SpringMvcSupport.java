package com.zcx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**").addResourceLocations("/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("/front/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}
