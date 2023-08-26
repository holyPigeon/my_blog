package com.example.my_blog.domain.comment.repository;

import com.example.my_blog.domain.comment.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

  void save(Comment comment);

  Optional<Comment> findById(Long id);

  List<Comment> findByAuthor(String name);

  List<Comment> findAll();

  List<Comment> findAllParentCommentByPost(Long postId);

  void deleteComment(Long id);
}
