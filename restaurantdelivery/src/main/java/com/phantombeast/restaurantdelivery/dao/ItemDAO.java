package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.phantombeast.restaurantdelivery.bean.ItemBean;

public class ItemDAO {
	private Connection cn;

	public ItemDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String ADD_ITEM = "Insert into items (restaurant_id, category_id, name, price, stock, total_users, avg_rating, image) values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ITEM = "Update items Set name = ?, price = ?, stock = ?, image = ? where item_id = ?";
	private static final String SELECT_ITEM_BY_ID = "Select * from items where item_id = ?";
//	private static final String SELECT_ITEM_BY_NAME = "Select * from items where name = ?";
	private static final String SEARCH_ITEM_BY_NAME = "Select * from items where FREETEXT (name, ?)";
//	private static final String SEARCH_ITEM_BY_CATERGORY = "Select * from items where FREETEXT (name, ?)";
	private static final String SELECT_ALL_ITEMS = "Select * from items";
	private static final String DELETE_ITEM_BY_ID = "Delete from items where item_id = ?";

	public boolean addItem(ItemBean ib) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(ADD_ITEM);
			ps.setInt(1, ib.getRestId());
			ps.setInt(2, ib.getCatID());
			ps.setString(3, ib.getName());
			ps.setFloat(4, ib.getPrice());
			ps.setInt(5, ib.getStock());
			ps.setInt(6, ib.getTotalUsersRated());
			ps.setDouble(7, ib.getAvgRating());
			ps.setString(8, ib.getImage());

			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean updateItem(ItemBean ib) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(UPDATE_ITEM);
			ps.setString(1, ib.getName());
			ps.setFloat(2, ib.getPrice());
			ps.setInt(3, ib.getStock());
			ps.setInt(6, ib.getItemID());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public ItemBean selectItemById(int id) {
		ItemBean item = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_ITEM_BY_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int restID = rs.getInt(2);
				int catID = rs.getInt(3);
				String name = rs.getString(4);
				float price = rs.getFloat(5);
				int stock = rs.getInt(6);
				int totalUserRated = rs.getInt(7);
				double avgRating = rs.getDouble(8);
				String image = rs.getString(9);

				item = new ItemBean(id, restID, catID, name, price, stock, totalUserRated, avgRating, image);
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;
	}

	public List<ItemBean> searchItemByName(String searchName) {
		List<ItemBean> items = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SEARCH_ITEM_BY_NAME);
			ps.setString(1, searchName);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				items = new ArrayList<ItemBean>();
				int itemID = rs.getInt(1);
				int restID = rs.getInt(2);
				int catID = rs.getInt(3);
				String name = rs.getString(4);
				float price = rs.getFloat(5);
				int stock = rs.getInt(6);
				int totalUserRated = rs.getInt(7);
				double avgRating = rs.getDouble(8);
				String image = rs.getString(9);

				items.add(new ItemBean(itemID, restID, catID, name, price, stock, totalUserRated, avgRating, image));
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public List<ItemBean> selectAllItems() {
		List<ItemBean> items = null;

		Statement s;
		try {
			s = cn.createStatement();

			ResultSet rs = s.executeQuery(SELECT_ALL_ITEMS);
			items = new ArrayList<ItemBean>();
			while (rs.next()) {
				int itemID = rs.getInt(1);
				int restID = rs.getInt(2);
				int catID = rs.getInt(3);
				String name = rs.getString(4);
				float price = rs.getFloat(5);
				int stock = rs.getInt(6);
				int totalUserRated = rs.getInt(7);
				double avgRating = rs.getDouble(8);
				String image = rs.getString(9);

				items.add(new ItemBean(itemID, restID, catID, name, price, stock, totalUserRated, avgRating, image));
			}

			s.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public boolean deleteItem(int id) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(DELETE_ITEM_BY_ID);
			ps.setInt(1, id);
			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}
}
