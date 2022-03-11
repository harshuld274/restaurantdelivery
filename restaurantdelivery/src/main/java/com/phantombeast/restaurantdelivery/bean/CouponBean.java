package com.phantombeast.restaurantdelivery.bean;

public class CouponBean {
	public enum Status {
		ACTIVE, INACTIVE
	}

	private int couponID, restID, maxDiscPercent, maxDiscAmnt;
	private String promocode;
	private Status status;

	public CouponBean(int couponID, int restID, int maxDiscPercent, int maxDiscAmnt, String promocode, Status status) {
		super();
		this.couponID = couponID;
		this.restID = restID;
		this.maxDiscPercent = maxDiscPercent;
		this.maxDiscAmnt = maxDiscAmnt;
		this.promocode = promocode;
		this.status = status;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	public int getRestID() {
		return restID;
	}

	public void setRestID(int restID) {
		this.restID = restID;
	}

	public int getMaxDiscPercent() {
		return maxDiscPercent;
	}

	public void setMaxDiscPercent(int maxDiscPercent) {
		this.maxDiscPercent = maxDiscPercent;
	}

	public int getMaxDiscAmnt() {
		return maxDiscAmnt;
	}

	public void setMaxDiscAmnt(int maxDiscAmnt) {
		this.maxDiscAmnt = maxDiscAmnt;
	}

	public String getPromocode() {
		return promocode;
	}

	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
