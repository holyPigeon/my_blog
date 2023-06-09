package com.example.my_blog.domain.comment.service;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;

  @Transactional
  public Long save(Comment comment) {

    commentRepository.save(comment);

    return comment.getId();
  }

  public Comment findById(Long id) {

    return commentRepository.findById(id);
  }

  public List<Comment> findAll() {

    return commentRepository.findAll();
  }

  @Transactional
  public void updateComment(Long id, String content) {

    Comment comment = findById(id);

    if (comment!= null) {
      comment.setContent(content);
      comment.setUpdatedAt(LocalDateTime.now());
    }

  }

  @Transactional
  public void delete(Long id) {

    commentRepository.deleteComment(id);
  }
}
