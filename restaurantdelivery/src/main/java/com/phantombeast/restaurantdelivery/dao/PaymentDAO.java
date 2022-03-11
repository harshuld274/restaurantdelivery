package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.phantombeast.restaurantdelivery.bean.PaymentBean;

public class PaymentDAO {
	private Connection cn;

	public PaymentDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String GENERATE_PAYMENT_ID = "Insert into payments (payment_date, amount, mode) values (?, ?, ?)";

	public int generatePaymentID(PaymentBean pb) {
		int id = 0;

		PreparedStatement ps;
		try {
			ps = cn.prepareStatement(GENERATE_PAYMENT_ID);
			ps.setTimestamp(1, pb.getPaymentDate());
			ps.setFloat(2, pb.getAmount());
			ps.setInt(3, pb.getMode().ordinal());

			ResultSet rs = ps.executeQuery();

			ps.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
}
