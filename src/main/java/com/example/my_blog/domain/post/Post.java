package com.example.my_blog.domain.post;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

  @Id @GeneratedValue
  @Column(name="post_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Lob
  private String title;

  @Lob
  private String content;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments;


  //==연관관계 편의 메소드==//
  public void setMember(Member member) {
    this.member = member;
    member.getPosts().add(this);
  }

  public void setComments(Comment comment) {
    comments.add(comment);
    comment.setPost(this);
  }

  public static Post createPost(Member member, String title ,String content) {
    Post post = new Post();

    post.setMember(member);
    post.setTitle(title);
    post.setContent(content);
    post.setCreatedAt(LocalDateTime.now());
    post.setUpdatedAt(LocalDateTime.now());

    return post;
  }
}
