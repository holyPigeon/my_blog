package com.example.my_blog.controller;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import com.example.my_blog.domain.user.service.dto.request.JoinUserRequest;
import com.example.my_blog.domain.user.service.dto.request.UpdateUserRequest;
import com.example.my_blog.domain.user.service.dto.request.UserSearchCondition;
import com.example.my_blog.domain.user.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8081"})
public class UserController {

  private final UserService userService;

  /**
   * 회원 가입
   */
  @PostMapping("/users")
  public ResponseEntity<Object> joinUser(@RequestBody JoinUserRequest request) {

    User user = User.createUser(request.getLoginId(), request.getPassword(), request.getName(), request.getNickname());
    Long userId = userService.join(user);

    return ResponseEntity
        .created(URI.create("/users/" + userId))
        .build();
  }

  /**
   * 회원 상세 조회
   */
  @GetMapping("/users/{userId}")
  public DetailUserResponse listDetailUser(@PathVariable Long userId) {

    User findUser = userService.findById(userId);

    return new DetailUserResponse(findUser);
  }

  /**
   * 회원 전체 조회
   */
  @GetMapping("/users")
  public Page<DetailUserResponse> listUser(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam("sort") String sort,
      @RequestParam("name") String name,
      @RequestParam("nickname") String nickname
  ) {

    PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "id"));
    UserSearchCondition userSearchCondition = new UserSearchCondition(name, nickname);
    Page<DetailUserResponse> userDtoPage = userService.search(userSearchCondition, pageRequest);

    return userDtoPage;
  }

  /**
   * 회원 수정
   */
  @PatchMapping("/users/{userId}")
  public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {

    userService.updateUser(userId, request);
    User findUser = userService.findById(userId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 회원 삭제
   */
  @DeleteMapping("/users/{userId}")
  public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {

    userService.deleteById(userId);

    return ResponseEntity.noContent().build();
  }
}
