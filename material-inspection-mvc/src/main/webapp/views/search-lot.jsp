<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" />
<style>
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
<title>lot search</title>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container mt-2">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					 <li class="nav-item"><a class="nav-link" href="/lot/all">Inspection Lot</a></li>
					<li class="nav-item"><a class="nav-link" href="/home">Home</a>
					</li>
					<li class="nav-item"><a href="/" class="nav-link">Logout</a></li>
				</ul>

			</div>
		</div>
	</nav>
	<div>
<h3 class="text-center mt-4 mb-3">
LOT DETAILS 
</h3>
</div>
	<form:form action="/lot/searchlot/result" method="POST" modelAttribute="searchLot">
		<table class="table mt-3 w-75 table-bordered mx-auto font-weight-bold">
			<thead>
				<tr>
					<td>Inspection Date</td>
					<td><form:input type="date" path="fromDate" id="fromDate"
							autofocus="autofocus" /> to <form:input type="date" id="toDate"
							path="toDate" autofocus="autofocus" /></td>
							<td></td>
				</tr>
				<tr>
					<td>Material Number</td>
					<td><form:input path="materialId" id="materialId"
							placeholder="enter mat id" /></td>
					<td><form:errors path="materialId" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Plant Id</td>
					<td><form:input path="plantId" placeholder="enter plant id" /></td>
					<td><form:errors path="plantId" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Status</td>
					<td><select class="form-select" id="status" name="status">
							<option value="">select an option</option>
							<option value="PASS">PASS</option>
							<option value="FAIL">FAIL</option>
							<option value="ON HOLD">ON HOLD</option>
							<option value="UNDER PROCESS">UNDER PROCESS</option>
					</select></td>
					<td><form:errors path="status" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Search"
						class="btn btn-primary" /></td>
					<td></td>
				</tr>
			</thead>
		</table>
	</form:form>
	<footer class="footer">
		<div class="container pt-2 pb-2">
			<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights reserved.</span>
		</div>
	</footer>

	<script>
		document.getElementById("fromDate").addEventListener("change",
				validateDates);
		document.getElementById("toDate").addEventListener("change",
				validateDates);

		function validateDates() {
			var fromDate = new Date(document.getElementById("fromDate").value);
			var toDate = new Date(document.getElementById("toDate").value);

			var dateDifference = (toDate - fromDate) / (1000 * 60 * 60 * 24);
			if (dateDifference > 90) {
				alert("please select valid dates");
				fromDate.value = "";
				toDate.value = "";
			}
			if (fromDate >= toDate) {
				alert("select date ranges in 90 days period");

				toDate.value = "";
			}
		}
	</script>
</body>
</html>