package com.example.my_blog.domain.member.repository;

import com.example.my_blog.domain.member.Member;

import java.util.List;

public interface MemberRepository {

  void save(Member member);

  Member findById(Long id);

  Member findByLoginId(String loginId);

  Member findByName(String name);

  List<Member> findAll();

  void deleteById(Long id);
}
