package com.fitmegut.dciwarehousefinalproject.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemDto {

	private long itemId;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String itemName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String itemBrand;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String size;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String color;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String itemCondition; // New/...

	private String description;
	private String image;

	public ItemDto() {
	}

	public ItemDto(long itemId, String itemName, String itemBrand, String size, String color, String itemCondition,
				   String description, String image) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemBrand = itemBrand;
		this.size = size;
		this.color = color;
		this.itemCondition = itemCondition;
		this.description = description;
		this.image = image;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}