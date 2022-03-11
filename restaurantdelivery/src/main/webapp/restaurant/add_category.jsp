<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
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
							String catAddSuccess = (String) session.getAttribute("catAddSuccess");
							if (catAddSuccess != null) {
							%>

							<p class="h4 fw-bold text-success"><%=catAddSuccess%></p>
							<%
							session.removeAttribute("catAddSuccess");
							}
							%>
							<div class="d-flex align-items-center mb-3 pb-1">
								<i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
								<span class="h1 fw-bold mb-0">Zula</span>
							</div>
							<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Add
								Your Category</h5>
							<form action="addCategory" method="post"
								enctype="multipart/form-data">

								<div class="col-md-10 mb-4">
									<div class="form-outline">
										<input type="text" name="name" placeholder="Category Name"
											class="form-control form-control-lg" required />
									</div>
								</div>

								<div class="col-md-10 mb-4">
									<div class="form-outline">
										<textarea rows="5" cols="60" name="description"
											class="form-control" placeholder="Description (in less than 100 characters)" required="required"></textarea>
									</div>
								</div>
								<div class="form-group mb-4">
									<label for="img">Category Image: </label> <input type="file"
										class="form-control-file" name="image" required>
								</div>
								<div class="mt-4 pt-2 text-center">
									<input class="btn btn-primary btn-lg" type="submit"
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