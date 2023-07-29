package com.example.my_blog.domain.like.post.repository;

import com.example.my_blog.domain.like.post.PostLike;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostLikeRepositoryImpl implements PostLikeRepository {

  private final EntityManager em;

  @Override
  public void save(PostLike postLike) {

    em.persist(postLike);
  }

  @Override
  public PostLike getPostLike(Long postId, Long userId) {

    try { // 만약 해당 게시물에 해당 유저가 좋아요를 했다면 PostLike 객체 반환
      return em.createQuery("select distinct pl from PostLike pl" +
              " join fetch pl.post p" +
              " join fetch pl.user u where p.id = :postId and u.id = :userId", PostLike.class)
          .setParameter("postId", postId)
          .setParameter("userId", userId)
          .getSingleResult();
    } catch (NoResultException e) { // 만약 해당 게시물에 해당 유저가 좋아요를 하지 않았다면 null 반환
      return null;
    }

  }


  @Override
  public List<PostLike> getPostLikeListByPostId(Long postId) {

    return em.createQuery("select distinct pl from PostLike pl" +
            " join fetch pl.post p where p.id = :postId", PostLike.class)
        .setParameter("postId", postId)
        .getResultList();
  }

  @Override
  public Long getPostLikeCountByPostId(Long postId) {

    return em.createQuery("select count(pl) from Post p" +
            " join p.likes pl where p.id = :postId", Long.class)
        .setParameter("postId", postId)
        .getSingleResult();
  }

  @Override
  public void delete(Long postId, Long userId) {

    PostLike postLike = getPostLike(postId, userId);
    em.remove(postLike);
  }
}
