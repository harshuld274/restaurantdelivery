package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.phantombeast.restaurantdelivery.bean.CategoryBean;

public class CategoryDAO {
	private Connection cn;

	public CategoryDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String ADD_CATEGORY = "Insert into categories (name, description, image) values (?, ?, ?)";
	private static final String UPDATE_CATERGORY = "Update categories Set name = ?, description = ?, image = ? where catergory_id = ?";
	private static final String SELECT_CATEGORY_BY_ID = "Select * from categories where category_id = ?";
	private static final String SELECT_CATEGORY_BY_NAME = "Select * from categories where name = ?";
	private static final String SEARCH_CATEGORY_BY_NAME = "Select * from categories where FREETEXT (name, ?)";
	private static final String SELECT_ALL_CATEGORIES = "Select * from categories";

	public boolean addCategory(CategoryBean cb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(ADD_CATEGORY);
			ps.setString(1, cb.getName());
			ps.setString(2, cb.getDescription());
			ps.setString(3, cb.getImage());

			success = ps.executeUpdate() > 0;

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean updateCategory(CategoryBean cb) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(UPDATE_CATERGORY);
			ps.setString(1, cb.getName());
			ps.setString(2, cb.getDescription());
			ps.setString(3, cb.getImage());
			ps.setInt(4, cb.getCatID());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public CategoryBean selectCategoryById(int id) {
		CategoryBean category = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_CATEGORY_BY_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString(2);
				String description = rs.getString(3);
				String image = rs.getString(4);

				category = new CategoryBean(id, name, description, image);
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}

	public CategoryBean selectCategoryByName(String name) {
		CategoryBean category = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_CATEGORY_BY_NAME);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String description = rs.getString(3);
				String image = rs.getString(4);

				category = new CategoryBean(id, name, description, image);
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return category;
	}

	public List<CategoryBean> searchCategoryByName(String searchName) {
		List<CategoryBean> categories = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SEARCH_CATEGORY_BY_NAME);
			ps.setString(1, searchName);

			ResultSet rs = ps.executeQuery();
			categories = new ArrayList<CategoryBean>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				String image = rs.getString(4);

				categories.add(new CategoryBean(id, name, description, image));
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public List<CategoryBean> selectAllCategories() {
		List<CategoryBean> categories = null;

		Statement s;
		try {
			s = cn.createStatement();

			ResultSet rs = s.executeQuery(SELECT_ALL_CATEGORIES);
			while (rs.next()) {
				categories = new ArrayList<CategoryBean>();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				String image = rs.getString(4);

				categories.add(new CategoryBean(id, name, description, image));
			}

			s.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}
}
