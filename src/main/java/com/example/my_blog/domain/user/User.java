package com.example.my_blog.domain.user;

import com.example.my_blog.domain.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {

  @Id @GeneratedValue
  @Column(name = "user_id")
  private Long id;

  private String loginId;

  private String password;

  private String name;

  private int age;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Post> posts = new ArrayList<>();

//  @OneToMany(mappedBy = "member")
//  private List<Item> items = new ArrayList<>();
}
