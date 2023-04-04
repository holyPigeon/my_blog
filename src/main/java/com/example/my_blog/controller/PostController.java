package com.example.my_blog.controller;

import com.example.my_blog.domain.member.Member;
import com.example.my_blog.domain.member.service.MemberService;
import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.service.PostService;
import com.example.my_blog.domain.post.service.dto.request.CreatePostRequestDTO;
import com.example.my_blog.domain.post.service.dto.response.PostResponseDTO;
import com.example.my_blog.domain.post.service.dto.response.ListPostResponse;
import com.example.my_blog.domain.post.service.dto.response.CreatePostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final MemberService memberService;

  /**
   * 게시글 작성
   */
  @PostMapping("/post/create")
  public CreatePostResponseDTO registerItem(@RequestBody CreatePostRequestDTO createPostRequestDTO) {

    Member findMember = memberService.findByName(createPostRequestDTO.getAuthor());
    Post post = Post.createPost(findMember, createPostRequestDTO.getTitle(), createPostRequestDTO.getContent());
    Long postId = postService.save(post);

    return new CreatePostResponseDTO(postId);

  }

  /**
   * 게시글 조회
   */
  @GetMapping("/post/list")
  public ListPostResponse<List<PostResponseDTO>> listItem() {
    List<Post> posts = postService.findAll();

    List<PostResponseDTO> collect = posts.stream().map(p ->
        new PostResponseDTO(p.getId(), p.getMember().getName(), p.getTitle(), p.getContent(),
            p.getCreatedAt(), p.getUpdatedAt())).toList();

    return new ListPostResponse<>(collect.size(), collect);

  }

  /**
   * 게시글 상세 조회
   */
  @GetMapping("/post/list/{postId}")
  public PostResponseDTO listItemByPostId(@PathVariable Long postId) {

    Post findPost = postService.findById(postId);

    return new PostResponseDTO(findPost.getId(), findPost.getMember().getName(), findPost.getTitle(),
        findPost.getContent(), findPost.getCreatedAt(), findPost.getUpdatedAt());
}



  /*@GetMapping("/member/{memberId}/itemList")
  public List<ItemDTO> listItemByMemberId(@PathVariable Long memberId) {

    List<Item> items = itemService.findByMemberId(memberId);
//    itemList.forEach(item -> item.setMember(null));

    return null;
  }*/
}
