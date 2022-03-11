package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.phantombeast.restaurantdelivery.bean.CartBean;
import com.phantombeast.restaurantdelivery.bean.ItemBean;
import com.phantombeast.restaurantdelivery.bean.OrderBean;
import com.phantombeast.restaurantdelivery.bean.OrderBean.Status;

public class CartDAO {
	private Connection cn;

	public CartDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String ADD_ITEM_TO_CART = "Insert into carts (customer_id, order_id, item_id, buying_price, item_quantity, status) values (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ITEM_QUANTITY = "Update carts Set item_quantity = ? where customer_id = ? and order_id = ? and item_id = ?";
	private static final String DELETE_ITEM_FROM_CART = "Delete from carts where customer_id = ? and order_id = ? and item_id = ?";
	private static final String EMPTY_CUSTOMER_CART = "Delete from carts where customer_id = ? and status = 0";
	private static final String SELECT_CUSTOMER_CART = "Select * from carts where customer_id = ? and status = 0";
	private static final String SELECT_PENDING_ORDERS = "Select * from orders INNER JOIN on carts where carts.customer_id = orders.customer_id and carts.order_id = orders.order_id and carts.status = 1";
	private static final String SELECT_PREVIOUS_ORDERS = "Select * from orders INNER JOIN on carts where carts.customer_id = orders.customer_id and carts.order_id = orders.order_id and carts.status = 2";
	private static final String SELECT_CANCELLED_ORDERS = "Select * from orders INNER JOIN on carts where carts.customer_id = orders.customer_id and carts.order_id = orders.order_id and carts.status = 3";
	private static final String PLACE_CART_ORDER = "Update carts Set status = 1 where customer_id = ? and order_id = ? and status = 0";

	public boolean addItem(CartBean cb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(ADD_ITEM_TO_CART);
			ps.setInt(1, cb.getCustID());
			ps.setInt(2, cb.getOrderID());
			ps.setInt(3, cb.getItemID());
			ps.setFloat(4, cb.getBuyingPrice());
			ps.setInt(5, cb.getQuantity());
			ps.setInt(6, cb.getStatus().ordinal());

			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean updateItemQuantity(CartBean cb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(UPDATE_ITEM_QUANTITY);
			ps.setInt(1, cb.getCustID());
			ps.setInt(2, cb.getOrderID());
			ps.setInt(3, cb.getItemID());
			ps.setFloat(4, cb.getBuyingPrice());
			ps.setInt(5, cb.getQuantity());
			ps.setInt(6, cb.getStatus().ordinal());

			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean deleteItem(CartBean cb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(DELETE_ITEM_FROM_CART);
			ps.setInt(1, cb.getCustID());
			ps.setInt(2, cb.getOrderID());
			ps.setInt(3, cb.getItemID());

			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public List<ItemBean> selectCustomerCart(int custID) {
		List<ItemBean> items = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_CUSTOMER_CART);
			ps.setInt(1, custID);

			ResultSet rs = ps.executeQuery();
			items = new ArrayList<ItemBean>();

			while (rs.next()) {
				int itemID = rs.getInt(1);
				int restID = rs.getInt(2);
				int categoryID = rs.getInt(3);
				String name = rs.getString(4);
				float price = rs.getFloat(5);
				int stock = rs.getInt(6);
				int totalUsersRated = rs.getInt(7);
				double avgRating = rs.getInt(8);
				String image = rs.getString(9);

				items.add(new ItemBean(itemID, restID, categoryID, name, price, stock, totalUsersRated, avgRating,
						image));
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public boolean emptyCustomerCart(int custID) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(EMPTY_CUSTOMER_CART);
			ps.setInt(1, custID);

			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public List<OrderBean> selectPendingOrders(int custID) {
		List<OrderBean> orders = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_PENDING_ORDERS);
			ps.setInt(1, custID);

			ResultSet rs = ps.executeQuery();
			orders = new ArrayList<OrderBean>();

			while (rs.next()) {
				int orderID = rs.getInt(1);
				int restID = rs.getInt(3);
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

	public List<OrderBean> selectPreviousOrders(int custID) {
		List<OrderBean> orders = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_PREVIOUS_ORDERS);
			ps.setInt(1, custID);

			ResultSet rs = ps.executeQuery();
			orders = new ArrayList<OrderBean>();

			while (rs.next()) {
				int orderID = rs.getInt(1);
				int restID = rs.getInt(3);
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

	public List<OrderBean> selectCancelledOrders(int custID) {
		List<OrderBean> orders = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_CANCELLED_ORDERS);
			ps.setInt(1, custID);

			ResultSet rs = ps.executeQuery();
			orders = new ArrayList<OrderBean>();

			while (rs.next()) {
				int orderID = rs.getInt(1);
				int restID = rs.getInt(3);
				Timestamp orderTime = rs.getTimestamp(4);
				Timestamp deliveredTime = rs.getTimestamp(5);
				float total = rs.getFloat(6);
				float discount = rs.getFloat(7);
				int paymentID = rs.getInt(8);
				String comments = rs.getString(9);
				Status status = Status.values()[rs.getInt(10)];

				orders.add(new OrderBean(orderID, restID, custID, orderTime, deliveredTime, total, discount, paymentID,
						comments, status));
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}
}
