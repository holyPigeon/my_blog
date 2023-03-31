package com.example.my_blog.domain.post.service.dto.request;

import com.example.my_blog.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostRequestDTO {

  private Member member;
  private String title;
  private String content;
}
