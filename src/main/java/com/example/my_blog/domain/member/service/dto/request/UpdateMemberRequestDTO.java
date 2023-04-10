package com.example.my_blog.domain.member.service.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberRequestDTO {

  private String name;
  private int age;

}
