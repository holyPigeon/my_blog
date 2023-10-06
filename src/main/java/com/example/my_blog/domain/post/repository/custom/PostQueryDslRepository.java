package com.example.my_blog.domain.post.repository.custom;

import com.example.my_blog.domain.post.service.dto.request.PostSearchCondition;
import com.example.my_blog.domain.post.service.dto.response.DetailPostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryDslRepository {

  Page<DetailPostResponse> listSearchResult(PostSearchCondition condition, Pageable pageable);

  Page<DetailPostResponse> listSortedSearchResult(PostSearchCondition condition, String sortType, Pageable pageable);
}
