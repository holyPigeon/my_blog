package com.example.my_blog.domain.item.service;

import com.example.my_blog.domain.item.Item;
import com.example.my_blog.domain.item.repository.ItemRepository;
import com.example.my_blog.domain.item.service.dto.request.ItemEditRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public Long save(Item item) {
    itemRepository.save(item);

    return item.getId();
  }

  public Item findById(Long id) {
    return itemRepository.findById(id);
  }

  public Item findByName(String name) {
    return itemRepository.findByName(name);
  }

  public List<Item> findByMemberId(Long memberId) {
    return itemRepository.findByMemberId(memberId);
  }

  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  @Transactional
  public void update(Long id, ItemEditRequestDTO itemEditRequestDTO) {
    Item findItem = findById(id);

    if (findItem!= null) {
      findItem.setName(itemEditRequestDTO.getName());
      findItem.setPrice(itemEditRequestDTO.getPrice());
      findItem.setQuantity(itemEditRequestDTO.getQuantity());
    }
  }

  public void deleteById(Long id) {
    itemRepository.deleteById(id);
  }


}
