package com.phantombeast.restaurantdelivery.bean;

public class CartBean {
	public enum Status {
		CART, ORDER_PENDING, ORDER_DELIVERED
	}

	private int custID, orderID, itemID, quantity;
	private float buyingPrice;
	private Status status;

	public CartBean(int custID, int orderID, int itemID, int quantity, float buyingPrice, Status status) {
		super();
		this.custID = custID;
		this.orderID = orderID;
		this.itemID = itemID;
		this.quantity = quantity;
		this.buyingPrice = buyingPrice;
		this.status = status;
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(float buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
