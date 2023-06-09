package com.example.my_blog.domain.post.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListPostResponse<T> {

  private int count;
  private T customerList;
}
