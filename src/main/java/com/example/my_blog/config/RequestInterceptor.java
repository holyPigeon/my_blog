package com.example.my_blog.config;

import com.example.my_blog.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Objects;

import static com.example.my_blog.SessionConst.SESSION_KEY;
import static com.example.my_blog.exception.MyBlogErrorCode.AUTHENTICATION_USER;


@Slf4j
public class RequestInterceptor implements HandlerInterceptor{
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    String requestURI = request.getRequestURI();
    log.info("요청 URI : [{}] {}", request.getMethod(), requestURI);

    HttpSession session = request.getSession(false);
    if (Objects.isNull(session) || Objects.isNull(session.getAttribute(SESSION_KEY))) {
      if (Objects.equals(request.getMethod(), "GET") && (
          PatternMatchUtils.simpleMatch("/api/users/", request.getRequestURI()) ||
              PatternMatchUtils.simpleMatch("/api/posts/", request.getRequestURI())
      )) {
        return true;
      }

      log.info("미인증 사용자 요청");
      String errorResponseToJSON = objectMapper.writeValueAsString(ErrorResponse.of(AUTHENTICATION_USER));

      response.setStatus(AUTHENTICATION_USER.getStatus().value());
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(errorResponseToJSON);
      response.sendRedirect("/login");
      return false;
    }

    return true;
  }
}


