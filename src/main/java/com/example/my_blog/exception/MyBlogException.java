package com.example.my_blog.exception;

import lombok.Getter;

@Getter
public class MyBlogException extends RuntimeException {
  private MyBlogErrorCode errorCode;

  private MyBlogException(MyBlogErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public static MyBlogException type(MyBlogErrorCode errorCode) {
    return new MyBlogException(errorCode);
  }
}
