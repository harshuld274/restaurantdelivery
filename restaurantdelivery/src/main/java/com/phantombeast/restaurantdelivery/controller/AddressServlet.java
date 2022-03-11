package com.phantombeast.restaurantdelivery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phantombeast.restaurantdelivery.bean.AddressBean;
import com.phantombeast.restaurantdelivery.bean.AddressBean.Type;
import com.phantombeast.restaurantdelivery.bean.CustomerBean;
import com.phantombeast.restaurantdelivery.dao.AddressDAO;
import com.phantombeast.restaurantdelivery.dao.ConnectionProvider;
import com.phantombeast.restaurantdelivery.dao.CustomerDAO;

public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddressServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/addContact":
			addContactDetails(request, response);
			break;

		}
	}

	private void addContactDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String streetname = request.getParameter("streetname");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String state = request.getParameter("state");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		
		String error = validateContactDetails(streetname, city, district,state, pincode, email, mobile);
		HttpSession session = request.getSession();
		if (error == null) {
			CustomerDAO customerDAO = new CustomerDAO(ConnectionProvider.getConnection());
			AddressDAO addressDAO = new AddressDAO(ConnectionProvider.getConnection());
			
			CustomerBean customer = (CustomerBean) session.getAttribute("customer");
			customer.setEmail(email);
			customer.setMobile(mobile);
			boolean contactSuccess = customerDAO.updateCustomer(customer);
			
			boolean addressSuccess = addressDAO
					.addAddress(new AddressBean(customer.getCustID(), streetname, city, district, state, pincode, Type.valueOf("CUST_PRIMARY")));

			if (contactSuccess && addressSuccess) {
				System.out.println("Contact Details updated");
				session.setAttribute("contactUpdateSuccess", "Contact updated successfully");
				response.sendRedirect("contact_details.jsp");
			} else {
				session.setAttribute("contactUpdateFail", "Error on server");
				System.out.println("Contact Details Fail");
				response.sendRedirect("contact_details.jsp");
			}

		} else {
			session.setAttribute("contactUpdateFail", error);
			System.out.println("Contact Details Error");
			response.sendRedirect("contact_details.jsp");
		}

	}

	public String validateContactDetails(String streetname, String city, String district, String state, int pincode,
			String email, String mobile) {
		String error = null;
		if (streetname.length() < 0) {
			error = "Streetname cannot be empty";
		} else if (city.length() == 0) {
			error = "City cannot be empty";
		} else if (district.length() == 0) {
			error = "District cannot be empty";
		} else if (state.length() == 0) {
			error = "State cannot be empty";
		} else if (pincode < 0 && pincode > 999999) {
			error = "Pincode cannot be empty";
		}	else if (mobile.length() == 0) {
			error = "Mobile Number cannot be empty";
		} else if (mobile.length() != 10) {
			error = "Mobile Number must be 10 characters long";
		} else {
			try {
				Long.parseLong(mobile);
			} catch (NumberFormatException e) {
				error = "Mobile Number is invalid";
			}
		}
		return error;
	}

}
