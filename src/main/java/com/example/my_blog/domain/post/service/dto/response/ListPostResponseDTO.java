package com.example.my_blog.domain.post.service.dto.response;

import com.example.my_blog.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListPostResponseDTO {

  private Long id;
  private Member member;
  private String title;
  private String content;
}
