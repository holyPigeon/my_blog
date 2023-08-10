package com.example.my_blog.controller;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.comment.service.CommentService;
import com.example.my_blog.domain.comment.service.dto.request.CreateCommentRequest;
import com.example.my_blog.domain.comment.service.dto.request.CreateReplyCommentRequest;
import com.example.my_blog.domain.comment.service.dto.request.UpdateCommentRequest;
import com.example.my_blog.domain.comment.service.dto.response.ListCommentDetailResponse;
import com.example.my_blog.domain.comment.service.dto.response.ListCommentResponse;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.service.PostService;
import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
//@CrossOrigin(origins = {"http://localhost:8080"})
//@Api(tags = "댓글 관련 API")
public class CommentController {

  private final UserService userService;
  private final PostService postService;
  private final CommentService commentService;

  @PostMapping("/posts/{postId}/comments")
//  @ApiOperation(value = "댓글 등록 API", notes = "해당 게시물에 댓글 등록")
  public ResponseEntity<Long> createComment(@PathVariable Long postId, @RequestBody CreateCommentRequest request) {

    Post post = postService.findById(postId);
    User user = userService.findById(request.getUserId());

    Comment comment = Comment.createComment(user, post, request.getContent());
    Long commentId = commentService.save(comment);

    return ResponseEntity
        .created(URI.create("/posts/" + postId + "/comments/" + commentId))
        .build();
  }

  @PostMapping("/posts/{postId}/comments/reply")
//  @ApiOperation(value = "댓글 등록 API", notes = "해당 게시물에 댓글 등록")
  public ResponseEntity<Long> createReplyComment(@PathVariable Long postId, @RequestBody CreateReplyCommentRequest request) {

    Post post = postService.findById(postId);
    User user = userService.findById(request.getUserId());

    Comment comment = Comment.createComment(user, post, request.getContent());
    Long commentId = commentService.saveReplyComment(request.getParentId(), comment);

    return ResponseEntity
        .created(URI.create("/posts/" + postId + "/comments/" + commentId))
        .build();
  }

  @GetMapping("/posts/{postId}/comments")
//  @ApiOperation(value = "전체 댓글 조회 API", notes = "해당 게시글의 전체 댓글 조회")
  public ListCommentResponse<List<ListCommentDetailResponse>> listComment(@PathVariable Long postId) {

    Post post = postService.findById(postId);
    List<ListCommentDetailResponse> listCommentData = post.getComments().stream()
        .map(comment -> new ListCommentDetailResponse(comment.getId(), post.getId(), comment.getUser().getNickname(),
            comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt())).collect(Collectors.toList());

    return new ListCommentResponse<>(listCommentData.size(), listCommentData);
  }

  @GetMapping("/posts/{postId}/comments/{commentId}")
//  @ApiOperation(value = "단일 댓글 조회 API", notes = "댓글 상세 정보 조회")
  public ListCommentDetailResponse listDetailComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {

    Comment comment = commentService.findById(commentId);
    return new ListCommentDetailResponse(comment.getId(), comment.getPost().getId(), comment.getUser().getNickname(),
        comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
  }

  @PatchMapping("/posts/{postId}/comments/{commentId}")
//  @ApiOperation(value = "댓글 수정 API", notes = "댓글 본문을 수정")
  public ResponseEntity<Long> updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequest request) {

    commentService.updateComment(commentId, request.getContent());

    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping("/posts/{postId}/comments/{commentId}")
//  @ApiOperation(value = "댓글 삭제 API", notes = "댓글 삭제")
  public ResponseEntity<Object> deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {

    commentService.delete(commentId);

    return ResponseEntity.noContent().build();
  }
}
