package com.example.my_blog.controller;

import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.post.service.PostService;
import com.example.my_blog.domain.post.service.dto.request.CreatePostRequestDTO;
import com.example.my_blog.domain.post.service.dto.response.ListPostResponseDTO;
import com.example.my_blog.domain.post.service.dto.response.ListPostResponse;
import com.example.my_blog.domain.post.service.dto.response.CreatePostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping("/post/create")
  public CreatePostResponseDTO registerItem(@RequestBody CreatePostRequestDTO createPostRequestDTO) {

    Post post = Post.createPost(createPostRequestDTO.getMember(), createPostRequestDTO.getTitle(), createPostRequestDTO.getContent());

    return new CreatePostResponseDTO(post.getId());

  }

  @GetMapping("/post/list")
  public ListPostResponse<List<ListPostResponseDTO>> listItem() {
    List<Post> posts = postService.findAll();

    List<ListPostResponseDTO> collect = posts.stream().map(p ->
        new ListPostResponseDTO(p.getId(), p.getMember(), p.getTitle(), p.getContent())).toList();

    return new ListPostResponse<>(collect.size(), collect);

  }

  /*@GetMapping("/member/{memberId}/itemList")
  public List<ItemDTO> listItemByMemberId(@PathVariable Long memberId) {

    List<Item> items = itemService.findByMemberId(memberId);
//    itemList.forEach(item -> item.setMember(null));

    return null;
  }*/
}
