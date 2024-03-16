<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Material Inspection charateristics Details</title>
</head>
<body>
	<link rel="stylesheet"
		href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
<style>
.container {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.app-link {
	margin: 3%;
}
.footer {
	position: fixed;
	bottom: 0;
	width: 100%;
	background-color: #343a40; /* Adjust the color as needed */
	color: white;
	text-align: center;
	padding: 10px 0;
}
</style>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container mt-2">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
            <li class="nav-item">
                    <a class="nav-link" href="/material/all">Material</a>
                </li>
               <!--  <li class="nav-item">
                    <a class="nav-link" href="/mtch/add">Add Character</a>
                </li> -->
                <li class="nav-item">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <li class="nav-item">
                 <a href="/" class="nav-link">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div>
<h3 class="text-center mt-4 mb-3">
MATERIAL CHARACTERISTICS LIST 
</h3>
</div>
<h2 class="text-center">Characteristics of ${materialName}</h2>
	<div class="container mt-2">
	
		<table class="table table-hover table-striped table-success w-75 mx-auto th 5">
			<thead>
				<tr>
					<th>CH DESC</th>
					<th>TOL UL</th>
					<th>TOL LL</th>
					<th>UOM</th>
					<th>ACTION</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="mtch" items="${mtch}">
					<tr>
						<td>${mtch.chDesc}</td>
						<td>${mtch.tolUl}</td>
						<td>${mtch.tolLl}</td>
						<td>${mtch.uom}</td>
						<td>
						<a href="edit?id=${mtch.chId}" class="btn btn-link"><i class="fas fa-edit"></i></a>
						&nbsp &nbsp;
						<a href="/mtch/delete?id=${mtch.chId}" class="btn btn-link"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<footer class="footer">
			<div class="container pt-2 pb-2">
				<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights
					reserved.</span>
			</div>
		</footer>
	<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
</body>

</html>