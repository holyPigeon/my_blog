package com.example.my_blog.domain.post.service;

import com.example.my_blog.domain.like.post.PostLike;
import com.example.my_blog.domain.like.post.repository.PostLikeRepository;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.repository.PostRepository;
import com.example.my_blog.domain.post.service.dto.request.UpdatePostRequest;
import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.repository.UserRepository;
import com.example.my_blog.exception.MyBlogException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.my_blog.exception.MyBlogErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final PostLikeRepository postLikeRepository;

  @Transactional
  public Long save(Post item) {

    postRepository.save(item);

    return item.getId();
  }

  public Post findById(Long id) {

    return postRepository.findById(id)
        .orElseThrow(() -> MyBlogException.type(POST_NOT_FOUND));
  }

  public List<Post> findByUserId(Long userId) {

    return postRepository.findByUserId(userId);
  }

  public List<Post> findAll() {

    return postRepository.findAll();
  }

  @Transactional
  public void updatePost(Long id, UpdatePostRequest updatePostRequest) {

    Post findPost = findById(id);

    findPost.setTitle(updatePostRequest.getTitle());
    findPost.setContent(updatePostRequest.getContent());
  }

  @Transactional
  public void deleteById(Long id) {

    postRepository.deleteById(id);
  }

  //== 게시글 좋아요 관련 메소드 ==//

  @Transactional
  public void likePost(Long postId, Long userId) {

    if (isAlreadyLiked(postId, userId)) { // 이미 좋아요한 게시글이라면 에러 반환
      throw MyBlogException.type(ALREADY_LIKE_MARKING);
    }

    Post post = postRepository.findById(postId)
        .orElseThrow(() -> MyBlogException.type(POST_NOT_FOUND));
    User user = userRepository.findById(userId)
        .orElseThrow(() -> MyBlogException.type(USER_NOT_FOUND));

    postLikeRepository.save(PostLike.createPostLike(user, post));
  }

  @Transactional
  public void cancelLikePost(Long postId, Long userId) {

    if (!isAlreadyLiked(postId, userId)) { // 좋아요하지 않은 게시글이라면 에러 반환
      throw MyBlogException.type(ILLEGAL_LIKE_MARKING_CANCEL);
    }
    postLikeRepository.deleteByPostIdAndUserId(postId, userId);
  }

  // 이미 좋아요한 게시글인지 판별
  public boolean isAlreadyLiked(Long postId, Long userId) {

    // 해당 postId와 userId의 좋아요가 존재하는지, 아니면 존재하지 않는지(null인지) 반환
    return postLikeRepository.findByPostIdAndUserId(postId, userId).isPresent();
  }

  public int getPostLikeCount(Long postId) {

    // Long 타입의 count 변수를 int 타입으로 변환
    return Math.toIntExact(postLikeRepository.countPostLikesByPostId(postId));
  }
}
