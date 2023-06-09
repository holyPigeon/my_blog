package com.example.my_blog.domain.user.repository;

import com.example.my_blog.domain.user.User;

import java.util.List;

public interface UserRepository {

  void save(User user);

  User findById(Long id);

  User findByLoginId(String loginId);

  User findByName(String name);

  User findByNickname(String nickname);

  List<User> findAll();

  void deleteById(Long id);
}
