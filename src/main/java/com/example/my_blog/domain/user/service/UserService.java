package com.example.my_blog.domain.user.service;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.repository.UserRepository;
import com.example.my_blog.domain.user.service.dto.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    return userRepository.findById(id).orElseThrow(
        () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다."));
  }

  public User findByName(String name) {

    return userRepository.findByName(name).orElseThrow(
        () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다."));
  }

  public List<User> findAll() {

    return userRepository.findAll();
  }

  @Transactional
  public void updateUser(Long id, UpdateUserRequest updateUserRequest) {

    User findUser = userRepository.findById(id).orElseThrow(
        () -> new NoSuchElementException("해당 사용자가 존재하지 않습니다."));

    findUser.setNickname(updateUserRequest.getNickname());
  }

  @Transactional
  public void deleteById(Long id) {

    userRepository.deleteById(id);
  }

}
