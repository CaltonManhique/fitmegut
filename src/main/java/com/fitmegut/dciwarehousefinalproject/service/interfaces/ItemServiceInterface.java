package com.fitmegut.dciwarehousefinalproject.service.interfaces;

import com.fitmegut.dciwarehousefinalproject.web.dto.ItemDto;

public interface ItemServiceInterface {

    // Method for creation and modifications
    void save(ItemDto itemDto);

    void deleteItemById(Long id);
}
