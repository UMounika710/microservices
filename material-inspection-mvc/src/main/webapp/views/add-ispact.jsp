<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" />
<style>
a:hover {
	box-shadow: 0 0 10px black;
	text-decoration: none;
	border-radius: 10px;
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
<title>Inspection Actuals Entry</title>
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
					<li class="nav-item"><a class="nav-link" href="/lot/all">Inspection
							Lot</a></li>
					<li class="nav-item"><a class="nav-link" href="/home">Home</a>
					</li>
					<li class="nav-item"><a href="/" class="nav-link">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container mt-2">

		<!--  success or failure message region after insertion or deletion -->
		<div class="w-25 p-2 rounded font-weight-bold"
			style="color: green; text-align: center; font-weight: bold;">
			${succMsg} ${errMsg}</div>

		<form:form action="/ispact/saveispact" method="POST"
			modelAttribute="ispact" onsubmit="return validateForm()">

			<form:input type="hidden" path="lot" value="${lot.lotId}" />
			<table
				class="table mt-3 w-75 table-light mx-auto table-bordered font-weight-bold">
				<thead>

					<tr>
						<td>Material Character</td>
						<td>
						<select name="character">
								<c:forEach var="characters" items="${materialCharactersList}">
									<option path="character" value="${characters.chId}">${characters.chDesc}</option>
								</c:forEach>
						</select>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>MIN MES</td>
						<td><form:input path="minMes" placeholder="enter min mes"
								id="minMes" /></td>
						<td><form:errors path="minMes" cssclass="error"></form:errors>
							</form></td>
					</tr>
					<tr>
						<td>MAX MES</td>
						<td><form:input path="maxMes" placeholder="enter max mes"
								id="maxMes" /></td>
						<td><form:errors path="maxMes" cssClass="error"></form:errors></td>
					</tr>
					<!-- <tr>
						<td colspan="3">
							Display validation error message here
							<div id="validationError" style="color: red;"></div>
						</td>
					</tr> -->
					<tr>
						<td colspan="3" class="text-center"><input type="submit"
							value="Save Actuals" class="btn btn-primary" />
							<a href="/lot/all" class="btn btn-secondary">Cancel</a>
							</td>
					</tr>
				</thead>
			</table>

		</form:form>

	</div>
	<script>
		function validateForm() {
			var minMes = document.getElementById("minMes").value;
			var maxMes = document.getElementById("maxMes").value;

			if (parseFloat(minMes) > parseFloat(maxMes)) {
				alert("Min MES should be less than or equal to Max MES.");
				return false; // Prevent form submission
			}

			return true; // Allow form submission
		}
	</script>

</body>
<footer class="footer">
	<div class="container pt-2 pb-2">
		<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights reserved.</span>
	</div>
</footer>
</html>