//package com.example.my_blog.domain.like.post.repository;
//
//import com.example.my_blog.domain.like.post.PostLike;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.NoResultException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Repository
//@RequiredArgsConstructor
//public class PostLikeRepositoryImpl implements PostLikeRepository {
//
//  private final EntityManager em;
//
//  @Override
//  public void save(PostLike postLike) {
//
//    em.persist(postLike);
//  }
//
//  @Override
//  public Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId) {
//
//    try {
//      return Optional.ofNullable(
//          em.createQuery("select distinct pl from PostLike pl" +
//                  " join fetch pl.post p" +
//                  " join fetch pl.user u where p.id = :postId and u.id = :userId", PostLike.class)
//              .setParameter("postId", postId)
//              .setParameter("userId", userId)
//              .getSingleResult()
//      );
//    } catch (NoResultException ex) {
//      return Optional.empty();
//    }
//  }
//
//
//  @Override
//  public List<PostLike> findByPostId(Long postId) {
//
//    return em.createQuery("select distinct pl from PostLike pl" +
//            " join fetch pl.post p where p.id = :postId", PostLike.class)
//        .setParameter("postId", postId)
//        .getResultList();
//  }
//
//  @Override
//  public Long findCountByPostId(Long postId) {
//
//    return em.createQuery("select count(pl) from Post p" +
//            " join p.likes pl where p.id = :postId", Long.class)
//        .setParameter("postId", postId)
//        .getSingleResult();
//  }
//
//  @Override
//  public void deleteByPostIdAndUserId(Long postId, Long userId) {
//
//    PostLike postLike = findByPostIdAndUserId(postId, userId).orElseThrow(
//        () -> new NoSuchElementException("해당 좋아요가 존재하지 않습니다.")
//    );
//
//    em.remove(postLike);
//  }
//}
