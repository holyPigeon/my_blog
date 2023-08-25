package com.example.my_blog.domain.user.repository;

import com.example.my_blog.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> findById(Long id);

  Optional<User> findByLoginId(String loginId);

  Optional<User> findByName(String name);

  Optional<User> findByNickname(String nickname);

  List<User> findAll();

  void deleteById(Long id);
}
