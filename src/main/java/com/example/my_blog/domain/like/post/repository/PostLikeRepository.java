package com.example.my_blog.domain.like.post.repository;

import com.example.my_blog.domain.like.post.PostLike;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository {

  void save(PostLike postLike);

  Optional<PostLike> getPostLike(Long postId, Long userId);

  List<PostLike> getPostLikeListByPostId(Long postId);

  Long getPostLikeCountByPostId(Long postId);

  void delete(Long postId, Long userId);
}
