<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LMS</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="index.js"></script>
<script type="" src="index.css"></script>
<link href="index.css" rel="stylesheet">
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
	<%--alerts--%>
	<div class="container my-1">
		<div id="allAlerts"
			class="alert alert-<%=alert_type%> alert-dismissible fade show"
			role="alert">
			<strong><c:out value="${sessionScope.alert}"></c:out></strong>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</div>

	<%--nav bar--%>
	<div class="container">
		<div class="row">
			<div class="col-md-6 ">
				<ul class="nav nav-underline ">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About Us</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact
							Us</a></li>
				</ul>
			</div>
			<div class="col-md-6 my-2 nav-user-info">
				<button type="button" class="btn btn-outline-danger"
					data-bs-toggle="modal" data-bs-target="#LoginModal">
					Welcome,
					<c:out value="${sessionScope.username}">No Data</c:out>
				</button>
				<button id="userRole" type="button" class="btn btn-outline-success"
					data-bs-toggle="modal" data-bs-target="#RegisterModal">
					Role :
					<c:out value="${sessionScope.userrole}">
					</c:out>
				</button>
				<button type="button" class="btn btn-outline-danger"
					data-bs-toggle="modal">
					<a id="logout" href="logout">Logout</a>
				</button>
			</div>
		</div>
	</div>

	<%--slides--%>
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
								<h1>
									Welcome,
									<c:out value="${sessionScope.username}"></c:out>
								</h1>
								<p>"Discover a world of knowledge and resources at your
									fingertips. Explore our extensive collection of books, digital
									media, and more!"</p>
								<p>
									<button class="btn btn-outline-danger" data-bs-toggle="modal"
										data-bs-target="#">Visit US</button>
									<button class="btn btn-outline-danger" data-bs-toggle="modal"
										data-bs-target="#">Know More</button>
								</p>
							</div>
						</div>
					</div>
					<div class="carousel-item">
						<img src="Images/2_cara.jpg" width="100%" height="500px" alt="">
						<div class="container">
							<div class="carousel-caption carousel-caption">
								<h1>Easy Book Search and Checkout.</h1>
								<p class="opacity-75">"Find your favorite books effortlessly
									with our advanced search feature. Enjoy a streamlined checkout
									process and manage your loans with ease."</p>
								<p>
									<button class="btn btn-outline-danger" data-bs-toggle="modal"
										data-bs-target="#">Visit US</button>
									<button class="btn btn-outline-danger" " data-bs-toggle="modal"
										data-bs-target="#">Know More</button>
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
									<button class="btn btn-outline-danger" data-bs-toggle="modal"
										data-bs-target="#">Visit US</button>
									<button class="btn btn-outline-danger" data-bs-toggle="modal"
										data-bs-target="#">Know More</button>
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

	<!---services cards--->
	<div class="container my-4">
		<div class="row">
			<div class="col-md-6">
				<div
					class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col p-4 d-flex flex-column position-static">
						<strong class="d-inline-block mb-2 text-primary-emphasis"><c:out
								value="${sessionScope.cardfirstfirstline}"></c:out></strong>
						<h3 class="mb-0">
							<c:out value="${sessionScope.cardfirstheading}"></c:out>
						</h3>
						<div class="mb-1 text-body-secondary">
							<c:out value="${sessionScope.cardfirstthirdline}"></c:out>
						</div>
						<p class="card-text mb-auto">
							<c:out value="${sessionScope.cardfirstfourthline}"></c:out>
						</p>
						<form action="issuedBooks" method="post">
							<input hidden="hidden" name="unique_Id"
								value="<c:out value='${sessionScope.unique_id}'></c:out>" />
							<button class="btn btn-outline-danger" type="submit" style="width:-webkit-fill-available;">
								<c:out value="${sessionScope.card1}"></c:out>
							</button>
						</form>
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
						<strong class="d-inline-block mb-2 text-success-emphasis"><c:out
								value="${sessionScope.cardsecondfirstline}"></c:out></strong>
						<h3 class="mb-0">
							<c:out value="${sessionScope.cardsecondheading}"></c:out>
						</h3>
						<div class="mb-1 text-body-secondary">
							<c:out value="${sessionScope.cardsecondthirdline}"></c:out>
						</div>
						<p class="mb-auto">
							<c:out value="${sessionScope.cardsecondfourthline}"></c:out>
						</p>
						<a class="btn btn-outline-danger" type="button"
							href="<c:out value='${sessionScope.viewBookType}'></c:out>">
							<c:out value="${sessionScope.card2}"></c:out>
						</a>
					</div>
					<div class="col-auto d-none d-lg-block">
						<img height="250" width="200" src="Images/service_2.jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</div>

	<%--footer--%>
	<div class="container">
		<footer class="container">
			<hr class="featurette-divider">
			<p class="float-end">
				<a href="#">Back to top</a>
			</p>
			<p>
				© 2017-2024 Company, Inc. · <a href="#">Privacy</a> · <a href="#">Terms</a>
			</p>
		</footer>
	</div>

	<%--scripts--%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script src="index.js"></script>
</body>
</html>