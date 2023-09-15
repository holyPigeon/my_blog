package com.example.my_blog.domain.like.post.repository;

import com.example.my_blog.domain.like.post.PostLike;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

  @EntityGraph(attributePaths = {"post", "user"})
  Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);

  @EntityGraph(attributePaths = {"post"})
  List<PostLike> findByPostId(Long postId);

  @EntityGraph(attributePaths = {"post"})
  Long countPostLikesByPostId(Long postId);

  void deleteByPostIdAndUserId(Long postId, Long userId);
}
