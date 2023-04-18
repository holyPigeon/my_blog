package com.example.my_blog.controller;

import com.example.my_blog.domain.member.Member;
import com.example.my_blog.domain.member.service.MemberService;
import com.example.my_blog.domain.member.service.dto.request.JoinMemberRequestDTO;
import com.example.my_blog.domain.member.service.dto.request.UpdateMemberRequestDTO;
import com.example.my_blog.domain.member.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    member.setLoginId(joinMemberRequestDTO.getLoginId());
    member.setPassword(joinMemberRequestDTO.getPassword());
    member.setName(joinMemberRequestDTO.getName());
    member.setAge(joinMemberRequestDTO.getAge());
    Long joinId = memberService.join(member);

    return new JoinMemberResponseDTO(joinId);
  }

  /**
   * 회원 조회
   */
  @GetMapping("/member/list")
  public ListMemberResponse<List<DetailMemberResponseDTO>> listMember() {

    List<Member> memberList = memberService.findAll();
    List<DetailMemberResponseDTO> listMemberData = memberList.stream().map(m -> new DetailMemberResponseDTO(m.getId(), m.getName(), m.getAge()))
        .toList();

    return new ListMemberResponse<>(listMemberData.size(), listMemberData);
  }

  /**
   * 회원 상세 조회
   */
  @GetMapping("/member/list/{memberId}")
  public DetailMemberResponseDTO listDetailMember(@PathVariable Long memberId) {

    Member findMember = memberService.findById(memberId);

    return new DetailMemberResponseDTO(findMember.getId(), findMember.getName(), findMember.getAge());
  }

  /**
   * 회원 수정
   */
  @PostMapping("/member/update/{memberId}")
  public UpdateMemberResponseDTO updateMember(@PathVariable Long memberId, @RequestBody UpdateMemberRequestDTO updateMemberRequestDTO) {

    memberService.updateMember(memberId, updateMemberRequestDTO);
    Member findMember = memberService.findById(memberId);

    return new UpdateMemberResponseDTO(findMember.getId(), findMember.getName(), findMember.getAge());
  }

  /**
   * 회원 삭제
   */
  @DeleteMapping("/member/delete/{memberId}")
  public DeleteMemberResponseDTO deleteMember(@PathVariable Long memberId) {

    memberService.deleteById(memberId);

    return new DeleteMemberResponseDTO(memberId);
  }
}
