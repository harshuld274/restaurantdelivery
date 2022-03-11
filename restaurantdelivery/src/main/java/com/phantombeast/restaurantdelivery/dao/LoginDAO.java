package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.phantombeast.restaurantdelivery.bean.LoginBean;

public class LoginDAO {
	private Connection cn;

	public LoginDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String VALIDATE_LOGIN = "Select id from login where username = ? and password = ? and role = ?";
	private static final String ADD_LOGIN_DETAILS = "Insert into login (username, password, id, role) values (?, ?, ?, ?)";
	private static final String UPDATE_LOGIN_DETAILS = "Update login Set password = ? where username = ? and id = ? and role = ?";
	private static final String CHECK_USERNAME_EXISTS = "Select * from login where username = ?";

	public boolean addLoginDetails(LoginBean lb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(ADD_LOGIN_DETAILS);
			ps.setString(1, lb.getUsername());
			ps.setString(2, lb.getPassword());
			ps.setInt(3, lb.getId());
			ps.setInt(4, lb.getRole().ordinal());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean updateLoginDetails(LoginBean lb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(UPDATE_LOGIN_DETAILS);
			ps.setString(1, lb.getUsername());
			ps.setString(2, lb.getPassword());
			ps.setInt(3, lb.getId());
			ps.setInt(4, lb.getRole().ordinal());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public int validateLogin(LoginBean lb) {
		int id = 0;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(VALIDATE_LOGIN);
			ps.setString(1, lb.getUsername());
			ps.setString(2, lb.getPassword());
			ps.setInt(3, lb.getRole().ordinal());

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				id = rs.getInt(1);

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public boolean doesUsernameExist(String username) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(CHECK_USERNAME_EXISTS);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			success = rs.next();

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
}
