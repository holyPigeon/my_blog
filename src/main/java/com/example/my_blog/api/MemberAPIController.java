package com.example.my_blog.api;

import com.example.my_blog.domain.member.Member;
import com.example.my_blog.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

  private final MemberService memberService;

  /**
   * 회원 가입
   */
  @PostMapping("/member/join")
  public JoinMemberResponse joinMember(@RequestBody JoinMemberRequest request) {
    Member member = new Member();
    member.setName(request.getName());

    memberService.join(member);

    return new JoinMemberResponse(member.getId());
  }

  /**
   * 회원 조회
   */
  @GetMapping("/member/list")
  public ListMemberResponse<List<MemberData>> listMember() {
    List<Member> memberList = memberService.findAll();
    List<MemberData> memberData = memberList.stream().map(m -> new MemberData(m.getId(), m.getName()))
        .collect(Collectors.toList());

    return new ListMemberResponse<>(memberData.size(), memberData);
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

  @Data
  @AllArgsConstructor
  static class ListMemberResponse<T> {

    private int count;
    private T data;
  }

  @Data
  @AllArgsConstructor
  static class MemberData {

    private Long id;
    private String name;
  }
}
