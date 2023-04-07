package com.example.my_blog.domain.member.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListMemberResponseDTO {
  private Long id;
  private String name;
  private int age;
}