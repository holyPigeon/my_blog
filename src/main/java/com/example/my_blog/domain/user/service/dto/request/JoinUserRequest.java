package com.example.my_blog.domain.user.service.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinUserRequest {

  private String loginId;
  private String password;
  private String name;

}
