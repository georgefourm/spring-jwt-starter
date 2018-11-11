package com.georgesdoe.abe.service;

import com.georgesdoe.abe.domain.Item;
import com.georgesdoe.abe.dto.ItemDto;
import com.georgesdoe.abe.exception.ResourceNotFoundException;
import com.georgesdoe.abe.mapper.ItemMapper;
import com.georgesdoe.abe.repository.ItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private ItemRepository repository;

    private ItemMapper mapper;

    public ItemService(ItemRepository repository, ItemMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public Iterable<Item> index(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Item create(ItemDto dto) {
        Item item = mapper.itemDtoToItem(dto);
        repository.save(item);
        return item;
    }

    public Item update(Long id, ItemDto dto){
        Item original = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(Item.class));
        Item updated = mapper.updateItemFromDto(dto,original);
        repository.save(updated);
        return updated;
    }

    public void remove(Long id){
        Item item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Item.class));
        repository.delete(item);
    }
}
