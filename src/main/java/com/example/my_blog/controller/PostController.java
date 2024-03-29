package com.example.my_blog.controller;

import com.example.my_blog.domain.post.service.dto.request.*;
import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.service.PostService;
import com.example.my_blog.domain.post.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8081"})
public class PostController {

  private final PostService postService;
  private final UserService userService;

  /**
   * 게시글 작성
   */
  @PostMapping("/posts")
  public ResponseEntity<Object> registerPost(@RequestBody CreatePostRequest request) {

    User findUser = userService.findByNickname(request.getAuthor());
    Post post = Post.createPost(findUser, request.getTitle(), request.getContent());
    Long postId = postService.save(post);

    return ResponseEntity
        .created(URI.create("/posts/" + postId))
        .build();
  }

  /**
   * 게시글 단일 조회
   */
  @GetMapping("/posts/{postId}")
  public DetailPostResponse listPostDetail(@PathVariable Long postId) {

    postService.incrementViewCount(postId);
    Post findPost = postService.findById(postId);

    return new DetailPostResponse(findPost);
  }

  /**
   * 게시글 전체 조회
   */
  @GetMapping("/posts")
  public Page<DetailPostResponse> listPost(
      @RequestParam("page") int page,
      @RequestParam("size") int size
  ) {

    PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));
    Page<DetailPostResponse> postDtoPage = postService
        .findAll(pageRequest)
        .map(DetailPostResponse::new);

    return postDtoPage;
  }

  /**
   * 게시글 검색
   */
  @GetMapping("/posts/search")
  public Page<DetailPostResponse> searchPost(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam("author") String author
  ) {

    PageRequest pageRequest = PageRequest.of(page - 1, size);
    PostSearchCondition postSearchCondition = new PostSearchCondition(title, content, author);
    Page<DetailPostResponse> postDtoPage = postService.listSearchResult(postSearchCondition, pageRequest);

    return postDtoPage;
  }

  /**
   * 정렬된 전체 회원 조회 + 일반 검색 및 정렬된 검색 결과 조회
   */
  @GetMapping("/posts/sort")
  public Page<DetailPostResponse> searchSortedPost(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam("sort") String sortType,
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam("author") String author
  ) {

    PageRequest pageRequest = PageRequest.of(page - 1, size);
    PostSearchCondition postSearchCondition = new PostSearchCondition(title, content, author);
    Page<DetailPostResponse> postDtoPage = postService.listSortedSearchResult(postSearchCondition, sortType, pageRequest);

    return postDtoPage;
  }

  /**
   * 게시글 수정
   */
  @PatchMapping("/posts/{postId}")
  public ResponseEntity<Object> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {

    postService.updatePost(postId, request);
    Post findPost = postService.findById(postId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 게시글 삭제
   */
  @DeleteMapping("/posts/{postId}")
  public ResponseEntity<Object> deletePost(@PathVariable Long postId) {

    postService.deleteById(postId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 게시글 좋아요
   */
  @PostMapping("/posts/like")
  public ResponseEntity<Object> likePost(@RequestBody LikePostRequest request) {

    postService.likePost(request.getPostId(), request.getUserId());

    return ResponseEntity.noContent().build();
  }

  /**
   * 게시글 좋아요 취소
   */
  @PostMapping("/posts/cancelLike")
  public ResponseEntity<Object> cancelLikePost(@RequestBody CancelLikePostRequest request) {

    postService.cancelLikePost(request.getPostId(), request.getUserId());

    return ResponseEntity.noContent().build();
  }

  /**
   * 게시글 좋아요 체크
   */
  @PostMapping("/posts/isAlreadyLiked")
  public IsAlreadyLikedResponse isAlreadyLiked(@RequestBody IsAlreadyLikedRequest request) {

    if (postService.isAlreadyLiked(request.getPostId(), request.getUserId())) {
      return new IsAlreadyLikedResponse(true);
    } else {
      return new IsAlreadyLikedResponse(false);
    }
  }

  @GetMapping("/posts/{postId}/getPostLikeCount")
  public GetPostLikeCountResponse getPostLikeCount(@PathVariable Long postId) {

    int postLikeCount = postService.getPostLikeCount(postId);

    return new GetPostLikeCountResponse(postLikeCount);
  }
}
