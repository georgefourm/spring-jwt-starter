package com.georgesdoe.abe.controller;

import com.georgesdoe.abe.domain.Item;
import com.georgesdoe.abe.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemRepository repository;

    public ItemController(ItemRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        repository.save(item);
        return item;
    }
}
