package com.example.my_blog.domain.post.repository.custom;

import com.example.my_blog.domain.post.service.dto.request.PostSearchCondition;
import com.example.my_blog.domain.post.service.dto.response.DetailPostResponse;
import com.example.my_blog.domain.post.service.dto.response.QDetailPostResponse;
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

import static com.example.my_blog.domain.post.QPost.post;
import static com.example.my_blog.domain.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryDslRepositoryImpl implements PostQueryDslRepository{

  private final JPAQueryFactory queryFactory;

  /*
  일반 게시글 검색
   */
  @Override
  public Page<DetailPostResponse> listSearchResult(PostSearchCondition condition, Pageable pageable) {

    List<DetailPostResponse> content = queryFactory
        .select(new QDetailPostResponse(post))
        .from(post)
        .leftJoin(post.user, user).fetchJoin()
        .where(
            titleContains(condition.getTitle()),
            contentContains(condition.getContent()),
            authorContains(condition.getAuthor())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(post.id.desc())
        .fetch();

    // 성능 최적화를 위해 pageSize를 구하는 쿼리를 따로 빼놓았음.
    JPAQuery<Long> countQuery = getSearchResultCountQuery(condition, pageable);

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  /*
  정렬된 전체 게시글 조회 + 일반 검색 및 정렬된 검색 결과 조회
   */
  @Override
  public Page<DetailPostResponse> listSortedSearchResult(PostSearchCondition condition, String sortType, Pageable pageable) {
    JPAQuery<DetailPostResponse> basicQuery = queryFactory
        .select(new QDetailPostResponse(post))
        .from(post)
        .leftJoin(post.user, user).fetchJoin()
        .where(
            titleContains(condition.getTitle()),
            contentContains(condition.getContent()),
            authorContains(condition.getAuthor())
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize());

    List<DetailPostResponse> content = addSortingQuery(basicQuery, sortType);

    // 성능 최적화를 위해 pageSize를 구하는 쿼리를 따로 빼놓았음.
    JPAQuery<Long> countQuery = getSearchResultCountQuery(condition, pageable);

    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
  }

  /*
    pageSize를 구하는 Count 쿼리를 성능 최적화를 위해 따로 구현 + 가독성을 위해 메소드 추출
   */
  private JPAQuery<Long> getSearchResultCountQuery(PostSearchCondition condition, Pageable pageable) {

    return queryFactory
        .select(post.count())
        .from(post)
        .leftJoin(post.user, user).fetchJoin()
        .where(
            titleContains(condition.getTitle()),
            contentContains(condition.getContent()),
            authorContains(condition.getAuthor())
        );
  }

  /**
   * 제목, 내용, 글쓴이 포함 여부 조사 메소드
   */
  private BooleanExpression titleContains(String title) {
    return hasText(title) ? post.title.contains(title) : null;
  }

  private BooleanExpression contentContains(String content) {
    return hasText(content) ? post.content.contains(content) : null;
  }

  private BooleanExpression authorContains(String author) {
    return hasText(author) ? post.user.nickname.contains(author) : null;
  }

  /*
    정렬 관련 쿼리를 추가하기 위한 메소드
   */
  private List<DetailPostResponse> addSortingQuery(JPAQuery<DetailPostResponse> basicQuery, String sortType) {
    List<DetailPostResponse> content;
    switch (sortType) {

      case "view":
        content = basicQuery
            .orderBy(post.viewCount.desc())
            .fetch();
        break;

      case "rview":
        content = basicQuery
            .orderBy(post.viewCount.asc())
            .fetch();
        break;

      case "like":
        content = basicQuery
            .orderBy(post.likes.size().desc())
                .fetch();
        break;

      case "rlike":
        content = basicQuery
            .orderBy(post.likes.size().asc())
            .fetch();
        break;

      case "date":
        content = basicQuery
            .orderBy(post.createdDate.desc())
            .fetch();
        break;

      case "rdate":
        content = basicQuery
            .orderBy(post.createdDate.asc())
            .fetch();
        break;

      default:
        content = new ArrayList<>();
    }

    return content;
  }
}
