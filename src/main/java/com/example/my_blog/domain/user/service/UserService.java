package com.example.my_blog.domain.user.service;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.repository.UserRepository;
import com.example.my_blog.domain.user.service.dto.request.UpdateUserRequest;
import com.example.my_blog.exception.MyBlogException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.my_blog.exception.MyBlogErrorCode.USER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public Long join(User user) {

    userRepository.save(user);

    return user.getId();
  }


  public User findById(Long id) {

    return userRepository.findById(id)
        .orElseThrow(() -> MyBlogException.type(USER_NOT_FOUND));
  }

  public User findByName(String name) {

    return userRepository.findByName(name)
        .orElseThrow(() -> MyBlogException.type(USER_NOT_FOUND));
  }

  public List<User> findAll() {

    return userRepository.findAll();
  }

  @Transactional
  public void updateUser(Long id, UpdateUserRequest updateUserRequest) {

    User findUser = userRepository.findById(id)
        .orElseThrow(() -> MyBlogException.type(USER_NOT_FOUND));

    findUser.setNickname(updateUserRequest.getNickname());
  }

  @Transactional
  public void deleteById(Long id) {

    userRepository.deleteById(id);
  }

}
