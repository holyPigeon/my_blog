package com.example.my_blog.controller;

import com.example.my_blog.SessionConst;
import com.example.my_blog.domain.login.service.LoginService;
import com.example.my_blog.domain.login.service.dto.request.LoginRequest;
import com.example.my_blog.domain.login.service.dto.response.DetailSessionResponse;
import com.example.my_blog.domain.login.service.dto.response.LoginResponse;
import com.example.my_blog.domain.login.service.dto.response.LogoutResponse;
import com.example.my_blog.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {

    User loginUser = loginService.login(loginRequest.getLoginId(), loginRequest.getPassword());
    log.info("login: {}", loginUser);

    if (loginUser == null) {
      log.error("login failed");
      return new LoginResponse(-1L, "dummy", "dummy", "dummy", "dummy");
    }

    HttpSession session = request.getSession();
    session.setAttribute(SessionConst.SESSION_KEY, loginUser);

    return new LoginResponse(loginUser.getId(), loginUser.getLoginId(), loginUser.getPassword(), loginUser.getName(), loginUser.getNickname());
  }

  @PostMapping("/logout")
  public LogoutResponse logout(HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    return new LogoutResponse(true, "로그아웃");
  }

  @GetMapping("/session")
  public DetailSessionResponse detailSession(HttpServletRequest request) {

    HttpSession session = request.getSession(false);

    if (!Objects.isNull(session) && !Objects.isNull(session.getAttribute(SessionConst.SESSION_KEY))) {
      User loginUser = (User) session.getAttribute(SessionConst.SESSION_KEY);
      return new DetailSessionResponse(loginUser.getId(), loginUser.getLoginId(), loginUser.getName());
    } else {
      return new DetailSessionResponse(-1L, "", "");
    }
  }
}
