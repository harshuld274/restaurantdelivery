package com.phantombeast.restaurantdelivery.bean;

public class CategoryBean {
	private int catID;
	private String name, description, image;

	public CategoryBean(String name, String description, String image) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
	}

	public CategoryBean(int catID, String name, String description, String image) {
		super();
		this.catID = catID;
		this.name = name;
		this.description = description;
		this.image = image;
	}

	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
