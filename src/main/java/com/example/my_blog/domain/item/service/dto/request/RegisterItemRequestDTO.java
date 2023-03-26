package com.example.my_blog.domain.item.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterItemRequestDTO {

  private String name;
  private int price;
  private int quantity;
}
