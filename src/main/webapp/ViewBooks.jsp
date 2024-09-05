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
						aria-current="page" href="UserIndex.jsp">Home</a></li>
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
					<c:out value="${sessionScope.username}"></c:out>
				</button>
				<button id="userRole" type="button" class="btn btn-outline-success"
					data-bs-toggle="modal" data-bs-target="#RegisterModal">
					Role :
					<c:out value="${sessionScope.userrole}"></c:out>
				</button>
				<button type="button" class="btn btn-outline-danger"
					data-bs-toggle="modal">
					<a id="logout" href="logout">Logout</a>
				</button>
			</div>
		</div>
	</div>
	<div class="container">
		<h1 class="modal-title fs-5">View Books</h1>
	</div>
	<div class="container">
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
					<th scope="col">Issued Books</th>
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
								<input type="hidden" name="itemId" value="${row.getId()}" /> <input
									class="btn btn-outline-danger" type="submit" value="Delete" />
							</form></td>
						<td>
							<form action="viewAdminIssuedBook" method="post">
								<input type="hidden" id="viewAdmin" name="bookId"
									value="${row.getId()}" /> <input id="adminIssuedBookModal"
									class="btn btn-outline-success" type="submit"
									value="Recipients">
							</form>
						</td>
						<td>
							<form action="editBook" method="post">
								<input type="hidden" id="editBooks" name="editBookId"
									value="${row.getId()}" /> <input
									class="btn btn-outline-warning" type="submit" value="Edit">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="modal-footer">
			<a type="submit" class="btn btn-outline-info"
				href="UserIndex.jsp">Home</a>
		</div>
	</div>


	<%--scripts--%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script src="index.js"></script>
</body>
</html>