package com.example.my_blog.api;

import com.example.my_blog.domain.item.Item;
import com.example.my_blog.domain.item.service.ItemService;
import com.example.my_blog.domain.item.service.dto.response.ItemDTO;
import com.example.my_blog.domain.item.service.dto.response.ListItemResponse;
import com.example.my_blog.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

  private final MemberService memberService;
  private final ItemService itemService;

  @GetMapping("/item/list")
  public ListItemResponse<List<ItemDTO>> listItem() {
    List<Item> items = itemService.findAll();

    List<ItemDTO> collect = items.stream().map(i ->
        new ItemDTO(i.getId(), i.getName(), i.getPrice(), i.getQuantity())).toList();

    return new ListItemResponse<>(collect.size(), collect);

  }

  /*@GetMapping("/member/{memberId}/itemList")
  public List<ItemDTO> listItemByMemberId(@PathVariable Long memberId) {

    List<Item> items = itemService.findByMemberId(memberId);
//    itemList.forEach(item -> item.setMember(null));

    return null;
  }*/
}
