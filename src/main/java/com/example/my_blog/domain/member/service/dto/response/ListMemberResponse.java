package com.example.my_blog.domain.member.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListMemberResponse<T> {

  private int count;
  private T data;
}
