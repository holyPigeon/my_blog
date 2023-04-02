package com.example.my_blog.controller;

import com.example.my_blog.domain.member.Member;
import com.example.my_blog.domain.member.service.MemberService;
import com.example.my_blog.domain.member.service.dto.request.JoinMemberRequestDTO;
import com.example.my_blog.domain.member.service.dto.response.JoinMemberResponseDTO;
import com.example.my_blog.domain.member.service.dto.response.ListMemberResponse;
import com.example.my_blog.domain.member.service.dto.response.ListMemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  /**
   * 회원 가입
   */
  @PostMapping("/member/join")
  public JoinMemberResponseDTO joinMember(@RequestBody JoinMemberRequestDTO joinMemberRequestDTO) {
    Member member = new Member();

    member.setName(joinMemberRequestDTO.getName());
    member.setAge(joinMemberRequestDTO.getAge());

    Long joinId = memberService.join(member);

    return new JoinMemberResponseDTO(joinId);
  }

  /**
   * 회원 조회
   */
  @GetMapping("/member/list")
  public ListMemberResponse<List<ListMemberResponseDTO>> listMember() {
    List<Member> memberList = memberService.findAll();
    List<ListMemberResponseDTO> listMemberData = memberList.stream().map(m -> new ListMemberResponseDTO(m.getId(), m.getName(), m.getAge()))
        .toList();

    return new ListMemberResponse<>(listMemberData.size(), listMemberData);
  }

}
