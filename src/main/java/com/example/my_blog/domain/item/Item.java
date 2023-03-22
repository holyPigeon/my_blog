package com.example.my_blog.domain.item;

import com.example.my_blog.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

  @Id @GeneratedValue
  @Column(name="item_id")
  private Long id;

  private String name;

  private int price;

  private int quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;


  //==연관관계 편의 메소드==//
  public void setMember(Member member) {
    this.member = member;
    member.getItems().add(this);
  }
}
