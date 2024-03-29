 package com.example.my_blog.domain.login.service;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.repository.UserRepository;
import com.example.my_blog.exception.MyBlogException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.my_blog.exception.MyBlogErrorCode.USER_NOT_FOUND;
import static com.example.my_blog.exception.MyBlogErrorCode.WRONG_PASSWORD;

 @Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

  private final UserRepository userRepository;

  public User login(String username, String password) {

    User findUser = userRepository.findByLoginId(username)
        .orElseThrow(() -> MyBlogException.type(USER_NOT_FOUND));

    checkIsCorrectPassword(findUser, password);

    return findUser;
  }

  private void checkIsCorrectPassword(User findUser, String password) {

    if (!findUser.getPassword().equals(password)) {
      throw MyBlogException.type(WRONG_PASSWORD);
    }
  }
}
