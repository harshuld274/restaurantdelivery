<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Zula</title>
<%@include file="../components/all_css.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container h2 fw-bold">
		Welcome
		<div class="input-group">
			<div class="form-outline">
				<input type="search" placeholder="Search" id="form1" class="form-control" />
			</div>
			<button type="button" class="btn btn-primary">
				<i class="fas fa-search"></i>
			</button>
		</div>
	</div>
		<%@include file="../components/footer.jsp"%>
</body>
</html>