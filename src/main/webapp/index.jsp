<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LMS</title>
<style>
* {
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	margin: 0%;
}

.card1 {
	margin-top: 50%;
}

.card img {
	margin-top: 3%;
	height: auto;
	width: 100%;
	align-content: center;
}
</style>
<script src="index.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>

<body>
	<%
	String alert_type = (String) session.getAttribute("alert-type");
	if (alert_type == null) {
		alert_type = "warning";
	}
	%>
	<%-- alerts --%>
	<div class="container my-1">
		<c:set var="alertMessage"
			value="${sessionScope.alert != null ? sessionScope.alert : 'Welcome To Vishwas Library Management System.'}" />

		<div id="allAlerts"
			class="alert alert-<%=alert_type%> alert-dismissible fade show"
			role="alert">
			<strong><c:out value="${alertMessage}" /></strong>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<ul class="nav nav-underline ">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About Us</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact
							Us</a></li>
				</ul>
			</div>
			<div class="col-md-4 my-2">
				<button type="button" class="btn btn-outline-danger"
					data-bs-toggle="modal" data-bs-target="#LoginModal">Admin
					Login</button>
				<button type="button" class="btn btn-outline-primary"
					data-bs-toggle="modal" data-bs-target="#RegisterAdmin">Admin
					Registration</button>
			</div>
		</div>

		<!--Admin Login trigger modal -->
		<div class="modal fade" id="LoginModal" tabindex="-1"
			aria-labelledby="LoginModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="LoginModal">Admin Login</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="adminLogin" method="post">
							<div class="form-floating my-2">
								<input type="text" name="email" class="form-control"
									id="floatingEmail" placeholder="name@example.com"
									pattern="[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]" required>
								<label for="floatingInput">Email address</label>
							</div>
							<div class="form-floating my-2">
								<input type="password" name="password" class="form-control"
									id="floatingPassword" placeholder="Password"
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" required>
								<label for="floatingPassword">Password</label>
							</div>
							<div class="form-check text-start my-3">
								<input class="form-check-input" type="checkbox"
									value="remember-me" id="flexCheckDefault"> <label
									class="form-check-label" for="flexCheckDefault">
									Remember me </label>
							</div>
							<button class="btn btn-primary w-100 py-2" type="submit">Sign
								in</button>
							<div class="my-4">
								<div class="alert alert-info" role="alert"
									style="font-size: 13px">Warning : Password must contain
									at least one capital letter and special character with
									length of 8-16.</div>
							</div>
							<p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!--Admin Register trigger modal -->
		<div class="modal fade" id="RegisterAdmin" tabindex="-1"
			aria-labelledby="RegisterAdmin" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="RegisterAdmin">Admin
							Register</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="adminRegistration" method="post">
							<div class="form-floating my-2">
								<input type="text" name="fname" class="form-control"
									id="floatingEmail" pattern="[A-Za-z]{1,32}"
									placeholder="Somesh" required> <label
									for="floatingInput">First Name</label>
							</div>
							<div class="form-floating my-2">
								<input type="text" name="lname" pattern="[A-Za-z]{1,32}"
									class="form-control" id="floatingEmail" placeholder="Prajapati"
									required> <label for="floatingInput">Last Name</label>
							</div>
							<div class="form-floating my-2">
								<input type="text" name="address"
									pattern="[0-9a-zA-Z\s,.-]{5,100}" class="form-control"
									id="floatingEmail"
									placeholder="Ex : 123 Main Street Apt 4B, Springfield, IL, 62704"
									required><label for="floatingInput">Address</label>
							</div>
							<div class="form-floating my-2">
								<input type="email" name="email"
									pattern="[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]"
									class="form-control" id="floatingEmail"
									placeholder="name@example.com" required> <label
									for="floatingInput">Email address</label>
							</div>
							<div class="form-floating my-2">
								<input type="password" name="password"
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}"
									class="form-control" id="floatingPassword"
									placeholder="Password" required> <label
									for="floatingPassword">Password</label>
							</div>
							<div class="form-floating my-2">
								<input type="text" name="libname" pattern="[a-zA-Z][//s]{10,50}"
									class="form-control" id="floatingEmail"
									placeholder="Enter Library Name" required> <label
									for="floatingInput">Enter Library Name</label>
							</div>
							<button class="btn btn-primary w-100 py-2" type="submit">Register</button>
							<div class="my-4">
								<div class="alert alert-info" role="alert"
									style="font-size: 13px">Warning : Password must contain
									at least one capital letter and special character with
									length of 8-16.</div>
							</div>
							<p class="mt-5 mb-3 text-body-secondary">© 2017-2024</p>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Slides -->
		<div class="row">
			<div class="container my-2 mx-1">
				<div id="myCarousel" class="carousel slide mb-6"
					data-bs-ride="carousel">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#myCarousel"
							data-bs-slide-to="0" class="active" aria-label="Slide 1"
							aria-current="true"></button>
						<button type="button" data-bs-target="#myCarousel"
							data-bs-slide-to="1" aria-label="Slide 2" class=""></button>
						<button type="button" data-bs-target="#myCarousel"
							data-bs-slide-to="2" aria-label="Slide 3" class=""></button>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="Images/1_cara.jpg" width="100%" height="500px" alt="">
							<div class="container">
								<div class="carousel-caption carousel-caption">
									<h1>Welcome to Vishwas Libraries</h1>
									<p>"Discover a world of knowledge and resources at your
										fingertips. Explore our extensive collection of books, digital
										media, and more!"</p>
									<p>
									<p>
										<button class="btn btn-primary" data-bs-toggle="modal"
											data-bs-target="#UserLoginModal">User Login</button>
										<button class="btn btn-primary" data-bs-toggle="modal"
											data-bs-target="#UserRegisterModal">User Register</button>
									</p>
									</p>
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<img src="Images/2_cara.jpg" width="100%" height="500px" alt="">
							<div class="container">
								<div class="carousel-caption carousel-caption">
									<h1>Easy Book Search and Checkout.</h1>
									<p class="opacity-75">"Find your favorite books
										effortlessly with our advanced search feature. Enjoy a
										streamlined checkout process and manage your loans with ease."</p>
									<p>
										<button class="btn btn-primary" data-bs-toggle="modal"
											data-bs-target="#UserLoginModal">User Login</button>
										<button class="btn btn-primary" data-bs-toggle="modal"
											data-bs-target="#UserRegisterModal">User Register</button>
									</p>
								</div>
							</div>
						</div>
						<div class="carousel-item">
							<img src="Images/3_cara.jpg" width="100%" height="500px" alt="">
							<div class="container">
								<div class="carousel-caption">
									<h1>Access Digital Resources Anytime.</h1>
									<p>"Explore our digital library, including e-books,
										audiobooks, and online journals. Access resources from
										anywhere, anytime."</p>
									<p>
									<p>
										<button class="btn btn-primary" data-bs-toggle="modal"
											data-bs-target="#UserLoginModal">User Login</button>
										<button class="btn btn-primary" data-bs-toggle="modal"
											data-bs-target="#UserRegisterModal">User Register</button>
									</p>
									</p>
								</div>
							</div>
						</div>

					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#myCarousel" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#myCarousel" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
		</div>
		<!--User Login trigger modal -->
		<div class="modal fade" id="UserLoginModal" tabindex="-1"
			aria-labelledby="UserLoginModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="UserLoginModal">User Login</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="login" method="post">

							<div class="form-floating my-2">
								<input type="email" name="email" class="form-control"
									id="floatingEmail" placeholder="name@example.com"
									pattern="[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]" required>
								<label for="floatingInput">Email address</label>
							</div>
							<div class="form-floating my-2">
								<input type="password" name="password" class="form-control"
									id="floatingPassword" placeholder="Password"
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" required>
								<label for="floatingPassword">Password</label>
							</div>
							<div class="form-check text-start my-3">
								<input class="form-check-input" type="checkbox"
									value="remember-me" id="flexCheckDefault"> <label
									class="form-check-label" for="flexCheckDefault">
									Remember me </label>
							</div>
							<button class="btn btn-primary w-100 py-2" type="submit">Sign
								in</button>
							<div class="my-4">
								<div class="alert alert-info" role="alert"
									style="font-size: 13px">Warning : Password must contain
									at least one capital letter and special character with
									length of 8-16.</div>
							</div>
							<p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!--User Register trigger modal -->
		<div class="modal fade" id="UserRegisterModal" tabindex="-1"
			aria-labelledby="UserRegisterModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="UserRegisterModal">Student
							Register</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="studentRegistration" method="post">
							<div class="form-floating my-2">
								<input type="text" name="fname" class="form-control"
									id="floatingEmail" pattern="[A-Za-z]{1,32}"
									placeholder="Somesh" required> <label
									for="floatingInput">First Name</label>
							</div>
							<div class="form-floating my-2">
								<input type="text" name="lname" pattern="[A-Za-z]{1,32}"
									class="form-control" id="floatingEmail" placeholder="Prajapati"
									required> <label for="floatingInput">Last Name</label>
							</div>
							<div class="form-floating my-2">
								<input type="email" name="email"
									pattern="[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]"
									class="form-control" id="floatingEmail"
									placeholder="name@example.com" required> <label
									for="floatingInput">Email address</label>
							</div>
							<div class="form-floating my-2">
								<input type="password" name="password"
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}"
									class="form-control" id="floatingPassword"
									placeholder="Password" required> <label
									for="floatingPassword">Password</label>
							</div>
							<button class="btn btn-primary w-100 py-2 my-3" type="submit">Register</button>
							<div class="my-4">
								<div class="alert alert-info" role="alert"
									style="font-size: 13px">Warning : Password must contain
									at least one capital letter and special character with
									length of 8-16.</div>
							</div>
							<p class="mt-5 mb-3 text-body-secondary">© 2017-2024</p>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Cards -->
		<div class="container my-4">
			<div class="row">
				<div class="col-md-6">
					<div
						class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-primary-emphasis">Madhya
								Pradesh Centres</strong>
							<h3 class="mb-0">Indore</h3>
							<div class="mb-1 text-body-secondary">Since - Nov 12</div>
							<p class="card-text mb-auto">This is a wider card with
								supporting text below as a natural lead-in to additional
								content.</p>
							<button class="btn btn-outline-danger">Know More</button>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img height="250" width="200" src="Images/service_1.jpg" alt="">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div
						class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-success-emphasis">Madhya
								Pradesh Centres</strong>
							<h3 class="mb-0">Ujjain</h3>
							<div class="mb-1 text-body-secondary">Since - Nov 11</div>
							<p class="mb-auto">This is a wider card with supporting text
								below as a natural lead-in to additional content.</p>
							<button class="btn btn-outline-danger">Know More</button>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img height="250" width="200" src="Images/service_2.jpg" alt="">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div
						class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-primary-emphasis">Madhya
								Pradesh Centres</strong>
							<h3 class="mb-0">Bhopal</h3>
							<div class="mb-1 text-body-secondary">Since - Nov 13</div>
							<p class="card-text mb-auto">This is a wider card with
								supporting text below as a natural lead-in to additional
								content.</p>
							<button class="btn btn-outline-danger">Know More</button>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img height="250" width="200" src="Images/service_3.jpg" alt="">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div
						class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-success-emphasis">Madhya
								Pradesh Centres</strong>
							<h3 class="mb-0">Jabalpur</h3>
							<div class="mb-1 text-body-secondary">Since - Nov 14</div>
							<p class="mb-auto">This is a wider card with supporting text
								below as a natural lead-in to additional content.</p>
							<button class="btn btn-outline-danger">Know More</button>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img height="250" width="200" src="Images/service_4.jpg" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<hr class="featurette-divider">
			<div class="row featurette">
				<div class="col-md-7">
					<h2 class="featurette-heading fw-normal lh-1">Don’t Miss Our
						Events!</h2>
					<p class="lead">Our library hosts a dynamic range of events
						designed to inspire and engage. From captivating author talks and
						interactive book clubs to educational workshops and lively
						community gatherings, there's always something exciting happening.
						These events offer opportunities to connect with fellow book
						lovers, learn from experts, and participate in enriching
						activities. Stay updated with our event calendar and make sure
						you’re part of the vibrant experiences that our library offers.
						Don’t miss out on the chance to enrich your knowledge and enjoy
						memorable moments with us. Check out what’s coming up and join the
						fun!</p>
				</div>
				<div class="col-md-5">
					<img alt="" src="Images/events.jpg" width="500" height="500">
				</div>
			</div>
			<hr class="featurette-divider">
			<div class="container">
				<div class="row featurette">
					<div class="col-md-7 order-md-2">
						<h2 class="featurette-heading fw-normal lh-1">Access Digital
							Resources.</h2>
						<p class="lead">Dive into a wealth of resources with our
							extensive digital library. Enjoy seamless access to a vast
							collection of e-books, audiobooks, and online databases,
							available anytime and from anywhere. Whether you're looking for
							the latest bestsellers, immersive audiobooks, or in-depth
							research materials, our digital offerings cater to all your
							reading and research needs. With user-friendly access on multiple
							devices, you can conveniently explore, read, and study at your
							own pace. Enhance your learning experience and enjoy the freedom
							of digital access with our comprehensive and ever-growing
							collection..</p>
					</div>
					<div class="col-md-5 order-md-1">
						<img alt="" src="Images/technology.jpg" width="500" height="500">
					</div>
				</div>
			</div>
			<footer class="container">
				<hr class="featurette-divider">
				<p class="float-end">
					<a href="#">Back to top</a>
				</p>
				<p>
					© 2017–2024 Company, Inc. · <a href="#">Privacy</a> · <a href="#">Terms</a>
				</p>
			</footer>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>

</html>