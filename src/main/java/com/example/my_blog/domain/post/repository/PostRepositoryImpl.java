package com.example.my_blog.domain.post.repository;

import com.example.my_blog.domain.post.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final EntityManager em;

  @Override
  public void save(Post post) {

    em.persist(post);
  }

  @Override
  public Post findById(Long id) {

    return em.find(Post.class, id);
  }

  @Override
  public List<Post> findByUserId(Long userId) {

    return em.createQuery("select distinct p from Post p" +
            " join fetch p.user u where u.id = :userId", Post.class)
        .setParameter("userId", userId)
        .getResultList();
  }

  @Override
  public List<Post> findAll() {

    return em.createQuery("SELECT p FROM Post p", Post.class)
        .getResultList();
  }


  @Override
  public void deleteById(Long id) {

    Post findPost = em.find(Post.class, id);

    em.remove(findPost);
  }
}
