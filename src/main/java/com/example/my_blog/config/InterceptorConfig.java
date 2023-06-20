package com.example.my_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RequestInterceptor())
        .order(1)
        .addPathPatterns("/api/**")
        .excludePathPatterns(
            "/login", // LoginController
            "/session", // UserController
            "/error"
        );
  }
}