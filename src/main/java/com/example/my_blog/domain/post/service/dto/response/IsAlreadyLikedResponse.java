package com.example.my_blog.domain.post.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IsAlreadyLikedResponse {

  private boolean isAlreadyLiked;
}
