package com.example.my_blog.domain.comment.repository;

import com.example.my_blog.domain.comment.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  private final EntityManager em;

  @Override
  public void save(Comment comment) {

    em.persist(comment);
  }

  @Override
  public Comment findById(Long id) {

    return em.find(Comment.class, id);
  }

  @Override
  public List<Comment> findByAuthor(String author) {

    return em.createQuery("select distinct c from Comment c" +
            " join fetch c.user u where u.name = :author", Comment.class)
        .setParameter("author", author)
        .getResultList();
  }

  @Override
  public List<Comment> findAll() {

    return em.createQuery("select c from Comment c", Comment.class)
        .getResultList();
  }

  @Override
  public void deleteComment(Long id) {

    Comment comment = findById(id);
    em.remove(comment);
  }
}
