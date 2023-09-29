package com.example.my_blog.domain.user.repository.custom;

import com.example.my_blog.domain.user.service.dto.request.UserSearchCondition;
import com.example.my_blog.domain.user.service.dto.response.DetailUserResponse;
import com.example.my_blog.domain.user.service.dto.response.QDetailUserResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.my_blog.domain.user.QUser.*;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryDslRepositoryImpl implements  UserQueryDslRepository {

  private final JPAQueryFactory queryFactory;

  public UserQueryDslRepositoryImpl (EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public List<DetailUserResponse> search(UserSearchCondition condition) {

    return queryFactory.select(new QDetailUserResponse(user))
        .from(user)
        .where(nameContains(condition.getName()), nicknameContains(condition.getNickname()))
        .fetch();
  }

  private BooleanExpression nameContains(String name) {
    return hasText(name) ? user.name.contains(name) : null;
  }

  private BooleanExpression nicknameContains(String nickname) {
    return hasText(nickname) ? user.nickname.contains(nickname) : null;
  }
}
