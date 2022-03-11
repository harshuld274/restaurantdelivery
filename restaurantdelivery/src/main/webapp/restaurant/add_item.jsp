<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Item</title>
<%@include file="../components/all_css.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<section class="vh-80" style="background-color: #c6bec7;">
		<div
			class="row d-flex mx-auto justify-content-center align-items-center h-100 py-5">
			<div class="card w-50" style="border-radius: 1rem;">
				<div class="row g-0">
					<div class="col d-flex align-items-center">
						<div class="card-body p-4 p-lg-5 text-black">
							<%
							String itemAddSuccess = (String) session.getAttribute("itemAddSuccess");
							if (itemAddSuccess != null) {
							%>

							<p class="h4 fw-bold text-success"><%=itemAddSuccess%></p>
							<%
							session.removeAttribute("itemAddSuccess");
							}
							%>
							<div class="d-flex align-items-center mb-3 pb-1">
								<i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
								<span class="h1 fw-bold mb-0">Zula</span>
							</div>
							<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Add
								Your Item</h5>
							<form class="ms-5" action="addItem" method="post"
								enctype="multipart/form-data">
								<div class="col-md-10 mb-4">
									<div class="form-outline">
										<input type="text" name="restName"
											value="<%=restaurant.getName()%>"
											class="form-control form-control-lg" disabled />
									</div>
								</div>
								<div class="col-md-10 mb-4">
									<div class="form-outline">
										<input type="hidden" name="restID"
											value="<%=restaurant.getRestID()%>"
											class="form-control form-control-lg" required />
									</div>
								</div>

								<div class="col-md-10 mb-4">
									<div class="form-outline">
										<input type="text" name="name" placeholder="Item Name"
											class="form-control form-control-lg" required />
									</div>
								</div>

								<div class="col-md-10 mb-4">

									<div class="form-outline">
										<input type="number" step="0.50" min="1" name="price"
											placeholder="Item Price" class="form-control form-control-lg"
											required />
									</div>

								</div>

								<div class="col-md-10 mb-4 d-flex align-items-center">

									<div class="form-outline">
										<input type="number" min="1" name="stock"
											placeholder="Item Stock" class="form-control form-control-lg"
											required />
									</div>

								</div>

								<div class="col-md-8 mb-4">
									<select class="select form-control-lg" name="catName" required>
										<option value="Starters">Starters</option>
										<option value="Main Course">Main Course</option>
										<option value="Desserts">Desserts</option>
									</select>
								</div>

								<div class="form-group mb-4">
									<label for="img">Item image: </label> <input type="file"
										class="form-control-file" id="img" name="image" required>
								</div>
								<div class="mt-4 pt-2 text-center">
									<input class="btn btn-primary btn-lg " type="submit"
										value="Submit" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@include file="../components/footer.jsp"%>
</body>
</html>