package com.example.my_blog.domain.post;

import com.example.my_blog.common.BaseEntity;
import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.like.post.PostLike;
import com.example.my_blog.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="post_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Lob
  @Column(columnDefinition = "LONGTEXT")
  private String title;

  @Lob
  @Column(columnDefinition = "LONGTEXT")
  private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<PostLike> likes = new ArrayList<>();

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();


  //==연관관계 편의 메소드==//
  public void setUser(User user) {

    this.user = user;
    user.getPosts().add(this);
  }

  //==생성 메소드==//
  public static Post createPost(User user, String title, String content) {

    Post post = new Post();

    post.setUser(user);
    post.setTitle(title);
    post.setContent(content);

    return post;
  }
}
