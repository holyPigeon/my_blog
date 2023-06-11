package com.example.my_blog.domain.comment.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListCommentResponse<T> {

  private int count;
  private T commentList;
}
