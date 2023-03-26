package com.example.my_blog.domain.member;

import com.example.my_blog.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

  @Id @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "member")
  private List<Item> items = new ArrayList<>();
}
