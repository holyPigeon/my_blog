package com.example.my_blog.domain.item.repository;

import com.example.my_blog.domain.item.Item;

import java.util.List;

public interface ItemRepository {

  void save(Item item);

  Item findById(Long id);

  Item findByName(String name);

  List<Item> findByMemberId(Long memberId);

  List<Item> findAll();

  void deleteById(Long id);
}
