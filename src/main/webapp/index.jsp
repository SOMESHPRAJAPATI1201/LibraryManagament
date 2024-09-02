<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>

<body>
	<%
	String alert_type = (String) session.getAttribute("alert-type");
	%>
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
					data-bs-toggle="modal" data-bs-target="#RegisterModal">Admin
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
								<input type="email" name="email" class="form-control"
									id="floatingEmail" placeholder="name@example.com"> <label
									for="floatingInput">Email address</label>
							</div>
							<div class="form-floating my-2">
								<input type="password" name="password" class="form-control"
									id="floatingPassword" placeholder="Password"> <label
									for="floatingPassword">Password</label>
							</div>
							<div class="form-check text-start my-3">
								<input class="form-check-input" type="checkbox"
									value="remember-me" id="flexCheckDefault"> <label
									class="form-check-label" for="flexCheckDefault">
									Remember me </label>
							</div>
							<button class="btn btn-primary w-100 py-2" type="submit">Sign
								in</button>
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
		<div class="modal fade" id="RegisterModal" tabindex="-1"
			aria-labelledby="RegisterModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="RegisterModal">Admin
							Register</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form class="row g-3" action="adminRegistration" method="post">
							<div class="col-md-6">
								<label for="inputEmail4" class="form-label">Email</label> <input
									type="email" class="form-control" name="email" id="inputEmail4">
							</div>
							<div class="col-md-6">
								<label for="inputPassword4" class="form-label">Password</label>
								<input type="password" class="form-control" name="password"
									id="inputPassword4">
							</div>
							<div class="col-md-6">
								<label for="inputFName" class="form-label">First Name</label> <input
									type="text" class="form-control" name="fname" id="firstName">
							</div>
							<div class="col-md-6">
								<label for="inputLName" class="form-label">Last Name</label> <input
									type="text" class="form-control" name="lname" id="lastName">
							</div>
							<div class="col-12">
								<label for="inputAddress" class="form-label">Address</label> <input
									type="text" class="form-control" name="address"
									id="inputAddress"
									placeholder="Ex : 123 Main Street Apt 4B, Springfield, IL, 62704">
							</div>
							<div class="col-md-4">
								<label for="inputState" class="form-label">Library Name</label>
								<select id="inputState" name="libname" class="form-select">
									<option selected>Choose...</option>
									<option value="SVCE, INDORE">Swami Vivekanand College
										Of Engineering, Indore</option>
									<option value="SAGE UNIVERSITY, INDORE">Sage
										University, Indore</option>
									<option value="SAGAR UNIVERSITY, BHOPAL">Sagar
										University, Bhopal</option>
									<option value="LNCT GROUP, INDORE">LNCT Group, Indore</option>
									<option value="LNCT GROUP, BHOPAL">LNCT Group, Bhopal</option>
								</select>
							</div>
							<div class="col-md-4">
								<label for="inputState" class="form-label">Role</label> <select
									id="inputState" class="form-select">
									<option selected>Admin</option>
								</select>
							</div>
							<div class="col-12">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="gridCheck">
									<label class="form-check-label" for="gridCheck"> Check
										me out </label>
								</div>
							</div>
							<div class="col-12">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
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
						<img src="Images/library.jpg" width="100%" height="500px" alt="">
						<div class="container">
							<div class="carousel-caption text-start">
								<h1>Example headline.</h1>
								<p class="opacity-75">Some representative placeholder
									content for the first slide of the carousel.</p>
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
						<img src="Images/library.jpg" width="100%" height="500px" alt="">
						<div class="container">
							<div class="carousel-caption">
								<h1>Another example headline.</h1>
								<p>Some representative placeholder content for the second
									slide of the carousel.</p>
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
						<img src="Images/library.jpg" width="100%" height="500px" alt="">
						<div class="container">
							<div class="carousel-caption text-end">
								<h1>One more for good measure.</h1>
								<p>Some representative placeholder content for the third
									slide of this carousel.</p>
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
									id="floatingEmail" placeholder="name@example.com"> <label
									for="floatingInput">Email address</label>
							</div>
							<div class="form-floating my-2">
								<input type="password" class="form-control"
									id="floatingPassword" name="password" placeholder="Password">
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
						<h1 class="modal-title fs-5" id="UserRegisterModal">Register</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="registration" method="post" class="row g-3">
							<div class="col-md-6">
								<label for="inputEmail4" class="form-label">Email</label> <input
									type="email" name="email" class="form-control" id="inputEmail4">
							</div>
							<div class="col-md-6">
								<label for="inputPassword4" class="form-label">Password</label>
								<input type="password" name="password" class="form-control"
									id="inputPassword4">
							</div>
							<div class="col-md-6">
								<label for="inputFName" class="form-label">First Name</label> <input
									type="text" name="fname" class="form-control" id="firstName">
							</div>
							<div class="col-md-6">
								<label for="inputLName" class="form-label">Last Name</label> <input
									type="text" name="lname" class="form-control" id="lastName">
							</div>
							<div class="col-md-4">
								<label for="inputState" class="form-label">Role</label> <select
									id="inputState" name="role" class="form-select">
									<option selected>Student</option>
								</select>
							</div>
							<div class="col-12">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="gridCheck">
									<label class="form-check-label" for="gridCheck"> Check
										me out </label>
								</div>
							</div>
							<div class="col-12">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
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
							supporting text below as a natural lead-in to additional content.</p>
						<button class="btn btn-outline-danger">Know More</button>
					</div>
					<div class="col-auto d-none d-lg-block">
						<img height="250" width="200"
							src="Images/pexels-element5-1370295.jpg" alt="">
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
						<img height="250" width="200"
							src="Images/pexels-emrecan-2079451.jpg" alt="">
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
							supporting text below as a natural lead-in to additional content.</p>
						<button class="btn btn-outline-danger">Know More</button>
					</div>
					<div class="col-auto d-none d-lg-block">
						<img height="250" width="200"
							src="Images/pexels-ivo-rainha-527110-1290141.jpg" alt="">
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
						<img height="250" width="200"
							src="Images/pexels-repuding-12064.jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<hr class="featurette-divider">
		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading fw-normal lh-1">
					First featurette heading. <span class="text-body-secondary">It’ll
						blow your mind.</span>
				</h2>
				<p class="lead">Some great placeholder content for the first
					featurette here. Imagine some exciting prose here.</p>
			</div>
			<div class="col-md-5">
				<svg
					class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto"
					width="500" height="500" xmlns="http://www.w3.org/2000/svg"
					role="img" aria-label="Placeholder: 500x500"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="var(--bs-secondary-bg)"></rect>
					<text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
			</div>
		</div>
		<hr class="featurette-divider">
		<div class="container">
			<div class="row featurette">
				<div class="col-md-7 order-md-2">
					<h2 class="featurette-heading fw-normal lh-1">
						Oh yeah, it’s that good. <span class="text-body-secondary">See
							for yourself.</span>
					</h2>
					<p class="lead">Another featurette? Of course. More placeholder
						content here to give you an idea of how this layout would work
						with some actual real-world content in place.</p>
				</div>
				<div class="col-md-5 order-md-1">
					<svg
						class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto"
						width="500" height="500" xmlns="http://www.w3.org/2000/svg"
						role="img" aria-label="Placeholder: 500x500"
						preserveAspectRatio="xMidYMid slice" focusable="false">
						<title>Placeholder</title><rect width="100%" height="100%"
							fill="var(--bs-secondary-bg)"></rect>
						<text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script src="index.js"></script>
</body>

</html>