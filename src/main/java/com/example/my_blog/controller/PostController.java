package com.example.my_blog.controller;

import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.service.PostService;
import com.example.my_blog.domain.post.service.dto.request.CreatePostRequest;
import com.example.my_blog.domain.post.service.dto.request.UpdatePostRequest;
import com.example.my_blog.domain.post.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
  public CreatePostResponse registerPost(@RequestBody CreatePostRequest createPostRequest) {

    User findUser = userService.findByName(createPostRequest.getAuthor());
    Post post = Post.createPost(findUser, createPostRequest.getTitle(), createPostRequest.getContent());
    Long postId = postService.save(post);

    return new CreatePostResponse(postId);

  }

  /**
   * 게시글 조회
   */
  @GetMapping("/post/list")
  public ListPostResponse<List<DetailPostResponse>> listPost() {

    List<Post> posts = postService.findAll();
    List<DetailPostResponse> collect = posts.stream().map(p ->
        new DetailPostResponse(p.getId(), p.getUser().getName(), p.getTitle(), p.getContent(),
            p.getCreatedAt(), p.getUpdatedAt())).toList();

    return new ListPostResponse<>(collect.size(), collect);

  }

  /**
   * 게시글 상세 조회
   */
  @GetMapping("/post/list/{postId}")
  public DetailPostResponse listPostDetail(@PathVariable Long postId) {

    Post findPost = postService.findById(postId);

    return new DetailPostResponse(findPost.getId(), findPost.getUser().getName(), findPost.getTitle(),
        findPost.getContent(), findPost.getCreatedAt(), findPost.getUpdatedAt());
  }

  /**
   * 게시글 수정
   */
  @PostMapping("/post/update/{postId}")
  public UpdatePostResponse updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {

    postService.updatePost(postId, updatePostRequest);
    Post findPost = postService.findById(postId);

    return new UpdatePostResponse(findPost.getId(), findPost.getUser().getName(),
        findPost.getTitle(), findPost.getContent(), findPost.getCreatedAt(), findPost.getUpdatedAt());
  }

  /**
   * 게시글 삭제
   */
  @DeleteMapping("/post/delete/{postId}")
  public DeletePostResponse deletePost(@PathVariable Long postId) {

    postService.deleteById(postId);

    return new DeletePostResponse(postId);
  }
}
