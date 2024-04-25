package com.fitmegut.dciwarehousefinalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long itemId;

	@Column(name = "itme_name", length = 60)
	private String itemName;

	@Column(name = "itme_brand", length = 60)
	private String itemBrand;

	@Column(name = "size", length = 10)
	private String size;

	@Column(name = "color", length = 30)
	private String color;

	@Column(name = "itme_condition", length = 60)
	private String itemCondition;

	private String description;

	@Column(name = "image", length = 60)
	private String image;

	@ManyToOne
	@JoinColumn(name = "wardrobe_id", nullable = false)
	private Wardrobe wardrobe;

	public Item() {
	}

	public Item(String itemName, String itemBrand, String size, String color, String itemCondition, String description, String image) {
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

	public Wardrobe getWardrobe() {
		return wardrobe;
	}

	public void setWardrobe(Wardrobe wardrobe) {
		this.wardrobe = wardrobe;
	}
}
