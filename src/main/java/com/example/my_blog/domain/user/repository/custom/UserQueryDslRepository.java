package com.example.my_blog.domain.user.repository.custom;

import com.example.my_blog.domain.user.service.dto.request.UserSearchCondition;
import com.example.my_blog.domain.user.service.dto.response.DetailUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryDslRepository {

  Page<DetailUserResponse> listSearchResult(UserSearchCondition condition, Pageable pageable);

  Page<DetailUserResponse> listSortedSearchResult(UserSearchCondition condition, String sortType, Pageable pageable);
}
