package com.example.my_blog.domain.item.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ItemEditRequestDTO {

  private String name;
  private int price;
  private int quantity;
}
