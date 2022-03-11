<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
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

									<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Create
										a new account</h5>
									<%
									if (customer != null) {
										session.setAttribute("signupFail2", "Logout to Sign Up");
										response.sendRedirect("home.jsp");
									}
									%>
									<%
									String signupFail = (String) session.getAttribute("signupFail");
									if (signupFail != null) {
									%>

									<p class="h4 fw-bold text-danger"><%=signupFail%></p>
									<%
									session.removeAttribute("signupFail");
									}
									%>
									<form class="mx-1 mx-md-4" action="signup" method="post">
										<div class="row">
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fas fa-user fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="text" placeholder="First Name"
														id="formFirstName" name="fname" class="form-control"
														required="required" />
												</div>
											</div>
											<div class="d-flex flex-row align-items-center col-md-6 mb-4">
												<i class="fas fa-user fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<input type="text" placeholder="Last Name"
														id="formLastName" name="lname" class="form-control"
														required="required" />
												</div>
											</div>
										</div>

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

										<div class="form-check d-flex justify-content-center mt-4 mb-2">
											<input class="form-check-input me-2" type="checkbox" value=""
												id="formAgreeConditions" name="agreeConditions"
												required="required" /> <label class="form-check-label"
												for="formAgreeConditions"> I agree all statements in
												<a href="#">Terms of service</a>
											</label>
										</div>
										<div class="d-flex justify-content-center mx-4 mt-4 mb-3 mb-lg-4">
											<button type="submit" class="btn btn-primary btn-lg">Register</button>
										</div>

										<p class="text-center text-muted mt-5 mb-0">
											Have already an account? <a href="login.jsp"
												class="fw-bold text-body">Login here</a>
										</p>

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