package com.example.my_blog.controller;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.service.PostService;
import com.example.my_blog.domain.post.service.dto.request.CreatePostRequest;
import com.example.my_blog.domain.post.service.dto.request.UpdatePostRequest;
import com.example.my_blog.domain.post.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final UserService userService;

  /**
   * 게시글 작성
   */
  @PostMapping("/post/create")
  public ResponseEntity<Object> registerPost(@RequestBody CreatePostRequest request) {

    User findUser = userService.findByName(request.getAuthor());
    Post post = Post.createPost(findUser, request.getTitle(), request.getContent());
    Long postId = postService.save(post);

    return ResponseEntity
        .created(URI.create("/posts/" + postId))
        .build();

  }

  /**
   * 게시글 조회
   */
  @GetMapping("/post/list")
  public ListPostResponse<List<DetailPostResponse>> listPost() {

    List<Post> posts = postService.findAll();
    List<DetailPostResponse> collect = posts.stream().map(p ->
        new DetailPostResponse(p.getId(), p.getUser().getNickname(), p.getTitle(), p.getContent(),
            p.getCreatedAt(), p.getUpdatedAt())).toList();

    return new ListPostResponse<>(collect.size(), collect);

  }

  /**
   * 게시글 상세 조회
   */
  @GetMapping("/post/list/{postId}")
  public DetailPostResponse listPostDetail(@PathVariable Long postId) {

    Post findPost = postService.findById(postId);

    return new DetailPostResponse(findPost.getId(), findPost.getUser().getNickname(), findPost.getTitle(),
        findPost.getContent(), findPost.getCreatedAt(), findPost.getUpdatedAt());
  }

  /**
   * 게시글 수정
   */
  @PostMapping("/post/update/{postId}")
  public ResponseEntity<Object> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {

    postService.updatePost(postId, request);
    Post findPost = postService.findById(postId);

    return ResponseEntity.noContent().build();
  }

  /**
   * 게시글 삭제
   */
  @DeleteMapping("/post/delete/{postId}")
  public ResponseEntity<Object> deletePost(@PathVariable Long postId) {

    postService.deleteById(postId);

    return ResponseEntity.noContent().build();
  }
}
