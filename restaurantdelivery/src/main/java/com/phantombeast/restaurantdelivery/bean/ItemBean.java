package com.phantombeast.restaurantdelivery.bean;

public class ItemBean {
	private int itemID, restId, catID, stock, totalUsersRated;
	private String name, image;
	private float price;
	private double avgRating;

	public ItemBean(int restId, int catID, String name, float price, int stock, int totalUsersRated,
			double avgRating, String image) {
		super();
		this.restId = restId;
		this.catID = catID;
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.totalUsersRated = totalUsersRated;
		this.image = image;
		this.avgRating = avgRating;
	}
	
	public ItemBean(int itemID, int restId, int catID, String name, float price, int stock, int totalUsersRated,
			double avgRating, String image) {
		super();
		this.itemID = itemID;
		this.restId = restId;
		this.catID = catID;
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.totalUsersRated = totalUsersRated;
		this.image = image;
		this.avgRating = avgRating;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getRestId() {
		return restId;
	}

	public void setRestId(int restId) {
		this.restId = restId;
	}

	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getTotalUsersRated() {
		return totalUsersRated;
	}

	public void setTotalUsersRated(int totalUsersRated) {
		this.totalUsersRated = totalUsersRated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

}
