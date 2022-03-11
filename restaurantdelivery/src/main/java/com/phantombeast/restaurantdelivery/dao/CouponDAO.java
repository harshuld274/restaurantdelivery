package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;

public class CouponDAO {
	private Connection cn;

	public CouponDAO(Connection cn) {
		super();
		this.cn = cn;
	}

	private static final String ADD_COUPON = "Insert into coupons () values (?, ?, ?)";
	private static final String UPDATE_COUPON_DETAILS = "Update coupons Set max_discount_percentage = ?, max_discount_amount where coupon_id= ?";
	private static final String SELECT_COUPON_BY_ID = "Select * from coupons where coupon_id= ?";
	private static final String SELECT_COUPONS_BY_RESTAURANT = "Select * from coupons";

}
