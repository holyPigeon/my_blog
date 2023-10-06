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

import java.util.ArrayList;
import java.util.List;

import static com.example.my_blog.domain.user.QUser.*;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryDslRepositoryImpl implements  UserQueryDslRepository {

  private final JPAQueryFactory queryFactory;

  /*
  일반 회원 검색
   */
  @Override
  public Page<DetailUserResponse> listSearchResult(UserSearchCondition condition, Pageable pageable) {

    List<DetailUserResponse> content = queryFactory
        .select(new QDetailUserResponse(user))
        .from(user)
        .where(nameContains(condition.getName()), nicknameContains(condition.getNickname()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(user.id.desc())
        .fetch();

    // 성능 최적화를 위해 pageSize를 구하는 쿼리를 따로 빼놓았음.
    JPAQuery<Long> countQuery = getSearchResultCountQuery(condition, pageable);

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  /*
  정렬된 전체 회원 조회 + 일반 검색 및 정렬된 검색 결과 조회
   */
  @Override
  public Page<DetailUserResponse> listSortedSearchResult(UserSearchCondition condition, String sortType, Pageable pageable) {
    JPAQuery<DetailUserResponse> basicQuery = queryFactory
        .select(new QDetailUserResponse(user))
        .from(user)
        .where(nameContains(condition.getName()), nicknameContains(condition.getNickname()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());

    List<DetailUserResponse> content = addSortingQuery(basicQuery, sortType);

    JPAQuery<Long> countQuery = getSearchResultCountQuery(condition, pageable);

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  private JPAQuery<Long> getSearchResultCountQuery(UserSearchCondition condition, Pageable pageable) {

    return queryFactory
        .select(user.count())
        .from(user)
        .where(
            nameContains(condition.getName()),
            nicknameContains(condition.getNickname())
        );
  }

  /**
   * 이름 및 닉네임 포함 여부 조사 메소드
   */
  private BooleanExpression nameContains(String name) {
    return hasText(name) ? user.name.contains(name) : null;
  }

  private BooleanExpression nicknameContains(String nickname) {
    return hasText(nickname) ? user.nickname.contains(nickname) : null;
  }

  /**
   * 정렬 쿼리 추가 메소드
   */
  private List<DetailUserResponse> addSortingQuery(JPAQuery<DetailUserResponse> basicQuery, String sortType) {
    List<DetailUserResponse> content;
    switch (sortType) {

      case "name":
        content = basicQuery
            .orderBy(user.name.desc())
            .fetch();
        break;

      case "rname":
        content = basicQuery
            .orderBy(user.name.asc())
            .fetch();
        break;

      case "nickname":
        content = basicQuery
            .orderBy(user.nickname.desc())
            .fetch();
        break;

      case "rnickname":
        content = basicQuery
            .orderBy(user.nickname.asc())
            .fetch();
        break;

      case "date":
        content = basicQuery
            .orderBy(user.id.desc())
            .fetch();
        break;

      case "rdate":
        content = basicQuery
            .orderBy(user.id.asc())
            .fetch();
        break;

      default:
        content = new ArrayList<>(); // return empty list
    }

    return content;
  }
}
