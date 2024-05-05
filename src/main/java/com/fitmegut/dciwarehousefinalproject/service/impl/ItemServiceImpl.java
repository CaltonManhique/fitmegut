package com.fitmegut.dciwarehousefinalproject.service.impl;

import com.fitmegut.dciwarehousefinalproject.model.Item;
import com.fitmegut.dciwarehousefinalproject.model.Wardrobe;
import com.fitmegut.dciwarehousefinalproject.repository.ItemRepository;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.ItemServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.ItemDto;
import com.fitmegut.dciwarehousefinalproject.web.dto.WardrobeDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemServiceInterface {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public void save(ItemDto itemDto) {

        Wardrobe wardrobe = new Wardrobe();
        wardrobe.setId(itemDto.getWardrobeDto().getId());

        Item item = new Item(itemDto.getItemName(), itemDto.getItemBrand(), itemDto.getSize(), itemDto.getColor(),
                itemDto.getItemCondition(), itemDto.getDescription(), itemDto.getImage());

        item.setWardrobe(wardrobe);

        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteItemById(Long id) {

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            itemRepository.deleteById(id);
        } else {
            throw new RuntimeException("Item not found or already deleted with id: " + id);
        }
    }

    @Override
    public ItemDto findById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();

            return new ItemDto(item.getItemId(), item.getItemName(), item.getItemBrand(), item.getSize(), item.getColor(),
                    item.getItemCondition(), item.getDescription(), item.getImage(), new WardrobeDto(
                    item.getWardrobe().getId(), item.getWardrobe().getClothingCategories(),
                    item.getWardrobe().isPosted())
            );
        } else {
            throw new RuntimeException("Item not found or already deleted with id: " + id);
        }
    }

    @Override
    public List<ItemDto> findAll() {

        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemsDto = new ArrayList<>();

        for (Item item : items) {
            itemsDto.add(new ItemDto(item.getItemId(), item.getItemName(), item.getItemBrand(), item.getSize(), item.getColor(),
                    item.getItemCondition(), item.getDescription(), item.getImage(), new WardrobeDto(
                    item.getWardrobe().getId(), item.getWardrobe().getClothingCategories(),
                    item.getWardrobe().isPosted())));
        }

        return itemsDto;
    }

    @Override
    public void update(ItemDto itemDto) {

        Item item = getItem(itemDto.getItemId());

        item.setItemName(itemDto.getItemName());
        item.setItemBrand(itemDto.getItemBrand());
        item.setSize(itemDto.getSize());
        item.setColor(itemDto.getColor());
        item.setItemCondition(itemDto.getItemCondition());
        item.setDescription(itemDto.getDescription());
        item.setImage(itemDto.getImage());

        itemRepository.save(item);
    }

    private Item getItem(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            throw new RuntimeException("Item not found or already deleted with id: " + id);
        }
    }
}
