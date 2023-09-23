package com.example.my_blog.domain.comment.service.dto.response;

import com.example.my_blog.domain.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyCommentDetailResponse {

  private Long id;

  private Long postId;

  private String author;

  private String content;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime createdDate;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime lastModifiedDate;

  public ReplyCommentDetailResponse (Comment comment) {

    this.id = comment.getId();
    this.postId = comment.getPost().getId();
    this.author = comment.getUser().getNickname();
    this.content = comment.getContent();
    this.createdDate = comment.getCreatedDate();
    this.lastModifiedDate = comment.getLastModifiedDate();
  }
}
