package com.example.my_blog.domain.member.repository;

import com.example.my_blog.domain.member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

  private final EntityManager em;

  @Override
  public void save(Member member) {
    em.persist(member);
  }

  @Override
  public Member findById(Long id) {

    return em.find(Member.class, id);
  }

  @Override
  public Member findByLoginId(String loginId) {
    return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
        .setParameter("loginId", loginId)
        .getSingleResult();
  }

  @Override
  public Member findByName(String name) {

    return em.createQuery("select m from Member m where m.name = :name", Member.class)
        .setParameter("name", name)
        .getSingleResult();
  }

  @Override
  public List<Member> findAll() {

    return em.createQuery("select m from Member m", Member.class)
        .getResultList();
  }

  @Override
  public void deleteById(Long id) {

    Member member = em.find(Member.class, id);

    em.remove(member);
  }
}
