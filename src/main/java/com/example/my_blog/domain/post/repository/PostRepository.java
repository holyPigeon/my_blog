package com.example.my_blog.domain.post.repository;

import com.example.my_blog.domain.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

  void save(Post post);

  Optional<Post> findById(Long id);

  List<Post> findByUserId(Long memberId);

  List<Post> findAll();

  void deleteById(Long id);
}
