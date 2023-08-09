package com.example.my_blog.domain.comment;

import com.example.my_blog.domain.post.Post;
import com.example.my_blog.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="comment_id")
  private Long id;

  @Lob
  private String content;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Comment parent;

  @OneToMany(mappedBy = "parent", orphanRemoval = true)
  private List<Comment> childrens = new ArrayList<>();

  //==연관관계 편의 메소드==//
  public void setPost(Post post) {
    this.post = post;
    post.getComments().add(this);
  }

  public void setUser(User user) {
    this.user = user;
    user.getComments().add(this);
  }

  //==생성 메소드==//
  public static Comment createComment(User user, Post post, String content) {

    Comment comment = new Comment();

    comment.setUser(user);
    comment.setPost(post);
    comment.setContent(content);
    comment.setCreatedAt(LocalDateTime.now());
    comment.setUpdatedAt(LocalDateTime.now());

    return comment;
  }
}
