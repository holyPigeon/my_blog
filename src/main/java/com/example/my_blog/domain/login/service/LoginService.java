 package com.example.my_blog.domain.login.service;

import com.example.my_blog.domain.member.Member;
import com.example.my_blog.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

  private final MemberRepository memberRepository;

  public Member login(String username, String password) {

    Member findMember = memberRepository.findByLoginId(username);
    if (!isCorrectPassword(password, findMember)) {
      return null;
    }

    return findMember;
  }

  private boolean isCorrectPassword(String password, Member findMember) {
    return findMember.getPassword().equals(password);
  }
}
