package com.example.my_blog.controller;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import com.example.my_blog.domain.user.service.dto.request.JoinUserRequest;
import com.example.my_blog.domain.user.service.dto.request.UpdateUserRequest;
import com.example.my_blog.domain.user.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * 회원 가입
   */
  @PostMapping("/user/join")
  public ResponseEntity<Object> joinUser(@RequestBody JoinUserRequest request) {

    User user = User.createUser(request.getLoginId(), request.getPassword(), request.getName(), request.getNickname());
    Long userId = userService.join(user);

    return ResponseEntity
        .created(URI.create("/users/" + userId))
        .build();
  }

  /**
   * 회원 조회
   */
  @GetMapping("/user/list")
  public ListUserResponse<List<DetailUserResponse>> listUser() {

    List<User> userList = userService.findAll();
    List<DetailUserResponse> listUserData = userList.stream().map(u -> new DetailUserResponse(u.getId(), u.getName(), u.getNickname()))
        .toList();

    return new ListUserResponse<>(listUserData.size(), listUserData);
  }

  /**
   * 회원 상세 조회
   */
  @GetMapping("/user/list/{userId}")
  public DetailUserResponse listDetailUser(@PathVariable Long userId) {

    User findUser = userService.findById(userId);

    return new DetailUserResponse(findUser.getId(), findUser.getName(), findUser.getNickname());
  }

  /**
   * 회원 수정
   */
  @PostMapping("/user/update/{userId}")
  public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {

    userService.updateUser(userId, request);
    User findUser = userService.findById(userId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 회원 삭제
   */
  @DeleteMapping("/user/delete/{userId}")
  public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {

    userService.deleteById(userId);

    return ResponseEntity.noContent().build();
  }
}
