package com.example.my_blog.domain.user;

import com.example.my_blog.domain.comment.Comment;
import com.example.my_blog.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String loginId;

  private String password;

  private String name;

  private String nickname;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Post> posts = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  //==생성 메소드==//
  public static User createUser(String loginId, String password, String name, String nickname) {

    User user = new User();

    user.setLoginId(loginId);
    user.setPassword(password);
    user.setName(name);
    user.setNickname(nickname);

    return user;
  }
}
