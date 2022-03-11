package com.phantombeast.restauarantdelivery.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phantombeast.restaurantdelivery.bean.RestaurantBean;

public class RestaurantFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

    public RestaurantFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		RestaurantBean restaurant = (RestaurantBean) session.getAttribute("restaurant");
		if (restaurant != null)
			chain.doFilter(request, response);
		else
			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
