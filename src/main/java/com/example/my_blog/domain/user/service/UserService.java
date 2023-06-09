package com.example.my_blog.domain.user.service;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.repository.UserRepositoryImpl;
import com.example.my_blog.domain.user.service.dto.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

  private final UserRepositoryImpl memberRepository;

  @Transactional
  public Long join(User user) {
    memberRepository.save(user);

    return user.getId();
  }


  public User findById(Long id) {

    return memberRepository.findById(id);
  }

  public User findByName(String name) {

    return memberRepository.findByName(name);
  }

  public List<User> findAll() {

    return memberRepository.findAll();
  }

  @Transactional
  public void updateMember(Long id, UpdateUserRequest updateUserRequest) {
    User findUser = memberRepository.findById(id);

    if (findUser != null) {
      findUser.setName(updateUserRequest.getName());
      findUser.setAge(updateUserRequest.getAge());
    }
  }

  @Transactional
  public void deleteById(Long id) {
    memberRepository.deleteById(id);
  }

}
