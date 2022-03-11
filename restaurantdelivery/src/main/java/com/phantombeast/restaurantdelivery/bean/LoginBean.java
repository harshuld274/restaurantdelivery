package com.phantombeast.restaurantdelivery.bean;

public class LoginBean {
	public enum Role {
		ADMIN, CUSTOMER, RESTAURANT
	}

	private String username, password;
	private int id;
	private Role role;

	public LoginBean(String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public LoginBean(int id, String username, String password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void getUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
