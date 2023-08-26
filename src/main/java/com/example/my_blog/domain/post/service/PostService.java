package com.example.my_blog.domain.post.service;

import com.example.my_blog.domain.like.post.PostLike;
import com.example.my_blog.domain.like.post.repository.PostLikeRepository;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.repository.PostRepository;
import com.example.my_blog.domain.post.service.dto.request.UpdatePostRequest;
import com.example.my_blog.domain.user.User;
import com.example.my_blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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

    return postRepository.findById(id).orElseThrow(
        () -> new NoSuchElementException("해당 게시글이 존재하지 않습니다.")
    );
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

    if (findPost!= null) {
      findPost.setTitle(updatePostRequest.getTitle());
      findPost.setContent(updatePostRequest.getContent());
      findPost.setUpdatedAt(LocalDateTime.now());
    }
  }

  @Transactional
  public void deleteById(Long id) {

    postRepository.deleteById(id);
  }

  //== 게시글 좋아요 관련 메소드 ==//

  @Transactional
  public void likePost(Long postId, Long userId) {

    if (isAlreadyLiked(postId, userId)) { // 이미 좋아요한 게시글이라면 에러 반환
      throw new IllegalStateException("이미 좋아요한 게시글을 좋아요할 수 없습니다.");
    }

    Post post = postRepository.findById(postId).orElseThrow(
        () -> new NoSuchElementException("해당 게시글이 존재하지 않습니다.")
    );
    User user = userRepository.findById(userId).orElseThrow(
        () -> new NoSuchElementException("해당 유저가 존재하지 않습니다.")
    );

    postLikeRepository.save(PostLike.createPostLike(user, post));
  }

  @Transactional
  public void cancelLikePost(Long postId, Long userId) {

    if (!isAlreadyLiked(postId, userId)) { // 이미 좋아요한 게시글이라면 에러 반환
      throw new IllegalStateException("좋아요하지 않은 게시글의 좋아요를 취소할 수 없습니다.");
    }
    postLikeRepository.delete(postId, userId);
  }

  // 이미 좋아요한 게시글인지 판별
  public boolean isAlreadyLiked(Long postId, Long userId) {

    PostLike postLike = postLikeRepository.getPostLike(postId, userId);
    if (postLike == null) {
      return false;
    }

    return true;
  }

  public int getPostLikeCount(Long postId) {

    // Long 타입의 count 변수를 int 타입으로 변환
    return Math.toIntExact(postLikeRepository.getPostLikeCountByPostId(postId));
  }
}
