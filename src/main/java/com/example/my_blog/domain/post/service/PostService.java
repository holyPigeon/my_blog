package com.example.my_blog.domain.post.service;

import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.repository.PostRepository;
import com.example.my_blog.domain.post.service.dto.request.UpdatePostRequestDTO;
import lombok.RequiredArgsConstructor;
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

  public List<Post> findByMemberId(Long memberId) {
    return postRepository.findByMemberId(memberId);
  }

  public List<Post> findAll() {
    return postRepository.findAll();
  }

  @Transactional
  public void updatePost(Long id, UpdatePostRequestDTO updatePostRequestDTO) {
    Post findPost = findById(id);

    if (findPost!= null) {
      findPost.setTitle(updatePostRequestDTO.getTitle());
      findPost.setContent(updatePostRequestDTO.getContent());
      findPost.setUpdatedAt(LocalDateTime.now());
    }
  }

  @Transactional
  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }


}
