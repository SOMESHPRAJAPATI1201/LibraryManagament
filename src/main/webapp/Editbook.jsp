<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit Books</title>
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

button a {
	text-decoration: none;
	color: red;
}

button a:hover {
	text-decoration: none;
	color: white;
}

.nav-user-info {
	text-align: end;
}

#fetch {
	
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript" src="index2.js"></script>
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
	<!---Add Book Module--->
	<div class="container my-3">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="addBookModal">Edit Book</h1>
					</div>
					<div class="modal-body">
						<form action="editBookData" method="post">
							<input type="hidden" value="${sessionScope.bookId}" name="bookID">
							<div class="mb-3">
								<label for="exampleInputEmail1" class="form-label">Book
									Name</label> <input type="text" class="form-control" id="book"
									name="bookname" aria-describedby="bookinfo" value="${sessionScope.bookName}">
								<div id="emailHelp" class="form-text">Enter the book name.</div>
							</div>
							<div class="mb-3">
								<label for="AuthorName" class="form-label">Author Name</label> <input
									type="text" name="author" class="form-control" id="author" value="${sessionScope.bookAuthor}">
								<div id="authorhelp" class="form-text">Enter author name.</div>

							</div>
							<div class="row">
								<div class="col-md-5">
									<label for="quantity" class="form-label">Select
										Quantity</label> <select id="quantity" name="quantity"
										class="form-select">
										<option selected >${sessionScope.bookQuantity}</option>
										<option>50</option>
										<option>100</option>
										<option>150</option>
										<option>200</option>
										<option>250</option>
									</select>

								</div>
								<div class="col-md-5">
									<label for="edition" class="form-label">Select Edition</label>
									<select id="edition" name="edition" class="form-select">
										<option selected><b>${sessionScope.bookEdition}</b></option>
										<option>First</option>
										<option>Second</option>
										<option>Third</option>
										<option>Fourth</option>
										<option>Fifth</option>
									</select>
								</div>
							</div>
							<div class="d-grid gap-2 col-6 my-4 mx-auto">
								<button class="btn btn-primary" type="submit">Edit Book</button>
							</div>
						</form>
					</div>
					<div class="modal-footer my-5">
						<a class="btn btn-secondary" href="UserIndex.jsp">Close</a>
					</div>
				</div>
			</div>
	</div>
	<%--footer--%>
	<div class="container ">
		<footer class="my-6">
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
	<script src="/docs/5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>



</body>
</html>