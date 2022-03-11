package com.phantombeast.restaurantdelivery.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.phantombeast.restaurantdelivery.bean.CategoryBean;
import com.phantombeast.restaurantdelivery.dao.CategoryDAO;
import com.phantombeast.restaurantdelivery.dao.ConnectionProvider;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)

public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
		case "/restaurant/addCategory":
			addCategory(request, response);
			break;
		case "/admin/addCategory":
			addCategory(request, response);
			break;

		}
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Part filePart = request.getPart("image");
		String fileName = filePart.getSubmittedFileName();

		String error = validateCategoryDetails(name, description, filePart);
		HttpSession session = request.getSession();

		if (error == null) {
			CategoryDAO categoryDAO = new CategoryDAO(ConnectionProvider.getConnection());
			String destination = "/home/harshul-pt4991/assets/categories/" + name;
			for (Part part : request.getParts()) {
				part.write(destination);
			}
			CategoryBean cb = new CategoryBean(name, description, destination);
			
			if (categoryDAO.addCategory(cb)) {
				session.setAttribute("catAddSuccess", "Category successfully added");
				response.sendRedirect("add_category.jsp");
			} else {
				session.setAttribute("catAddFail", "Error on server");
				System.out.println("Category could not be added");
				response.sendRedirect("add_category.jsp");
			}

		} else {
			session.setAttribute("catAddFail", error);
			System.out.println("Category could not be added");
			response.sendRedirect("add_category.jsp");
		}

		
	}

	private String validateCategoryDetails(String name, String description, Part filePart) {
		// TODO Auto-generated method stub
		return null;
	}

}
