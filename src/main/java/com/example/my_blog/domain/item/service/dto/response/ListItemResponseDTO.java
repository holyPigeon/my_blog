package com.example.my_blog.domain.item.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListItemResponseDTO {

  private Long id;
  private String name;
  private int price;
  private int quantity;
}
