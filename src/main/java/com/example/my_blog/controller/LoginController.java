package com.example.my_blog.controller;

import com.example.my_blog.SessionConst;
import com.example.my_blog.domain.login.service.LoginService;
import com.example.my_blog.domain.login.service.dto.request.LoginRequestDTO;
import com.example.my_blog.domain.login.service.dto.response.DetailSessionResponseDTO;
import com.example.my_blog.domain.login.service.dto.response.LoginResponseDTO;
import com.example.my_blog.domain.login.service.dto.response.LogoutResponseDTO;
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
  public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request) {

    User loginUser = loginService.login(loginRequestDTO.getLoginId(), loginRequestDTO.getPassword());
    log.info("login: {}", loginUser);

    if (loginUser == null) {
      log.error("login failed");
      return new LoginResponseDTO(-1L, "null", "null", "null", -1);
    }

    HttpSession session = request.getSession();
    session.setAttribute(SessionConst.SESSION_KEY, loginUser);

    return new LoginResponseDTO(loginUser.getId(), loginUser.getLoginId(), loginUser.getPassword(), loginUser.getName(), loginUser.getAge());
  }

  @PostMapping("/logout")
  public LogoutResponseDTO logout(HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    return new LogoutResponseDTO(true, "로그아웃");
  }

  @GetMapping("/session")
  public DetailSessionResponseDTO detailSession(HttpServletRequest request) {

    HttpSession session = request.getSession(false);

    if (!Objects.isNull(session) && !Objects.isNull(session.getAttribute(SessionConst.SESSION_KEY))) {
      User loginUser = (User) session.getAttribute(SessionConst.SESSION_KEY);
      return new DetailSessionResponseDTO(loginUser.getId(), loginUser.getLoginId(), loginUser.getName(), loginUser.getAge());
    } else {
      return new DetailSessionResponseDTO(-1L, "", "", -1);
    }
  }
}
