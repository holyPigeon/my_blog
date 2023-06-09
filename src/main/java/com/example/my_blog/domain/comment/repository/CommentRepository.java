package com.example.my_blog.domain.comment.repository;

import com.example.my_blog.domain.comment.Comment;

import java.util.List;

public interface CommentRepository {

  void save(Comment comment);

  Comment findById(Long id);

  List<Comment> findByAuthor(String name);

  List<Comment> findAll();

  void deleteComment(Long id);
}
