package com.fitmegut.dciwarehousefinalproject.web.dto;

public class WardrobeManagerDto {

    private WardrobeDto wardrobe = new WardrobeDto();
    private ItemDto itemDto = new ItemDto();

    public WardrobeDto getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(WardrobeDto wardrobe) {
        this.wardrobe = wardrobe;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }
}
