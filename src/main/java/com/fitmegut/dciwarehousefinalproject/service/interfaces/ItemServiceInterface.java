package com.fitmegut.dciwarehousefinalproject.service.interfaces;

import com.fitmegut.dciwarehousefinalproject.web.dto.ItemDto;

import java.util.List;

public interface ItemServiceInterface {

    // Method for creation
    void save(ItemDto itemDto);

    void deleteItemById(Long id);

    ItemDto findById(Long id);

    List<ItemDto> findAll();

    void update(ItemDto itemDto);

}
