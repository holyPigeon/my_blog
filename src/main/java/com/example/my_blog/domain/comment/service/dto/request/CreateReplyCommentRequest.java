package com.example.my_blog.domain.comment.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReplyCommentRequest {

  private Long userId;

  private Long parentId;

  private String content;
}
