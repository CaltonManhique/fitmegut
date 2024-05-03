package com.fitmegut.dciwarehousefinalproject.service.interfaces;

import com.fitmegut.dciwarehousefinalproject.model.Item;
import com.fitmegut.dciwarehousefinalproject.web.dto.ItemDto;

import java.util.List;

public interface ItemServiceInterface {

    // Method for creation and modifications
    void save(ItemDto itemDto);

    void deleteItemById(Long id);

    Item findById(Long id);

    List<Item> findAll();
}
