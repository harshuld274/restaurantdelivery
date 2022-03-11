package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.phantombeast.restaurantdelivery.bean.AddressBean;

public class AddressDAO {
	private Connection cn;

	public AddressDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String ADD_ADDRESS = "Insert into addresses (streetname, city, district, state, pincode, type, id) values (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ADDRESS = "Update addresses Set streetname = ?, city = ?, district = ?, state = ?, pincode = ? where id = ? and type = ?";
	private static final String SELECT_ADDRESS_BY_ID_AND_TYPE = "Select * from addresses where id = ? and type = ?";

	public boolean addAddress(AddressBean ab) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(ADD_ADDRESS);
			ps.setString(1, ab.getStreetName());
			ps.setString(2, ab.getCity());
			ps.setString(3, ab.getDistrict());
			ps.setString(4, ab.getState());
			ps.setInt(5, ab.getPincode());
			ps.setInt(6, ab.getType().ordinal());
			ps.setInt(7, ab.getId());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public boolean updateAddress(AddressBean ab) {
		boolean success = false;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(UPDATE_ADDRESS);
			ps.setString(1, ab.getStreetName());
			ps.setString(2, ab.getCity());
			ps.setString(3, ab.getDistrict());
			ps.setString(4, ab.getState());
			ps.setInt(5, ab.getPincode());
			ps.setInt(6, ab.getId());
			ps.setInt(7, ab.getType().ordinal());

			success = ps.executeUpdate() > 0;
			System.out.println(success);
			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	public AddressBean selectAddressByIdAndType(int id, AddressBean.Type type) {
		AddressBean address = null;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(SELECT_ADDRESS_BY_ID_AND_TYPE);
			ps.setInt(1, id);
			ps.setInt(2, type.ordinal());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String streetname = rs.getString(3);
				String city = rs.getString(4);
				String district = rs.getString(5);
				String state = rs.getString(6);
				int pincode = rs.getInt(7);

				address = new AddressBean(id, streetname, city, district, state, pincode, type);
			}

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return address;
	}

}
