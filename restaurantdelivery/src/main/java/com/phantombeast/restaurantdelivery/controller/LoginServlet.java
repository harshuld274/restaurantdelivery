package com.phantombeast.restaurantdelivery.controller;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phantombeast.restaurantdelivery.bean.CustomerBean;
import com.phantombeast.restaurantdelivery.bean.LoginBean;
import com.phantombeast.restaurantdelivery.bean.LoginBean.Role;
import com.phantombeast.restaurantdelivery.bean.RestaurantBean;
import com.phantombeast.restaurantdelivery.dao.ConnectionProvider;
import com.phantombeast.restaurantdelivery.dao.CustomerDAO;
import com.phantombeast.restaurantdelivery.dao.LoginDAO;
import com.phantombeast.restaurantdelivery.dao.RestaurantDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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
		case "/login":
			login(request, response);
			break;
		case "/logout":
			logout(request, response);
			break;
		case "/signup":
			signupCustomer(request, response);
			break;
		case "/register":
			registerRestaurant(request, response);
			break;
		default:
			break;
		}

	}

	private void registerRestaurant(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String opnTime = request.getParameter("openTime");
		Time openTime = Time.valueOf(opnTime+":00");
		String clseTime = request.getParameter("closeTime");
		Time closeTime = Time.valueOf(clseTime+":00");
		int cancelTime = Integer.parseInt(request.getParameter("cancelTime"));
		int deliveryTime = Integer.parseInt(request.getParameter("deliveryTime"));
		float deliveryFee = Float.valueOf(request.getParameter("deliveryFee"));

		String streetname = request.getParameter("streetname");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String state = request.getParameter("state");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		HttpSession session = request.getSession();

		String error1 = validateInitialRestaurantDetails(name, username, password, password2, deliveryFee,
				deliveryTime);
		String error2 = new AddressServlet().validateContactDetails(streetname, city, district, state, pincode, email,
				mobile);

		if (error1 != null) {
			session.setAttribute("registerFail", error1);
			response.sendRedirect("register.jsp");
		} else if (error2 != null) {
			session.setAttribute("registerFail", error2);
			response.sendRedirect("register.jsp");
		} else {
			RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionProvider.getConnection());
			LoginDAO loginDAO = new LoginDAO(ConnectionProvider.getConnection());
			Timestamp creationTime = new Timestamp(System.currentTimeMillis());

			RestaurantBean rb = new RestaurantBean(name, mobile, email, openTime, closeTime, cancelTime, deliveryTime, deliveryFee, creationTime);
			int restID = restaurantDAO.signupRestaurant(rb);
			boolean loginSuccess = loginDAO
					.addLoginDetails(new LoginBean(restID, username, password, Role.valueOf("RESTAURANT")));

			if (restID != 0 && loginSuccess) {
				System.out.println("Restaurant signup success");
				rb.setRestID(restID);
				session.setAttribute("restaurant", rb);
				response.sendRedirect("restaurant/home.jsp");
			} else {
				session.setAttribute("registerFail", "Error on server");
				response.sendRedirect("register.jsp");
			}
		}
	}

	private String validateInitialRestaurantDetails(String name, String username, String password, String password2,
			float deliveryFee, int deliveryTime) {
		String error = null;
		LoginDAO loginDAO = new LoginDAO(ConnectionProvider.getConnection());

		if (name.length() == 0) {
			error = "Name cannot be empty";
		} else if (loginDAO.doesUsernameExist(username)) {
			error = "Username already taken. Try another";
		} else if (password.length() == 0) {
			error = "Password cannot be empty";
		} else if (password.length() < 4) {
			error = "Password length is too short. Must be atleast 4 characters long";
		} else if (!password.equals(password2)) {
			error = "Passwords don't match";
		} else if (deliveryFee < 0) {
			error = "Delivery Fee cannot be negative";
		} else if (deliveryTime < 0) {
			error = "Delivery Time cannot be less than zero";
		}

		return error;
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("customer");
		response.sendRedirect("login.jsp");
	}

	private void signupCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		HttpSession session = request.getSession();
		String error = validateInitialCustomerDetails(fname, lname, username, password, password2);

		if (error == null) {
			CustomerDAO customerDAO = new CustomerDAO(ConnectionProvider.getConnection());
			LoginDAO loginDAO = new LoginDAO(ConnectionProvider.getConnection());
			Timestamp creationTime = new Timestamp(System.currentTimeMillis());

			CustomerBean cb = new CustomerBean(fname, lname, null, null, creationTime);
			int custID = customerDAO.signupCustomer(cb);
			boolean loginSuccess = loginDAO
					.addLoginDetails(new LoginBean(custID, username, password, Role.valueOf("CUSTOMER")));

			if (custID != 0 && loginSuccess) {
				System.out.println("customer signup success");
				cb.setCustID(custID);
				session.setAttribute("customer", cb);
				response.sendRedirect("home.jsp");
			} else {
				session.setAttribute("signupFail", "Error on server");
				response.sendRedirect("signup.jsp");
			}

		} else {
			session.setAttribute("signupFail", error);
			response.sendRedirect("signup.jsp");
		}

	}

	private String validateInitialCustomerDetails(String fname, String lname, String username, String password,
			String password2) {
		String error = null;
		LoginDAO loginDAO = new LoginDAO(ConnectionProvider.getConnection());

		if (fname.length() == 0) {
			error = "First Name cannot be empty";
		} else if (lname.length() == 0) {
			error = "Last Name cannot be empty";
		} else if (loginDAO.doesUsernameExist(username)) {
			error = "Username already taken. Try another";
		} else if (password.length() == 0) {
			error = "Password cannot be empty";
		} else if (password.length() < 4) {
			error = "Password length is too short. Must be atleast 4 characters long";
		} else if (!password.equals(password2)) {
			System.out.println(password + " " + password2);
			error = "Passwords don't match";
		}

		return error;
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Role role = Role.valueOf(request.getParameter("role"));

		LoginDAO loginDAO = new LoginDAO(ConnectionProvider.getConnection());
		LoginBean lb = new LoginBean(username, password, role);
		HttpSession session = request.getSession();

		int id = loginDAO.validateLogin(lb);
		if (id != 0) {
			if (role.ordinal() == 0) {
				session.setAttribute("admin", "admin");
				System.out.println("Admin Logged in!!!");
				response.sendRedirect("admin/home.jsp");
			} else if (role.ordinal() == 1) {
				CustomerDAO customerDAO = new CustomerDAO(ConnectionProvider.getConnection());
				CustomerBean cb = customerDAO.selectCustomerById(id);

				session.setAttribute("customer", cb);
				System.out.println("Customer Logged in!!!");
				response.sendRedirect("home.jsp");
			} else if (role.ordinal() == 2) {
				RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionProvider.getConnection());
				RestaurantBean rb = restaurantDAO.selectRestauranyById(id);

				session.setAttribute("restaurant", rb);
				System.out.println("Restaurant Logged in!!!");
				response.sendRedirect("restaurant/home.jsp");
			}
		} else {
			session.setAttribute("loginFail", "Incorrect Email or Password");
			System.out.println("Log in failed!!!");
			response.sendRedirect("login.jsp");
		}
	}

}
