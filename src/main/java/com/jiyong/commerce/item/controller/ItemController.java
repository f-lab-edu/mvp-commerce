package com.jiyong.commerce.item.controller;

import com.jiyong.commerce.item.domain.Item;
import com.jiyong.commerce.item.dto.ItemDto;
import com.jiyong.commerce.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PutMapping
    public ResponseEntity<ItemDto.Response> itemAdd(@RequestBody ItemDto.Request item) {
        Item saveItem = itemService.addItem(item.toEntity());
        log.info("saveItem = {} ", saveItem);
        return ResponseEntity.ok(new ItemDto.Response(saveItem));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto.SimpleResponse>> itemList() {
        List<ItemDto.SimpleResponse> list = itemService.getItems().parallelStream().map(ItemDto.SimpleResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{search}/search")
    public ResponseEntity<List<ItemDto.SimpleResponse>> itemSearch(@PathVariable String search) {
        List<ItemDto.SimpleResponse> searchList = itemService.searchByName(search).parallelStream().map(ItemDto.SimpleResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(searchList);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto.Response> itemDetails(@PathVariable Long itemId) {
        Item findItem = itemService.getItem(itemId);
        return ResponseEntity.ok(new ItemDto.Response(findItem));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> itemRemove(@PathVariable Long itemId) {
        itemService.removeItem(itemId);
        return ResponseEntity.ok("ok");
    }


    @PostMapping("/{itemId}")
    public ResponseEntity<ItemDto.Response> itemModify(@PathVariable Long itemId, @RequestBody ItemDto.Request newItem) {
        Item item = itemService.modifyItem(itemId, newItem.toEntity());
        return ResponseEntity.ok(new ItemDto.Response(item));
    }
}
