package com.example.my_blog.domain.post.repository;

import com.example.my_blog.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findAll(Pageable pageable);

  @EntityGraph(attributePaths = {"user"})
  Page<Post> findByUserId(Long memberId, Pageable pageable);

  void deleteById(Long id);
}
