<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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

.red-text {
	color: red;
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
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<%-- <li class="nav-item"> <a class="nav-link" href="/ispact/add?lotId=${lotId}">Add Actuals</a></li> --%>
					<li class="nav-item"><a class="nav-link" href="/lot/all">Inspection
							Lot</a></li>
					<li class="nav-item"><a class="nav-link" href="/home">Home</a>
					</li>
					<li class="nav-item"><a href="/" class="nav-link">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<form:form action="/lot/update-actual-result?lotId=${lotId}" modelAttribute="lot">
		<div class="container mt-2">
			<table
				class="table table-hover table-striped table-success w-75 mx-auto th 5">
				<thead>
					<tr>
						<th>LOT ID</th>
						<th>CH ID</th>
						<th>MIN MES</th>
						<th>MAX MES</th>
					</tr>

				</thead>

				<tbody>
					<c:forEach var="actuals" items="${actuals}">
						<tr>
							<td>${actuals.lot.lotId}</td>
							<td>${actuals.character.chId}</td>
							<td
								class="${actuals.minMes >= actuals.character.tolLl && actuals.minMes <= actuals.character.tolUl ?'text-success bold-text':'text-danger bold-text'}">${actuals.minMes}</td>
							<td
								class="${actuals.maxMes <= actuals.character.tolUl && actuals.maxMes >= actuals.character.tolLl ?'text-success bold-text':'text-danger bold-text'}">${actuals.maxMes}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tr>
					<td>Status</td>
					<td><form:select path="result">
							<form:option value="">select an option</form:option>
							<form:option value="PASS">PASS</form:option>
							<form:option value="FAIL">FAIL</form:option>
							<form:option value="ON HOLD">ON HOLD</form:option>
						</form:select></td>
					<td><input type="submit" value="Submit"
						class="btn btn-primary" /></td>
					<td></td>
				</tr>

			</table>
		</div>
	</form:form>

	<footer class="footer">
		<div class="container pt-2 pb-2">
			<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights reserved.</span>
		</div>
	</footer>
	<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
</body>

</html>