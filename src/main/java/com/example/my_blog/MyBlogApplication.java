package com.example.my_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class MyBlogApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyBlogApplication.class, args);
  }

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> {
      ServletRequestAttributes attr
          = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

      String currentUser = attr.getRequest().getSession().getAttribute(SessionConst.SESSION_KEY).toString();

      if(currentUser != null)
        return Optional.of(currentUser);
      else
        return Optional.of("Anonymous");
    };
  }

}
