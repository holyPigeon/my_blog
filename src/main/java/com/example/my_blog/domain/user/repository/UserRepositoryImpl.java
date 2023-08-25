package com.example.my_blog.domain.user.repository;

import com.example.my_blog.domain.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final EntityManager em;

  @Override
  public void save(User user) {
    em.persist(user);
  }

  @Override
  public Optional<User> findById(Long id) {

    return Optional.ofNullable(
        em.find(User.class, id)
    );
  }

  @Override
  public Optional<User> findByLoginId(String loginId) {
    return Optional.ofNullable(
        em.createQuery("select u from User u where u.loginId = :loginId", User.class)
        .setParameter("loginId", loginId)
        .getSingleResult()
    );
  }

  @Override
  public Optional<User> findByName(String name) {

    return Optional.ofNullable(
        em.createQuery("select u from User u where u.name = :name", User.class)
        .setParameter("name", name)
        .getSingleResult()
    );
  }

  @Override
  public Optional<User> findByNickname(String nickname) {
    return Optional.ofNullable(
        em.createQuery("select u from User u where u.nickname = :nickname", User.class)
        .setParameter("nickname", nickname)
        .getSingleResult()
    );
  }

  @Override
  public List<User> findAll() {

    return em.createQuery("select u from User u", User.class)
        .getResultList();
  }

  @Override
  public void deleteById(Long id) {

    User user = em.find(User.class, id);

    em.remove(user);
  }
}
