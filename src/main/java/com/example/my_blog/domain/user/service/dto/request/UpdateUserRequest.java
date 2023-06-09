package com.example.my_blog.domain.user.service.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequest {

  private String name;
  private int age;

}
