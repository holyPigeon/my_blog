package com.example.my_blog.domain.post.service;

import com.example.my_blog.domain.like.post.PostLike;
import com.example.my_blog.domain.like.post.repository.PostLikeRepository;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.repository.PostRepository;
import com.example.my_blog.domain.post.service.dto.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  @Transactional
  public Long save(Post item) {

    postRepository.save(item);

    return item.getId();
  }

  public Post findById(Long id) {

    return postRepository.findById(id);
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


  // 이미 좋아요한 게시글인지 판별
  public boolean isAlreadyLiked(Long postId, Long userId) {

    PostLike postLike = postLikeRepository.getPostLike(postId, userId);
    if (postLike == null) {
      return false;
    }

    return true;
  }
}
