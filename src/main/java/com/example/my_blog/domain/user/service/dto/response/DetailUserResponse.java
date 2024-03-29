package com.example.my_blog.domain.user.service.dto.response;

import com.example.my_blog.domain.user.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailUserResponse {
  private Long id;
  private String name;
  private String nickname;

  @QueryProjection
  public DetailUserResponse(User user) {

    this.id = user.getId();
    this.name = user.getName();
    this.nickname = user.getNickname();
  }
}
