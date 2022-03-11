package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.phantombeast.restaurantdelivery.bean.RestaurantBean;

public class RestaurantDAO {
	private Connection cn;

	public RestaurantDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String SIGNUP_RESTAURANT = "Insert into restaurants (name, mobile, email, opentime, closetime, max_cancel_time, min_delivery_time, delivery_fee, creation_time) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_RESTAURANT_DETAILS = "Update restaurants Set name = ?, mobile = ?, email = ?, opentime = ?, closetime = ?, max_cancel_time = ?, min_delivery_time = ?, delivery_fee = ?  where restaurant_id = ?";
	private static final String SELECT_RESTAURANT_BY_ID = "Select * from restaurants where restaurant_id = ?";
	private static final String SELECT_ALL_RESTAURANTS = "Select * from restaurants";

	public int signupRestaurant(RestaurantBean rb) {
		int id = 0;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SIGNUP_RESTAURANT,  Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, rb.getName());
			ps.setString(2, rb.getMobile());
			ps.setString(3, rb.getEmail());
			ps.setTime(4, rb.getOpenTime());
			ps.setTime(5, rb.getCloseTime());
			ps.setInt(6, rb.getMaxCancelTime());
			ps.setInt(7, rb.getMinDeliveryTime());
			ps.setFloat(8, rb.getDeliveryFee());
			ps.setTimestamp(9, rb.getCreationTime());

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

	public boolean updateRestaurant(RestaurantBean rb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(UPDATE_RESTAURANT_DETAILS);
			ps.setString(1, rb.getName());
			ps.setString(2, rb.getMobile());
			ps.setString(3, rb.getEmail());
			ps.setTime(4, rb.getOpenTime());
			ps.setTime(5, rb.getCloseTime());
			ps.setInt(6, rb.getMaxCancelTime());
			ps.setInt(7, rb.getMinDeliveryTime());
			ps.setFloat(8, rb.getDeliveryFee());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public RestaurantBean selectRestauranyById(int id) {
		RestaurantBean restaurant = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_RESTAURANT_BY_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString(2);
				String mobile = rs.getString(3);
				String email = rs.getString(4);
				Time openTime = rs.getTime(5);
				Time closeTime = rs.getTime(6);
				int maxCancelTime = rs.getInt(7);
				int minDeliveryTime = rs.getInt(8);
				float deliveryFee = rs.getFloat(9);
				Timestamp creationTime = rs.getTimestamp(10);

				restaurant = new RestaurantBean(id, name, mobile, email, openTime, closeTime, maxCancelTime,
						minDeliveryTime, deliveryFee, creationTime);
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurant;
	}

	public List<RestaurantBean> selectAllRestaurants() {
		List<RestaurantBean> restaurants = null;

		Statement s;
		try {
			s = cn.createStatement();

			ResultSet rs = s.executeQuery(SELECT_ALL_RESTAURANTS);
			restaurants = new ArrayList<RestaurantBean>();
			while (rs.next()) {
				int restID = rs.getInt(1);
				String name = rs.getString(2);
				String mobile = rs.getString(3);
				String email = rs.getString(4);
				Time openTime = rs.getTime(5);
				Time closeTime = rs.getTime(6);
				int maxCancelTime = rs.getInt(7);
				int minDeliveryTime = rs.getInt(7);
				float deliveryFee = rs.getFloat(8);
				Timestamp creationTime = rs.getTimestamp(9);

				restaurants.add(new RestaurantBean(restID, name, mobile, email, openTime, closeTime, maxCancelTime,
						minDeliveryTime, deliveryFee, creationTime));
			}

			s.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurants;
	}
}
