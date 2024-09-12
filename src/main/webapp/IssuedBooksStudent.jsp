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
		<h1 class="modal-title fs-5">Issued Books</h1>
		<div class="row">
			<div class="input-group flex-nowrap">
				<span class="input-group-text">@</span> <input type="text"
					id="myInput" class="form-control"
					placeholder="Author Name, Book Name" aria-label="Username"
					aria-describedby="addon-wrapping">
			</div>
			<div class="d-grid gap-2 d-md-block my-2">
				<form action="issuedBooks" method="post">
					<input type="hidden" name="unique_Id" value="${sessionScope.unique_id}"/>
					<button type="submit" class="btn btn-outline-info">Fetch Issued Books</button>
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
					<th scope="col">Renew : Date Format (mm/dd/yyyy)</th>
					<th scope="col">Return</th>
				</tr>
			</thead>
			<tbody id="myTable">
				<c:set value="${sessionScope.issuedBookslist}" var="issuedBooklist"></c:set>
				<c:forEach items="${issuedBooklist}" var="row">
					<tr>
						<th scope="row">#</th>
						<td><c:out value="${row.getBookname()}"></c:out></td>
						<td><c:out value="${row.getAuthor()}"></c:out></td>
						<td><c:out value="${row.getEdition()}"></c:out></td>
						<td><form action="renewBook" method="post">
								<label for="issueddate">Issued Date :</label>
								<input type="date" id="issueddate" name="issuedDate" value="${row.getIssued_date()}" style="margin-right: 10px">
								<label for="returndate">Return Date :</label>
								<input type="date" id="returndate" name="returnDate" value="${row.getReturn_date()}" style="margin-right: 10px">
								<input type="hidden" name="unique_Id" value="${sessionScope.unique_id}"/>
								<input type="hidden" name="BookId" value="${row.getBook_id()}"/>
								<input type="hidden" name="renewBookId" value="${row.getIssued_book_id()}"/> 
								<input class="btn btn-outline-success" type="submit" value="Renew"/>
							</form></td>
						<td><form action="returnBook" method="post">
								<input type="hidden" name="uniqueId" value="${sessionScope.unique_id}"/> 
								<input type="hidden" name="BookId" value="${row.getIssued_book_id()}"/> 
								<input class="btn btn-outline-danger" type="submit" value="Return"/>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="modal-footer">
			<a type="button" class="btn btn-secondary"
				href="UserIndexStudent.jsp">Go Back</a>
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