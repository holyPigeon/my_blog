package com.example.my_blog.domain.post.repository;

import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.repository.custom.PostQueryDslRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends JpaRepository<Post, Long>, PostQueryDslRepository {

  Page<Post> findAll(Pageable pageable);

  @EntityGraph(attributePaths = {"user"})
  Page<Post> findByUserId(Long memberId, Pageable pageable);

  @Modifying
  @Query("update Post p set p.viewCount = p.viewCount + 1 where p.id = :postId")
  void incrementViewCount(@Param("postId") Long postId);

  void deleteById(Long id);
}
