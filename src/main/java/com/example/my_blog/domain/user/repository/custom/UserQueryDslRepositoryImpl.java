package com.example.my_blog.domain.user.repository.custom;

import com.example.my_blog.domain.user.service.dto.request.UserSearchCondition;
import com.example.my_blog.domain.user.service.dto.response.DetailUserResponse;
import com.example.my_blog.domain.user.service.dto.response.QDetailUserResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.my_blog.domain.user.QUser.*;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryDslRepositoryImpl implements  UserQueryDslRepository {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<DetailUserResponse> search(UserSearchCondition condition, Pageable pageable) {

    // 조건에 따른 회원 검색에 대한 List 결과값
    List<DetailUserResponse> content = queryFactory.select(new QDetailUserResponse(user))
        .from(user)
        .where(nameContains(condition.getName()), nicknameContains(condition.getNickname()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    // 성능 최적화를 위해 pageSize를 구하는 쿼리를 따로 빼놓았음.
    JPAQuery<DetailUserResponse> countQuery = queryFactory.select(new QDetailUserResponse(user))
        .from(user)
        .where(nameContains(condition.getName()), nicknameContains(condition.getNickname()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  private BooleanExpression nameContains(String name) {
    return hasText(name) ? user.name.contains(name) : null;
  }

  private BooleanExpression nicknameContains(String nickname) {
    return hasText(nickname) ? user.nickname.contains(nickname) : null;
  }
}
