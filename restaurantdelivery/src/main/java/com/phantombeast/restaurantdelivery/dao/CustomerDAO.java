package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.phantombeast.restaurantdelivery.bean.CustomerBean;

public class CustomerDAO {
	private Connection cn;

	public CustomerDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String SIGNUP_CUSTOMER = "Insert into customers (firstname, lastname, creation_time) values (?, ?, ?)";
	private static final String UPDATE_CUSTOMER_DETAILS = "Update customers Set firstname = ?, lastname = ?, mobile = ?, email = ? where customer_id = ?";
	private static final String SELECT_CUSTOMER_BY_ID = "Select * from customers where customer_id = ?";
	private static final String SELECT_ALL_CUSTOMERS = "Select * from customers";

	public int signupCustomer(CustomerBean cb) {
		int id = 0;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SIGNUP_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cb.getFname());
			ps.setString(2, cb.getLname());
			ps.setTimestamp(3, cb.getCreationTime());

			boolean success = ps.executeUpdate() > 0;
			if (success) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public boolean updateCustomer(CustomerBean cb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(UPDATE_CUSTOMER_DETAILS);
			ps.setString(1, cb.getFname());
			ps.setString(2, cb.getLname());
			ps.setString(3, cb.getMobile());
			ps.setString(4, cb.getEmail());
			ps.setInt(5, cb.getCustID());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public CustomerBean selectCustomerById(int id) {
		CustomerBean customer = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_CUSTOMER_BY_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String mobile = rs.getString(4);
				String email = rs.getString(5);
				Timestamp creationTime = rs.getTimestamp(6);

				customer = new CustomerBean(id, fname, lname, mobile, email, creationTime);
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	public List<CustomerBean> selectAllCustomers() {
		List<CustomerBean> customers = null;

		Statement s;
		try {
			s = cn.createStatement();

			ResultSet rs = s.executeQuery(SELECT_ALL_CUSTOMERS);
			customers = new ArrayList<CustomerBean>();
			while (rs.next()) {
				int custID = rs.getInt(1);
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String mobile = rs.getString(4);
				String email = rs.getString(5);
				Timestamp creationTime = rs.getTimestamp(6);

				customers.add(new CustomerBean(custID, fname, lname, mobile, email, creationTime));
			}

			s.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
	}
}
