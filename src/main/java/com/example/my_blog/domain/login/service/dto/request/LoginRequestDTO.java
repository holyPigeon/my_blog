package com.example.my_blog.domain.login.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDTO {

  private String loginId;
  private String password;
}
