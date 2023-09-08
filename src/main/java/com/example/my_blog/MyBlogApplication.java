package com.example.my_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class MyBlogApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyBlogApplication.class, args);
  }

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> Optional.of(UUID.randomUUID().toString());
  }

}
