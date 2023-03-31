package com.example.my_blog.domain.member.service.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinMemberRequestDTO {

  private String name;
  private int age;

}

// DTO를 만들지 않고 request 클래스를 MemberApiController에 직접 만든다면 멤버 변수를 하나만 써도 상관없다.
// 하지만 대부분의 경우 DTO를 만들어서 request를 받게 되고 이 때 멤버 변수가 하나라면 이는 이미 객체의 형태로 감싸져서 전달되기 때문에
// @RequestBody를 사용했을 때 에러가 발생하게 된다.

// 예를 들어 다음과 같은 형태의 DTO를 request로 받을 때

//  @Getter
//  @AllArgsConstructor
//  public class JoinMemberRequestDTO {
//
//   private String name;
//
//  }

// 멤버 변수가 하나이기 때문에 그 자체로 이미 객체로 변환되서 전달되고
// 이에 따라 다시 한번 @RequestBody를 통해 객체로 변환하려 할 때 문제가 생긴다.
