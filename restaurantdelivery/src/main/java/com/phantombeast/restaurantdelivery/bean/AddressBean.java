package com.phantombeast.restaurantdelivery.bean;

public class AddressBean {
	public enum Type {
		CUST_PRIMARY, CUST_DELIVERY, RESTAURANT, ORDER
	}

	private int id, pincode;
	private String streetName, city, district, state;
	private Type type;

	public AddressBean(int id, String streetName, String city, String district, String state, int pincode, Type type) {
		super();
		this.id = id;
		this.streetName = streetName;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
