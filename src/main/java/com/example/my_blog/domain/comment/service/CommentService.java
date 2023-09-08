package com.example.my_blog.domain.comment.service;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.comment.repository.CommentRepository;
import com.example.my_blog.exception.MyBlogException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.my_blog.exception.MyBlogErrorCode.COMMENT_NOT_FOUND;
import static com.example.my_blog.exception.MyBlogErrorCode.PARENT_COMMENT_NOT_FOUND;

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

    Comment parentComment = commentRepository.findById(parentId)
        .orElseThrow(() -> MyBlogException.type(PARENT_COMMENT_NOT_FOUND));

    // 부모 댓글과 자식 댓글(대댓글)간 연관관계 정의
    parentComment.getChildren().add(comment);
    comment.setParent(parentComment);

    commentRepository.save(comment);

    return comment.getId();
  }

  public Comment findById(Long id) {

    return commentRepository.findById(id)
        .orElseThrow(() -> MyBlogException.type(COMMENT_NOT_FOUND));
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

    comment.setContent(content);
  }

  @Transactional
  public void delete(Long id) {

    commentRepository.deleteById(id);
  }
}
