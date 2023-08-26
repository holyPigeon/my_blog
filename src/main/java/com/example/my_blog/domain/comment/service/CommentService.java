package com.example.my_blog.domain.comment.service;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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

  @Transactional
  public Long saveReplyComment(Long parentId, Comment comment) {

    // 부모 댓글과 자식 댓글(대댓글)간 연관관계 정의
    Comment parentComment = commentRepository.findById(parentId).orElseThrow(
        () -> new NoSuchElementException("해당 부모 댓글이 존재하지 않습니다.")
    );

    parentComment.getChildren().add(comment);
    comment.setParent(parentComment);

    commentRepository.save(comment);

    return comment.getId();
  }

  public Comment findById(Long id) {

    return commentRepository.findById(id).orElseThrow(
        () -> new NoSuchElementException("해당 댓글이 존재하지 않습니다.")
    );
  }

  public List<Comment> findAll() {

    return commentRepository.findAll();
  }

  // 해당 게시글의 모든 부모 댓글을 조회한다.
  public List<Comment> findAllParentCommentByPost(Long postId) {

    return commentRepository.findAllParentCommentByPost(postId);
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
