package com.example.my_blog.domain.post.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListPostResponse<T> {

  private int count;
  private T data;
}
