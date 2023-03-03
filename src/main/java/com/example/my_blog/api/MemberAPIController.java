package com.example.my_blog.api;

import com.example.my_blog.domain.member.Member;
import com.example.my_blog.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

  private final MemberService memberService;

  /**
   * 회원가입
   */
  @PostMapping("/mebmer/join")
  public JoinMemberResponse joinMember(@RequestBody JoinMemberRequest request) {
    Member member = new Member();
    member.setName(request.getName());

    memberService.join(member);

    return new JoinMemberResponse(member.getId());
  }

  @Data
  static class JoinMemberRequest {

    private String name;
  }

  @Data
  @AllArgsConstructor
  static class JoinMemberResponse {

    private Long id;
  }
}
