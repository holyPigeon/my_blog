package com.example.my_blog.domain.user.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPagingCondition {

  private int page;

  private int size;

  private String sort;
}
