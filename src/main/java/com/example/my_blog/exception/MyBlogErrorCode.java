package com.example.my_blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MyBlogErrorCode {
  // User
  AUTHENTICATION_USER(HttpStatus.UNAUTHORIZED, "로그인하지 않은 사용자입니다"),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보가 존재하지 않습니다"),
  WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다"),
  DUPLICATE_USER_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다"),
  DUPLICATE_USER_LOGIN_ID(HttpStatus.CONFLICT, "중복된 로그인 아이디입니다"),
  DUPLICATE_USER_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다"),
  DUPLICATE_USER_PHONENUMBER(HttpStatus.CONFLICT, "중복된 전화번호입니다"),
  ILLEGAL_USER_API_REQUEST(HttpStatus.BAD_REQUEST, "타인의 정보는 요청할 수 없습니다"),
  SAME_PASSWORD_AS_BEFORE(HttpStatus.CONFLICT, "이전에 사용하던 비밀번호로 변경할 수 없습니다"),

  // Post
  POST_NOT_FOUND(HttpStatus.NOT_FOUND, "작품 정보가 존재하지 않습니다"),
  ART_SOLD_OUT(HttpStatus.BAD_REQUEST, "이미 판매 완료된 작품입니다"),
  ILLEGAL_ART_UPLOAD(HttpStatus.BAD_REQUEST, "작품 파일이 업로드되지 않았습니다"),
  ALREADY_LIKE_MARKING(HttpStatus.BAD_REQUEST, "이미 좋아요한 글입니다"),
  ILLEGAL_LIKE_MARKING_CANCEL(HttpStatus.BAD_REQUEST, "이미 좋아요를 취소하였거나 좋아요를 한 적이 없는 작품입니다"),
  COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "댓글 정보가 존재하지 않습니다."),
  PARENT_COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "부모 댓글 정보가 존재하지 않습니다."),

  // Point
  POINT_NOT_ENOUGH(HttpStatus.CONFLICT, "포인트가 부족합니다"),

  // Purchase
  INELIGIBLE_USER_PURCHASE(HttpStatus.FORBIDDEN, "구매 자격이 없는 사용자입니다"),
  ILLEGAL_PURCHASE_ACCESS(HttpStatus.CONFLICT, "본인 작품을 입찰 또는 구매할 수 없습니다"),

  // API Request
  ILLEGAL_URL_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

  // RunTime Server Error
  RUN_TIME_ART_REGISTER(HttpStatus.INTERNAL_SERVER_ERROR, "작품 등록간 서버상에 오류가 발생했습니다")

  ;

  private final HttpStatus status;
  private final String message;

  MyBlogErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}
