package com.example.my_blog.domain.post.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IsAlreadyLikedRequest {

  private Long postId;

  private Long userId;
}
