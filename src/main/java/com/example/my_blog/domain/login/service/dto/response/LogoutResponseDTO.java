package com.example.my_blog.domain.login.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogoutResponseDTO {

  private boolean success;
  private String message;
}
