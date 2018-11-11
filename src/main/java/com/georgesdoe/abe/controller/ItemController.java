package com.georgesdoe.abe.controller;

import com.georgesdoe.abe.domain.Item;
import com.georgesdoe.abe.dto.ItemDto;
import com.georgesdoe.abe.service.ItemService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Item> index(@SortDefault("name") Pageable request){
        return service.index(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@Valid @RequestBody ItemDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable("id") Long id,@Valid @RequestBody ItemDto dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id){
        service.remove(id);
    }
}
