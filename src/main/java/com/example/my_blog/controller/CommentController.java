package com.example.my_blog.controller;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.comment.service.CommentService;
import com.example.my_blog.domain.comment.service.dto.request.CreateCommentRequest;
import com.example.my_blog.domain.comment.service.dto.request.CreateReplyCommentRequest;
import com.example.my_blog.domain.comment.service.dto.request.UpdateCommentRequest;
import com.example.my_blog.domain.comment.service.dto.response.CommentDetailResponse;
import com.example.my_blog.domain.comment.service.dto.response.ListCommentResponse;
import com.example.my_blog.domain.comment.service.dto.response.ReplyCommentDetailResponse;
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
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = {"http://localhost:8081"})
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
  public ListCommentResponse<List<CommentDetailResponse>> listComment(@PathVariable Long postId) {

    Post post = postService.findById(postId);

    List<Comment> parentCommentList = commentService.findAllParentCommentByPost(postId);
    AtomicInteger commentCount = new AtomicInteger(); // 일반적인 int 타입의 변수는 람다 표현식 안에서 값을 변경할 수 없으므로 AtomicInteger를 사용한다.

    // 기존의 댓글 리스트에서 부모 댓글 하나하나의 children 자리에 대댓글 리스트를 삽입하여 새로운 response DTO를 생성한다.
    List<CommentDetailResponse> adaptedCommentList = parentCommentList
        .stream()
        .map(comment -> {
          List<ReplyCommentDetailResponse> replyCommentList = comment.getChildren()
              .stream()
              .map(ReplyCommentDetailResponse::new).toList();

          commentCount.addAndGet(replyCommentList.size());

          return new CommentDetailResponse(comment, replyCommentList);
        }).toList();

    commentCount.addAndGet(adaptedCommentList.size());

    return new ListCommentResponse<>(commentCount.get(), adaptedCommentList);
  }

  @PatchMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<Long> updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequest request) {

    commentService.updateComment(commentId, request.getContent());

    return ResponseEntity.noContent().build();
  }
  
  @DeleteMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<Object> deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {

    commentService.delete(commentId);

    return ResponseEntity.noContent().build();
  }
}
