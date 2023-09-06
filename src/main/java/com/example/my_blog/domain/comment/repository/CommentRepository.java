package com.example.my_blog.domain.comment.repository;

import com.example.my_blog.domain.comment.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @EntityGraph(attributePaths = {"user"})
  List<Comment> findByUser_Name(String name);

  @Query("select distinct c from Comment c" +
      " join fetch c.post p where c.parent is null and p.id = :postId")
  @EntityGraph(attributePaths = {"post"})
  List<Comment> findAllParentCommentByPost(@Param("postId") Long postId);

  void deleteById(Long id);
}
