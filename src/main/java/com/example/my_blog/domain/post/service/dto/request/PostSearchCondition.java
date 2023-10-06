package com.example.my_blog.domain.post.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchCondition {

  private String title;

  private String content;

  private String author;
}
