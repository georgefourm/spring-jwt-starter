package com.georgesdoe.sbjs.web.controller;

import com.georgesdoe.sbjs.domain.Item;
import com.georgesdoe.sbjs.service.ItemService;
import com.georgesdoe.sbjs.web.dto.ItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @GetMapping
    public Iterable<Item> index(@SortDefault("name") Pageable request){
        return service.index(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@Valid @RequestBody ItemDto itemDto) {
        return service.create(itemDto);
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable("id") Long id,@Valid @RequestBody ItemDto itemDto){
        return service.update(id,itemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id){
        service.remove(id);
    }
}
