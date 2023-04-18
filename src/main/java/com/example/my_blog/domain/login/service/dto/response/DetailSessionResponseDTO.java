package com.example.my_blog.domain.login.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailSessionResponseDTO {

  private Long id;
  private String loginId;
  private String name;
  private int age;
}
