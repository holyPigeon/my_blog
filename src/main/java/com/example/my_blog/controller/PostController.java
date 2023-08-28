package com.example.my_blog.controller;

import com.example.my_blog.domain.post.service.dto.request.*;
import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.service.PostService;
import com.example.my_blog.domain.post.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:8080"})
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
   * 게시글 조회
   */
  @GetMapping("/posts")
  public ListPostResponse<List<DetailPostResponse>> listPost() {

    List<Post> posts = postService.findAll();

    List<DetailPostResponse> collect = posts.stream().map(p ->
        new DetailPostResponse(p.getId(), p.getUser().getNickname(), p.getTitle(), p.getContent(),
            p.getCreatedAt(), p.getUpdatedAt())).toList();

    List<DetailPostResponse> sorted = collect.stream()
        .sorted(Comparator.comparing(DetailPostResponse::getId).reversed())
        .toList();

    return new ListPostResponse<>(sorted.size(), sorted);

  }

  /**
   * 게시글 상세 조회
   */
  @GetMapping("/posts/{postId}")
  public DetailPostResponse listPostDetail(@PathVariable Long postId) {

    Post findPost = postService.findById(postId);

    return new DetailPostResponse(findPost.getId(), findPost.getUser().getNickname(), findPost.getTitle(),
        findPost.getContent(), findPost.getCreatedAt(), findPost.getUpdatedAt());
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
