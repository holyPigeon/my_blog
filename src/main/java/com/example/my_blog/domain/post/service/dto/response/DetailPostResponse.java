package com.example.my_blog.domain.post.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailPostResponse {

  private Long id;
  private String author;
  private String title;
  private String content;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime createdAt;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime updatedAt;
}
