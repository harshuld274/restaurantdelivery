package com.phantombeast.restaurantdelivery.bean;

import java.sql.Timestamp;

public class CustomerBean {
	private int custID;
	private String fname, lname, mobile, email;
	private Timestamp creationTime;

	public CustomerBean(String fname, String lname, String mobile, String email, Timestamp creationTime) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.email = email;
		this.creationTime = creationTime;
	}

	public CustomerBean(int custID, String fname, String lname, String mobile, String email, Timestamp creationTime) {
		super();
		this.custID = custID;
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.email = email;
		this.creationTime = creationTime;
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
}
