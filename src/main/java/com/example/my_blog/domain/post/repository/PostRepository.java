package com.example.my_blog.domain.post.repository;

import com.example.my_blog.domain.post.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

  @EntityGraph(attributePaths = {"user"})
  Page<Post> findByUserId(Long memberId, Pageable pageable);

  void deleteById(Long id);
}
