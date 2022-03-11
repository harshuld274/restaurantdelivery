package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.phantombeast.restaurantdelivery.bean.OrderBean;
import com.phantombeast.restaurantdelivery.bean.OrderBean.Status;

public class OrderDAO {
	private Connection cn;

	public OrderDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String PLACE_ORDER = "Insert into orders (customer_id, restaurant_id, order_time, delivered_time, total, discount, comments, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ORDER_BY_ID = "Select * from orders where order_id = ?";
	private static final String SELECT_ORDERS_BY_RESTAURANT = "Select * from orders where restaurant_id = ?";
	private static final String SELECT_ORDERS_BY_CUSTOMER = "Select * from orders where customer_id = ?";
//	private static final String SETTLE_ORDER_BY_ID = "Update orders Set status = ? where order_id = ?";

	public boolean placeOrder(OrderBean ob) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(PLACE_ORDER);
			ps.setInt(1, ob.getCustID());
			ps.setInt(2, ob.getRestID());
			ps.setTimestamp(3, ob.getOrderTime());
			ps.setTimestamp(4, ob.getDeliveredTime());
			ps.setFloat(5, ob.getTotal());
			ps.setFloat(6, ob.getDiscount());
			ps.setString(7, ob.getComments());
			ps.setInt(8, ob.getStatus().ordinal());
			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

//	public List<OrderBean> selectAllOrders() {
//		List<OrderBean> orders;
//
//		try {
//			Statement s = cn.createStatement();
//			ResultSet rs = s.executeQuery(SELECT_ALL_ORDERS);
//
//			while (rs.next()) {
//				
//				orders.add(ob);
//			}
//
//			s.close();
//			cn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return orders;
//	}

	public List<OrderBean> selectOrdersByRestaurant(int restID) {
		List<OrderBean> orders = null;
		PreparedStatement ps;

		try {
			ps = cn.prepareStatement(SELECT_ORDERS_BY_RESTAURANT);
			ps.setInt(1, restID);

			ResultSet rs = ps.executeQuery();
			orders = new ArrayList<OrderBean>();

			while (rs.next()) {
				int orderID = rs.getInt(1);
				int custID = rs.getInt(2);
				Timestamp orderTime = rs.getTimestamp(4);
				Timestamp deliveredTime = rs.getTimestamp(5);
				float total = rs.getFloat(6);
				float discount = rs.getFloat(7);
				int paymentID = rs.getInt(8);
				String comments = rs.getString(9);
				Status status = Status.values()[rs.getInt(10)];

				orders.add(new OrderBean(orderID, custID, restID, orderTime, deliveredTime, total, discount, paymentID,
						comments, status));
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	public OrderBean selectOrderById(int orderID) {
		OrderBean ob = null;
		PreparedStatement ps;

		try {
			ps = cn.prepareStatement(SELECT_ORDER_BY_ID);
			ps.setInt(1, orderID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int custID = rs.getInt(2);
				int restID = rs.getInt(3);
				Timestamp orderTime = rs.getTimestamp(4);
				Timestamp deliveredTime = rs.getTimestamp(5);
				float total = rs.getFloat(6);
				float discount = rs.getFloat(7);
				int paymentID = rs.getInt(8);
				String comments = rs.getString(9);
				Status status = Status.values()[rs.getInt(10)];

				ob = new OrderBean(orderID, custID, restID, orderTime, deliveredTime, total, discount, paymentID,
						comments, status);
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ob;
	}

//	public boolean settleOrderById(int id, int status) {
//		boolean success = false;
//
//		PreparedStatement ps;
//		try {
//			ps = cn.prepareStatement(SETTLE_ORDER_BY_ID);
//			ps.setString(1, status);
//			ps.setInt(2, id);
//
//			success = ps.executeUpdate() > 0;
//			ps.close();
//			cn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return success;
//	}
}
