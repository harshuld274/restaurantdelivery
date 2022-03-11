package com.phantombeast.restaurantdelivery.bean;

import java.sql.Timestamp;

public class OrderBean {
	public enum Status {
		PENDING, DELIVERED, CANCELLED, REJECTED
	}

	private int orderID, custID, restID, paymentID;
	private Timestamp orderTime, deliveredTime;
	private float total, discount;
	private String comments;
	private Status status;

	public OrderBean(int orderID, int custID, int restID, Timestamp orderTime, Timestamp deliveredTime, float total,
			float discount, int paymentID, String comments, Status status) {
		super();
		this.orderID = orderID;
		this.custID = custID;
		this.restID = restID;
		this.paymentID = paymentID;
		this.orderTime = orderTime;
		this.deliveredTime = deliveredTime;
		this.total = total;
		this.discount = discount;
		this.comments = comments;
		this.status = status;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public int getRestID() {
		return restID;
	}

	public void setRestID(int restID) {
		this.restID = restID;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Timestamp getDeliveredTime() {
		return deliveredTime;
	}

	public void setDeliveredTime(Timestamp deliveredTime) {
		this.deliveredTime = deliveredTime;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
