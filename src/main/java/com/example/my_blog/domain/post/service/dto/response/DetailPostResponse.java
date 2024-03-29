package com.example.my_blog.domain.post.service.dto.response;

import com.example.my_blog.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
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
  private int viewCount;
  private int likeCount;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime createdDate;

  @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime lastModifiedDate;

  @QueryProjection
  public DetailPostResponse (Post post) {

    this.id = post.getId();
    this.author = post.getUser().getNickname();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.viewCount = post.getViewCount();
    this.likeCount = post.getLikes().size();
    this.createdDate = post.getCreatedDate();
    this.lastModifiedDate = post.getLastModifiedDate();
  }
}
