package com.fitmegut.dciwarehousefinalproject.web.dto;

public class WardrobeManagerDto {

    private WardrobeDto wardrobeDto = new WardrobeDto();
    private ItemDto itemDto = new ItemDto();

    public WardrobeDto getWardrobeDto() {
        return wardrobeDto;
    }

    public void setWardrobeDto(WardrobeDto wardrobeDto) {
        this.wardrobeDto = wardrobeDto;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }
}
