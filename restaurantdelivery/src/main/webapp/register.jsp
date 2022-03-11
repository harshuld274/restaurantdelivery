<%@page import="com.phantombeast.restaurantdelivery.bean.AddressBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
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
						<form class="mx-1 mx-md-4" action="register" method="post">
							<div class="row g-0">
								<div class="col-md-6 d-flex align-items-center">
									<div class="card-body p-4 p-lg-5 text-black">
										<div class="d-flex align-items-center mb-3 pb-1">
											<i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
											<span class="h1 fw-bold mb-0">Zula</span>
										</div>

										<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Add
											your Restaurant</h5>
										<%
										if (restaurant != null) {
											session.setAttribute("signupFail2", "Logout to Sign Up");
											response.sendRedirect("restaurant/home.jsp");
										}
										%>
										<%
										String registerFail = (String) session.getAttribute("registerFail");
										if (registerFail != null) {
										%>

										<p class="h4 fw-bold text-danger"><%=registerFail%></p>
										<%
										session.removeAttribute("registerFail");
										}
										%>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" placeholder="Username" id="formUsername"
													name="username" class="form-control" required="required" />
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-lock fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="password" placeholder="Password"
													id="formPassword" name="password" class="form-control"
													required="required" />
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-key fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="password" placeholder="Confirm Password"
													id="formPassword2" name="password2" class="form-control"
													required="required" />
											</div>
										</div>
										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="email" placeholder="Email" id="formEmail"
													name="email" class="form-control" required="required" />
											</div>
										</div>
										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-mobile fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" placeholder="Mobile Number"
													id="formMobile" name="mobile" class="form-control"
													required="required" />
											</div>
										</div>
										<div
											class="form-check d-flex justify-content-center mt-4 mb-2">
											<input class="form-check-input me-2" type="checkbox" value=""
												id="formAgreeConditions" name="agreeConditions"
												required="required" /> <label class="form-check-label"
												for="formAgreeConditions"> I agree all statements in
												<a href="#">Terms of service</a>
											</label>
										</div>

										<div
											class="d-flex justify-content-center mx-4 mt-4 mb-3 mb-lg-4">
											<button type="submit" class="btn btn-primary btn-lg">Register</button>
										</div>
									</div>
								</div>
								<div class="col-md-6 d-flex align-items-center">
									<div class="card-body p-4 p-lg-5 text-black">
										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-user fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" placeholder="Restaurant Name"
													id="formName" name="name" class="form-control"
													required="required" />
											</div>
										</div>

										<div class="row">
											<div class="d-flex flex-row align-items-center mb-4">
												<i class="fas fa-house me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="text" placeholder="House No, Street Name"
														id="formStreetName" name="streetname" class="form-control"
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
														name="district" class="form-control" required="required" />
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
														class="form-control" required="required" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fa-solid fa-clock me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="time" placeholder="Open Time" name="openTime"
														class="form-control" required="required" />
												</div>
											</div>
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fa-solid fa-clock me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="time" placeholder="Close Time"
														name="closeTime" class="form-control" required="required" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fas fa-times me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="number" min="1" max="120"
														placeholder="Cancel Time (minutes)"
														name="cancelTime" class="form-control" required="required" />
												</div>
											</div>
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fas fa-times me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="number" min="1" max="120"
														placeholder="Delivery Time (minutes)"
														name="deliveryTime" class="form-control"
														required="required" />
												</div>
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-dollar-sign me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="number" min="1" max="200" step="0.50"
													placeholder="Delivery Fee" name="deliveryFee"
													class="form-control" required="required" />
											</div>
										</div>
										<p class="text-center text-muted mt-4">
											Have already an account? <a href="login.jsp"
												class="fw-bold text-body">Login here</a>
										</p>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@include file="../components/footer.jsp"%>
</body>
</html>