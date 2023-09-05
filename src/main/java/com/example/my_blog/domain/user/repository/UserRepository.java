package com.example.my_blog.domain.user.repository;

import com.example.my_blog.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLoginId(String loginId);

  Optional<User> findByName(String name);

  Optional<User> findByNickname(String nickname);

  void deleteById(Long id);
}
