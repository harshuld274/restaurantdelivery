package com.phantombeast.restaurantdelivery.bean;

import java.sql.Timestamp;

public class PaymentBean {
	public enum Mode {
		DEBIT_CARD, NETBANKING, CASH_ON_DELIVERY
	}

	private int paymentID;
	private Timestamp paymentDate;
	private float amount;
	private Mode mode;

	public PaymentBean(int paymentID, Timestamp paymentDate, float amount, Mode mode) {
		super();
		this.paymentID = paymentID;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.mode = mode;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

}
