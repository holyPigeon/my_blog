package com.example.my_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  public void addInterceptors(InterceptorRegistry registry) {
    // 인터셉터 추가 로직 구현
  }
}