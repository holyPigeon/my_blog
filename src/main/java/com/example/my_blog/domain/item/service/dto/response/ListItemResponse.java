package com.example.my_blog.domain.item.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListItemResponse<T> {

  private int count;
  private T data;
}
