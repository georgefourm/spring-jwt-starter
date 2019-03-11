package com.georgesdoe.sbjs.service;

import com.georgesdoe.sbjs.domain.Item;
import com.georgesdoe.sbjs.exception.ResourceNotFoundException;
import com.georgesdoe.sbjs.repository.ItemRepository;
import com.georgesdoe.sbjs.web.dto.ItemDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository repository;

    public ItemService(ItemRepository repository){
        this.repository = repository;
    }

    public Iterable<Item> index(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Item create(ItemDto itemDto){
        Item item = new Item();
        BeanUtils.copyProperties(itemDto,item);
        repository.save(item);
        return item;
    }

    public Item update(Long id,ItemDto itemDto){
        Item item = repository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Item.class));
        BeanUtils.copyProperties(itemDto,item);
        repository.save(item);
        return item;
    }

    public void remove(Long id){
        Item item = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Item.class));
        repository.delete(item);
    }
}
