package com.ping.simpledirectoryapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public ThrottleInterceptor throttleInterceptor;

    public WebConfig(ThrottleInterceptor throttleInterceptor) {
        this.throttleInterceptor = throttleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(throttleInterceptor).addPathPatterns("/environments/**");
    }
}
