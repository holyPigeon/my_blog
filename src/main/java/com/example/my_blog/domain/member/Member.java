package com.example.my_blog.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

  @Id @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String name;
}
