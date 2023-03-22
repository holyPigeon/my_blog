package com.example.my_blog.api;

import com.example.my_blog.domain.item.Item;
import com.example.my_blog.domain.item.service.ItemService;
import com.example.my_blog.domain.item.service.dto.request.RegisterItemRequestDTO;
import com.example.my_blog.domain.item.service.dto.response.ListItemResponseDTO;
import com.example.my_blog.domain.item.service.dto.response.ListItemResponse;
import com.example.my_blog.domain.item.service.dto.response.RegisterItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

  private final ItemService itemService;

  @PostMapping("/item/register")
  public RegisterItemResponseDTO registerItem(RegisterItemRequestDTO registerItemDTO) {
    Item item = new Item();

    item.setName(registerItemDTO.getName());
    item.setPrice(registerItemDTO.getPrice());
    item.setQuantity(registerItemDTO.getQuantity());

    itemService.save(item);

    return new RegisterItemResponseDTO(item.getId());

  }

  @GetMapping("/item/list")
  public ListItemResponse<List<ListItemResponseDTO>> listItem() {
    List<Item> items = itemService.findAll();

    List<ListItemResponseDTO> collect = items.stream().map(i ->
        new ListItemResponseDTO(i.getId(), i.getName(), i.getPrice(), i.getQuantity())).toList();

    return new ListItemResponse<>(collect.size(), collect);

  }

  /*@GetMapping("/member/{memberId}/itemList")
  public List<ItemDTO> listItemByMemberId(@PathVariable Long memberId) {

    List<Item> items = itemService.findByMemberId(memberId);
//    itemList.forEach(item -> item.setMember(null));

    return null;
  }*/
}
