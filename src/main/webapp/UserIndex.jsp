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
								<h1>Welcome, <c:out value="${sessionScope.username}"></c:out></h1>
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
									<p class="opacity-75">"Find your favorite books
										effortlessly with our advanced search feature. Enjoy a
										streamlined checkout process and manage your loans with ease."</p>
									<p>
										<button class="btn btn-outline-danger" data-bs-toggle="modal"
										data-bs-target="#">Visit US</button>
										<button class="btn btn-outline-danger"" data-bs-toggle="modal"
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


	<%--services--%>
	<!---Add Book Module--->
	<div class="modal fade" id="addBookModal" tabindex="-1"
		aria-labelledby="addBookModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="addBookModal">Add Book</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="addBook" method="post">
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Book
								Name</label> <input type="text" class="form-control" id="book"
								name="bookname" aria-describedby="bookinfo">
							<div id="emailHelp" class="form-text">Enter the book you
								want to add.</div>
						</div>
						<div class="mb-3">
							<label for="AuthorName" class="form-label">Author Name</label> <input
								type="text" name="author" class="form-control" id="author">
							<div id="authorhelp" class="form-text">Enter author name.</div>

						</div>
						<div class="row">
							<div class="col-md-5">
								<label for="quantity" class="form-label">Select Quantity</label>
								<select id="quantity" name="quantity" class="form-select">
									<option selected>Choose...</option>
									<option>1</option>
									<option>10</option>
									<option>50</option>
									<option>100</option>
									<option>150</option>
									<option>200</option>
									<option>250</option>
								</select>

							</div>
							<div class="col-md-5">
								<label for="edition" class="form-label">Select Edition</label> <select
									id="edition" name="edition" class="form-select">
									<option selected>Choose...</option>
									<option>First</option>
									<option>Second</option>
									<option>Third</option>
									<option>Fourth</option>
									<option>Fifth</option>
								</select>
							</div>
						</div>
						<div class="d-grid gap-2 col-12 my-4 ">
							<button class="btn btn-outline-success" type="submit">Add Book</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" id="liveAlertBtn" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!---View Book Module for admin--->
	<div class="modal fade" id="viewAdminBookModal" tabindex="-1"
		aria-labelledby="viewBookModal" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5">View Books</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="input-group flex-nowrap">
							<span class="input-group-text">@</span> <input type="text"
								id="myInput" class="form-control"
								placeholder="Author Name, Book Name" aria-label="Username"
								aria-describedby="addon-wrapping">
						</div>
						<div class="d-grid gap-2 d-md-block my-2">
							<button type="button" class="btn btn-outline-info"
								onclick="window.location.href='viewBooks';" id="liveToastBtn">Fetch
								Books</button>
						</div>
						<br>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Book Name</th>
								<th scope="col">Edition</th>
								<th scope="col">Author</th>
								<th scope="col">Quantity</th>
								<th scope="col">Delete Book</th>
								<th scope="col">View Book</th>
								<th scope="col">Edit Book</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:set value="${sessionScope.bookslist}" var="list"></c:set>
							<c:set value="${sessionScope.email1}" var="emailid1"></c:set>
							<c:forEach items="${list}" var="row">
								<tr>
									<th scope="row">#</th>
									<td><c:out value="${row.getName()}"></c:out></td>
									<td><c:out value="${row.getEdition()}"></c:out></td>
									<td><c:out value="${row.getAuthor()}"></c:out></td>
									<td><c:out value="${row.getQuantity()}"></c:out></td>
									<td><form action="deleteBook" method="post">
											<input type="hidden" name="itemId" value="${row.getId()}" />
											<input class="btn btn-outline-danger" type="submit"
												value="Delete" />
										</form>
									</td>
									<td>
										<form action="viewAdminIssuedBook" method="post">
											<input type="hidden" id="viewAdmin" name="bookId"
												value="${row.getId()}" />
											<input id="adminIssuedBookModal"
												class="btn btn-outline-success" type="submit" value="View">
										</form>
									</td>
									<td>
										<form action="editBook" method="post">
											<input type="hidden" id="editBooks" name="editBookId"
												value="${row.getId()}" />
											<input class="btn btn-outline-warning" type="submit"
												value="Edit">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!---View Book Module for student--->
	<div class="modal fade" id="viewStudentBookModal" tabindex="-1"
		aria-labelledby="viewBookModal" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5">View Books</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="input-group flex-nowrap">
							<span class="input-group-text">@</span> <input type="text"
								id="myInput" class="form-control"
								placeholder="Author Name, Book Name" aria-label="Username"
								aria-describedby="addon-wrapping">
						</div>
						<div class="d-grid gap-2 d-md-block my-2">
							<button type="button" class="btn btn-outline-info"
								onclick="window.location.href='viewBooks';">Fetch Books</button>
						</div>
						<br>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Book Name</th>
								<th scope="col">Edition</th>
								<th scope="col">Author</th>
								<th scope="col">Quantity</th>
								<th scope="col">Issue Book</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:set value="${sessionScope.bookslist}" var="list"></c:set>
							<c:forEach items="${list}" var="row">
								<tr>
									<th scope="row">#</th>
									<td><c:out value="${row.getName()}"></c:out></td>
									<td><c:out value="${row.getEdition()}"></c:out></td>
									<td><c:out value="${row.getAuthor()}"></c:out></td>
									<td><c:out value="${row.getQuantity()}"></c:out></td>
									<td><form action="issueBook" method="post">
											<input type="hidden" name="bookId" value="${row.getId()}" />
											<input type="hidden" name="uniqueId"
												value="${sessionScope.email}" /> <input
												class="btn btn-outline-warning" type="submit" value="Issue" />
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!---View Issued Book Module for student--->
	<div class="modal fade" id="issuedBookModal" tabindex="-1"
		aria-labelledby="issuedBookModal" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5">Issued Books</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="input-group flex-nowrap">
							<span class="input-group-text">@</span> <input type="text"
								id="myInput" class="form-control"
								placeholder="Author Name, Book Name" aria-label="Username"
								aria-describedby="addon-wrapping">
						</div>
						<div class="d-grid gap-2 d-md-block my-2">
							<form action="issuedBooks" method="post">
								<input type="hidden" name="unique_Id"
									value="${sessionScope.unique_id}" />
								<button type="submit" class="btn btn-outline-info">Fetch
									Issued Books</button>
							</form>
						</div>
						<br>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Book Name</th>
								<th scope="col">Author</th>
								<th scope="col">Edition</th>
								<th scope="col">Issue Date</th>
								<th scope="col">Return Date</th>
								<th scope="col">Return</th>
								<th scope="col">Renew</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:set value="${sessionScope.issuedBookslist}"
								var="issuedBooklist"></c:set>
							<c:forEach items="${issuedBooklist}" var="row">
								<tr>
									<th scope="row">#</th>
									<td><c:out value="${row.getBookname()}"></c:out></td>
									<td><c:out value="${row.getAuthor()}"></c:out></td>
									<td><c:out value="${row.getEdition()}"></c:out></td>
									<td><c:out value="${row.getIssued_date()}"></c:out></td>
									<td><c:out value="${row.getReturn_date()}"></c:out></td>
									<td><form action="returnBook" method="post">
											<input type="hidden" name="IssuedBookId"
												value="${row.getIssued_book_id()}" /> <input
												class="btn btn-outline-danger" type="submit" value="Return" />
										</form></td>
										<td><form action="renewBook" method="post">
											<input type="hidden" name="renewBookId"
												value="${row.getIssued_book_id()}" /> <input
												class="btn btn-outline-success" type="submit" value="Renew" />
										</form></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
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
						<button class="btn btn-outline-danger" data-bs-toggle="modal"
							data-bs-target="#<c:out value='${sessionScope.firstcardtype}'></c:out>">
							<c:out value="${sessionScope.card1}"></c:out>
						</button>
					</div>
					<div class="col-auto d-none d-lg-block">
						<img height="250" width="200"
							src="Images/service_1.jpg" alt="">
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
						<button class="btn btn-outline-danger" data-bs-toggle="modal"
							type="button"
							data-bs-target='#<c:out value="${sessionScope.viewBookType}"></c:out>'>
							<c:out value="${sessionScope.card2}"></c:out>
						</button>
					</div>
					<div class="col-auto d-none d-lg-block">
						<img height="250" width="200"
							src="Images/service_2.jpg" alt="">
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