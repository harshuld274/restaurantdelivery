<%@page
	import="com.phantombeast.restaurantdelivery.dao.ConnectionProvider"%>
<%@page import="com.phantombeast.restaurantdelivery.dao.ItemDAO"%>
<%@page import="com.phantombeast.restaurantdelivery.bean.ItemBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@include file="../components/all_css.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<%
	ItemDAO itemDAO = new ItemDAO(ConnectionProvider.getConnection());
	List<ItemBean> items = itemDAO.selectAllItems();
	%>
	<div class="container py-5">
		<div class="row">
		<%
			for (ItemBean item : items) {
				System.out.println(item.getImage());
		%>
			<div class="col-lg-3 mb-4">
				<div class="card h-20">
				
					<img class="card-img-top" src="assets/items/1-Chicken%20Biriyani-chickenbiriyani.jpeg" alt="<%=item.getName()%>">

					<div class="card-body">
						<h5 class="card-title"><%=item.getName() %></h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>

						<a href="#" class="btn btn-outline-primary btn-sm"> Card link
						</a> <a href="#" class="btn btn-outline-secondary btn-sm"> <i
							class="far fa-heart"></i></a>
					</div>
				</div>
			</div>
			<%} %>
		</div>
	</div>
</body>
</html>