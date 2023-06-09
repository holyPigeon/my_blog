package com.example.my_blog.domain.comment.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListCommentDetailResponse {

  private Long id;

  private Long postId;

  private String author;

  private String content;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
