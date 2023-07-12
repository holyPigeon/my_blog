package com.example.my_blog.domain.like.post;

import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.user.User;
import jakarta.persistence.*;

@Entity
public class PostLike {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  public static PostLike createPostLike(User user, Post post) {
    PostLike postLike = new PostLike();

    postLike.user = user;
    postLike.post = post;
    post.getLikes().add(postLike);

    return postLike;
  }

}
