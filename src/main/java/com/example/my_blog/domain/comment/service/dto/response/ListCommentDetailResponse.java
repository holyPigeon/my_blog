package com.example.my_blog.domain.comment.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListCommentDetailResponse {

  private Long id;

  private Long postId;

  private String author;

  private String content;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime createdDate;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime lastModifiedDate;

  private List<ListReplyCommentDetailResponse> children;
}
