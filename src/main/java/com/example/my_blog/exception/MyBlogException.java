package com.example.my_blog.exception;

import lombok.Getter;

@Getter
public class MyBlogException extends RuntimeException {
  private MyBlogErrorCode errorCode;

  private MyBlogException(MyBlogErrorCode errorCode) { // MyBlogException 생성자. 매개된 에러 코드에 따른 메시지와 에러 코드를 지정한다.
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public static MyBlogException type(MyBlogErrorCode errorCode) { // 해당 에러 코드에 따라 새로운 MyBlogException 객체를 생성한다.
    return new MyBlogException(errorCode);
  }
}
