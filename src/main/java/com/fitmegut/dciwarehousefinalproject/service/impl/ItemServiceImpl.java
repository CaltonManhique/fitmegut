package com.fitmegut.dciwarehousefinalproject.service.impl;

import com.fitmegut.dciwarehousefinalproject.model.Item;
import com.fitmegut.dciwarehousefinalproject.repository.ItemRepository;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.ItemServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemServiceInterface {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void save(ItemDto itemDto) {
        Item item = new Item(itemDto.getItemName(), itemDto.getItemBrand(), itemDto.getSize(), itemDto.getColor(),
                itemDto.getItemCondition(), itemDto.getDescription(), itemDto.getImage());

        itemRepository.save(item);
    }

    @Override
    public void deleteItemById(Long id) {

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            itemRepository.deleteById(id);
        }else{
            throw new RuntimeException("Item not found or already deleted with id: " + id );
        }
    }
}
