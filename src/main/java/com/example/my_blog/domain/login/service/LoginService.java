 package com.example.my_blog.domain.login.service;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

 @Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

  private final UserRepository userRepository;

  public User login(String username, String password) {

    User findUser = userRepository.findByLoginId(username).orElseThrow(
        () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다.")
    );

    if (!isCorrectPassword(password, findUser)) {
      return null;
    }

    return findUser;
  }

  private boolean isCorrectPassword(String password, User findUser) {

    return findUser.getPassword().equals(password);
  }
}
