package com.example.my_blog.domain.user.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailUserResponse {
  private Long id;
  private String name;
  private int age;
}
