package com.phantombeast.restaurantdelivery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.phantombeast.restaurantdelivery.bean.ItemBean;
import com.phantombeast.restaurantdelivery.dao.CategoryDAO;
import com.phantombeast.restaurantdelivery.dao.ConnectionProvider;
import com.phantombeast.restaurantdelivery.dao.ItemDAO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
		case "/restaurant/addItem":
			addItem(request, response);
			break;

		}
	}

	private void addItem(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		int restID = Integer.parseInt(request.getParameter("restID"));
		String catName = request.getParameter("catName");
		Float price = Float.valueOf(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));

		Part filePart = request.getPart("image");
		String fileName = filePart.getSubmittedFileName();

		String error = validateItemDetails(name, price, stock, fileName);
		HttpSession session = request.getSession();

		if (error == null) {
			ItemDAO itemDAO = new ItemDAO(ConnectionProvider.getConnection());
			CategoryDAO categoryDAO = new CategoryDAO(ConnectionProvider.getConnection());
			int catID = categoryDAO.selectCategoryByName(catName).getCatID();
//			int restID = ((RestaurantBean) session.getAttribute("restaurant")).getRestID();

			String destination = "/home/harshul-pt4991/assets/items/" + restID + "-" + name + "-" + fileName;
			for (Part part : request.getParts()) {
				part.write(destination);
			}

			ItemBean ib = new ItemBean(restID, catID, name, price, stock, 0, 0, destination);
			if (itemDAO.addItem(ib)) {
				session.setAttribute("itemAddSuccess", "Item successfully added");
				response.sendRedirect("add_item.jsp");
			} else {
				session.setAttribute("itemAddFail", "Error on server");
				System.out.println("Item could not be added");
				response.sendRedirect("add_item.jsp");
			}

		} else {
			session.setAttribute("itemAddFail", error);
			System.out.println("Item could not be added");
			response.sendRedirect("add_item.jsp");
		}

	}

	private String validateItemDetails(String name, Float price, int stock, String fileName) {
		String error = null;

		if (name.length() == 0) {
			error = "Item Name cannot be empty";
		} else if (price < 0) {
			error = "Price cannot be negative";
		} else if (stock <= 0) {
			error = "Stock cannot be negative";
		}

		return error;
	}
}
