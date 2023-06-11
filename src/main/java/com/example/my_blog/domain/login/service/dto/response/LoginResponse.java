package com.example.my_blog.domain.login.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

  private Long id;
  private String loginId;
  private String password;
  private String name;
  private String nickname;
}
