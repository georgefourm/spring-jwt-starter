package com.georgesdoe.abe.repository;

import com.georgesdoe.abe.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long>{

    Page<Item> findAll(Pageable pageable);
}
