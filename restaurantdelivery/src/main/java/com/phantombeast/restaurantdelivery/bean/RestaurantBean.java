package com.phantombeast.restaurantdelivery.bean;

import java.sql.Time;
import java.sql.Timestamp;

public class RestaurantBean {
	private int restID;
	private String name, mobile, email;
	private Time openTime, closeTime;
	private int maxCancelTime, minDeliveryTime; // IN MINUTES
	private float deliveryFee;
	private Timestamp creationTime;

	public RestaurantBean(String name, String mobile, String email, Time openTime, Time closeTime, int maxCancelTime,
			int minDeliveryTime, float deliveryFee, Timestamp creationTime) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.maxCancelTime = maxCancelTime;
		this.minDeliveryTime = minDeliveryTime;
		this.deliveryFee = deliveryFee;
		this.creationTime = creationTime;
	}

	public RestaurantBean(int restID, String name, String mobile, String email, Time openTime, Time closeTime,
			int maxCancelTime, int minDeliveryTime, float deliveryFee, Timestamp creationTime) {
		super();
		this.restID = restID;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.maxCancelTime = maxCancelTime;
		this.minDeliveryTime = minDeliveryTime;
		this.deliveryFee = deliveryFee;
		this.creationTime = creationTime;
	}

	public int getRestID() {
		return restID;
	}

	public void setRestID(int restID) {
		this.restID = restID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	public int getMaxCancelTime() {
		return maxCancelTime;
	}

	public void setMaxCancelTime(int maxCancelTime) {
		this.maxCancelTime = maxCancelTime;
	}

	public int getMinDeliveryTime() {
		return minDeliveryTime;
	}

	public void setMinDeliveryTime(int minDeliveryTime) {
		this.minDeliveryTime = minDeliveryTime;
	}

	public float getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(float deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

}
