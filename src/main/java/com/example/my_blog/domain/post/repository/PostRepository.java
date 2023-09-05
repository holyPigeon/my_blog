package com.example.my_blog.domain.post.repository;

import com.example.my_blog.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findByUserId(Long memberId);

  void deleteById(Long id);
}
