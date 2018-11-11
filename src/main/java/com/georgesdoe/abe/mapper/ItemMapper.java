package com.georgesdoe.abe.mapper;

import com.georgesdoe.abe.domain.Item;
import com.georgesdoe.abe.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item itemDtoToItem(ItemDto itemDto);

    Item updateItemFromDto(ItemDto itemDto,@MappingTarget Item item);
}
