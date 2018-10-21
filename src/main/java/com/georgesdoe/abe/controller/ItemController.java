package com.georgesdoe.abe.controller;

import com.georgesdoe.abe.domain.Item;
import com.georgesdoe.abe.exception.ResourceNotFoundException;
import com.georgesdoe.abe.repository.ItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemRepository repository;

    public ItemController(ItemRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Item> index(@SortDefault("name") Pageable request){
        return repository.findAll(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@Valid @RequestBody Item item) {
        repository.save(item);
        return item;
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable("id") Long id,@Valid @RequestBody Item itemPdo){
        Item item = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(Item.class));
        itemPdo.setId(id);
        repository.save(itemPdo);
        return item;
    }
}
