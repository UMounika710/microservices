<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lot Details</title>
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
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a href="/lot/add" class="nav-link">Add
							Lot</a></li>
					<li class="nav-item"><a href="/lot/search" class="nav-link">search
							Lot</a></li>
					<li class="nav-item"><a class="nav-link" href="/home">Home</a>
					</li>
					<li class="nav-item"><a href="/" class="nav-link">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container mt-2">
		<form id="searchForm" action="/lot/search/lotid" method="get">
			<input type="text" id="lotId" name="lotId"
				placeholder="Search by LOT ID">
			<!-- No submit button -->
		</form>
	</div>

	<script>
		$(document).ready(function() {
			$("#lotId").on("keyup", function(event) {
				if (event.key === "Enter") {
					$("#searchForm").submit(); // Trigger form submission on Enter key
				}
			});
		});
	</script>

	<div
		style="width: 35%; background-color: light; color: #721c24; margin-top: 20px; text-align: center;">
		${message}</div>

	<div class="container mt-2">

		<table
			class="table table-hover table-striped table-success w-100 mx-auto th 5">
			<thead>
				<tr>
					<th>LOT ID</th>
					<th>PLANT ID</th>
					<th>MATERIAL ID</th>
					<th>CREATED ON</th>
					<th>START DATE</th>
					<th>END DATE</th>
					<th>RESULT</th>
					<th>REMARK</th>
					<th>USER</th>
					<th>ACTION</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach var="lot" items="${lot}">
					<tr>
						<td>${lot.lotId}</td>
						<td>${lot.plant.plantId}</td>
						<td>${lot.material.id}</td>
						<td>${lot.createdOn}</td>
						<td>${lot.ispStDate}</td>
						<td>${lot.ispEnDate}</td>
						<td>${lot.result}</td>
						<td>${lot.remark}</td>
						<td>${lot.user.id }</td>
						<td>
						<a href="/lot/edit?id=${lot.lotId}" class="btn btn-link"><i
								class="fas fa-edit"></i></a> &nbsp &nbsp;
							<a href="#" class="btn btn-link"><i
								class="fa fa-trash" onclick= "confirmDelete('${vendor.vendorId}')" aria-hidden="true"></i> </a> 
								&nbsp; 
								<%-- <a href="/ispact/add?lotId=${lot.lotId}"
									class="btn btn-success btn-sm p-0.2">Add Actuals</a>
							</c:if>  --%>
							&nbsp;
								 <c:if test="${lot.getMaterial().getChList().size() != lot.getActuals().size() }">
								<a href="/ispact/add?lotId=${lot.lotId}"
									class="btn btn-success btn-sm p-0.2">Add Actuals</a>
							</c:if> 
							&nbsp;
							  <c:if
								test="${lot.result == 'ON HOLD'}">
								<a href="/ispact/review?lotId=${lot.lotId}"
									class="btn btn-success btn-sm p-0.2">Review</a>
							</c:if>
							 
							<a href="/ispact/view?lotId=${lot.lotId}"
							class="btn btn-success btn-sm p-0.2">View actuals</a>
						</td> &nbsp;
						<%-- <a
								href="/ispact/view?lotId=${lot.lotId}"
								class="btn btn-success btn-sm">View Actuals</a>
						</c:if> --%>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
	function confirmDelete(lotId){
		var confirmed = window.confirm("Are you sure you want to delete?");
		if (confirmed) {
			window.location.href = 'lot/delete?id=' + lotId;
		}
	}
	</script>
	<footer class="footer">
		<div class="container pt-2 pb-2">
			<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights reserved.</span>
		</div>
	</footer>

	<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
</body>

</html>