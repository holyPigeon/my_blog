package com.example.my_blog.domain.member.service;

import com.example.my_blog.domain.member.Member;
import com.example.my_blog.domain.member.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepositoryImpl memberRepository;

  @Transactional
  public Long join(Member member) {
    memberRepository.save(member);

    return member.getId();
  }


  public Member findOne(Long id) {
    Member findMember = memberRepository.findById(id);

    return findMember;
  }

  public List<Member> findAll() {
    List<Member> memberList = memberRepository.findAll();

    return memberList;
  }

  @Transactional
  public void updateName(Long id, String name) {
    Member findMember = memberRepository.findById(id);

    findMember.setName("name");
  }

}
