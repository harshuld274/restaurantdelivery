<%@page
	import="com.phantombeast.restaurantdelivery.bean.AddressBean.Type"%>
<%@page
	import="com.phantombeast.restaurantdelivery.dao.ConnectionProvider"%>
<%@page import="com.phantombeast.restaurantdelivery.dao.AddressDAO"%>
<%@page import="com.phantombeast.restaurantdelivery.bean.AddressBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Details</title>
<%@include file="../components/all_css.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<section class="vh-120" style="background-color: #c6bec7;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-xl-10">
					<div class="card" style="border-radius: 1rem;">
						<div class="row g-0">
							<div class="col-md-6 col-lg-7 d-flex align-items-center">
								<div class="card-body p-4 p-lg-5 text-black">
									<div class="d-flex align-items-center mb-3 pb-1">
										<i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
										<span class="h1 fw-bold mb-0">Zula</span>
									</div>

									<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Add
										your Address Details</h5>
									<%
									if (customer != null && restaurant != null) {
										session.setAttribute("signupFail2", "Logout to Sign Up");
										response.sendRedirect("home.jsp");
									}
									%>
									<%
									String contactUpdateSuccess = (String) session.getAttribute("contactUpdateSuccess");
									if (contactUpdateSuccess != null) {
									%>

									<p class="h4 fw-bold text-success"><%=contactUpdateSuccess%></p>
									<%
									session.removeAttribute("contactUpdateSuccess");
									}
									%>
									<%
									String contactUpdateFail = (String) session.getAttribute("contactUpdateFail");
									if (contactUpdateFail != null) {
									%>

									<p class="h4 fw-bold text-danger"><%=contactUpdateFail%></p>
									<%
									session.removeAttribute("contactUpdateFail");
									}
									%>
									<%
									AddressDAO addressDAO = new AddressDAO(ConnectionProvider.getConnection());
									AddressBean address = addressDAO.selectAddressByIdAndType(customer.getCustID(), Type.valueOf("CUST_PRIMARY"));
									%>
									<form class="mx-1 mx-md-4" action="addContact" method="post">
										<div class="row">
											<div class="d-flex flex-row align-items-center mb-4">
												<i class="fas fa-house me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="text" placeholder="House No, Street Name"
														id="formStreetName" name="streetname"
														value="<%=address.getStreetName()%>" class="form-control"
														required="required" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">

												<i class="fa-solid fa-city me-3 fa-fw"></i> <select
													class="form-select" name="city">
													<option value="Chennai">Chennai</option>
													<option value="Coimbatore">Coimbatore</option>
												</select>
											</div>

											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fas fa-landmark me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="text" placeholder="District" id="formDistrict"
														name="district" value="<%=address.getDistrict()%>"
														class="form-control" required="required" />
												</div>
											</div>
										</div>

										<div class="row">
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fas fa-location fa-lg me-3 fa-fw"></i> <select
													class="form-select" name="state">
													<option value="Tamil Nadu">Tamil Nadu</option>
													<option value="Kerala">Kerala</option>
												</select>
											</div>
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fas fa-map-marker me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="number" min="1" max="999999"
														placeholder="Pincode" id="formPincode" name="pincode"
														value="<%=address.getPincode()%>" class="form-control"
														required="required" />
												</div>
											</div>
										</div>
										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="email" placeholder="Email" id="formEmail"
													name="email" value="<%=customer.getEmail()%>"
													class="form-control" required="required" />
											</div>
										</div>
										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-mobile fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" placeholder="Mobile Number"
													id="formMobile" name="mobile" class="form-control"
													value="<%=customer.getMobile()%>" required="required" />
											</div>
										</div>
										<div
											class="d-flex justify-content-center mx-4 mt-5 mb-3 mb-lg-4">
											<button type="submit" class="btn btn-primary btn-lg">Save
												Details</button>
										</div>

									</form>
								</div>
							</div>
							<div class="col-md-6 col-lg-5 d-none d-md-block">
								<img
									src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
									alt="Sample photo" class="img-fluid"
									style="border-radius: 0 1rem 1rem 0;" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@include file="../components/footer.jsp"%>
</body>
</html>