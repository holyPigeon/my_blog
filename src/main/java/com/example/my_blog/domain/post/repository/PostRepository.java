package com.example.my_blog.domain.post.repository;

import com.example.my_blog.domain.post.Post;

import java.util.List;

public interface PostRepository {

  void save(Post post);

  Post findById(Long id);

  List<Post> findByUserId(Long memberId);

  List<Post> findAll();

  void deleteById(Long id);
}
