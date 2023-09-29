package com.example.my_blog.domain.user.repository.custom;

import com.example.my_blog.domain.user.service.dto.request.UserSearchCondition;
import com.example.my_blog.domain.user.service.dto.response.DetailUserResponse;

import java.util.List;

public interface UserQueryDslRepository {

  List<DetailUserResponse> search(UserSearchCondition condition);
}
