package com.example.my_blog.controller;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import com.example.my_blog.domain.user.service.dto.request.JoinUserRequest;
import com.example.my_blog.domain.user.service.dto.request.UpdateUserRequest;
import com.example.my_blog.domain.user.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * 회원 가입
   */
  @PostMapping("/user/join")
  public JoinUserResponse joinMember(@RequestBody JoinUserRequest joinUserRequest) {

    User user = new User();
    user.setLoginId(joinUserRequest.getLoginId());
    user.setPassword(joinUserRequest.getPassword());
    user.setName(joinUserRequest.getName());
    Long joinId = userService.join(user);

    return new JoinUserResponse(joinId);
  }

  /**
   * 회원 조회
   */
  @GetMapping("/user/list")
  public ListUserResponse<List<DetailUserResponse>> listMember() {

    List<User> userList = userService.findAll();
    List<DetailUserResponse> listUserData = userList.stream().map(u -> new DetailUserResponse(u.getId(), u.getName()))
        .toList();

    return new ListUserResponse<>(listUserData.size(), listUserData);
  }

  /**
   * 회원 상세 조회
   */
  @GetMapping("/user/list/{userId}")
  public DetailUserResponse listDetailMember(@PathVariable Long userId) {

    User findUser = userService.findById(userId);

    return new DetailUserResponse(findUser.getId(), findUser.getName());
  }

  /**
   * 회원 수정
   */
  @PostMapping("/user/update/{userId}")
  public UpdateUserResponse updateMember(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {

    userService.updateMember(userId, updateUserRequest);
    User findUser = userService.findById(userId);

    return new UpdateUserResponse(findUser.getId(), findUser.getName());
  }

  /**
   * 회원 삭제
   */
  @DeleteMapping("/user/delete/{userId}")
  public DeleteUserResponse deleteMember(@PathVariable Long userId) {

    userService.deleteById(userId);

    return new DeleteUserResponse(userId);
  }
}
