<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Material Inspection Result</title>
</head>
<body>
	<link rel="stylesheet"
		href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />

	<div class="container mt-5">
		<table class="table table-hover table-striped table-success w-100 mx-auto th 5">
			<thead>
				<tr>
					<th>LOT ID</th>
					<th>MATERIAL</th>
					<th>VENDOR</th>
					<th>PLANT</th>
					<th>RESULT</th>
					<th>REMARK</th>
					<th>USER ID</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="searchResult" items="${searchResult}">
					<tr>
						<td>${searchResult.lotId}</td>
						<td>${searchResult.material.descpt}</td>
						<td>${searchResult.plant.plantId}</td>
						<td>${searchResult.result}</td>
						<td>${searchResult.remark}</td>
						<td>${searchResult.user.id}</td>
						<td>
						<a href="add" class="btn btn-link"><i class="fa fa-eye" aria-hidden="true" ></i></a>
						&nbsp &nbsp;
						<a href="edit?id=${vendor.vendorId}" class="btn btn-link"><i class="fas fa-edit"></i></a>
						&nbsp &nbsp;
						<a href="delete?id=${vendor.vendorId}" class="btn btn-link"><i class="fa fa-trash" aria-hidden="true"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script src="webjars/jquery/3.6.4/jquery.min.js"></script>
</body>

</html>