<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Plant Entry</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container mt-2">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/plant/all">View All Plants</a>
                </li>
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
${plant.plantId != null ? 'Update Plant Details' : 'Create a Plant'} 
</h3>
</div>
	<div class="container">

		<!--  success or failure message region after insertion or deletion -->
		 <div class="w-25 p-2 rounded font-weight-bold" style="color: green; text-align:center; font-weight: bold;">
	  ${succMsg} ${errMsg}
	 </div>


		<form:form action="saveplant" method="POST" modelAttribute="plant">
			
			<table class="table mt-4 w-50 table-light mx-auto table-bordered font-weight-bold">
				<thead>
					<tr>
						<td>Plant Id<span class="required"> *</span></td>
					    <td><form:input path="plantId" readonly="${not empty plant.plantId and plant.plantId ne null}" autofocus="autofocus" placeholder="enter plant id" required="required"  /></td>
					    <td><form:errors path="plantId" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td>Plant Name<span class="required"> *</span></td>
						
						<td><form:input path="plantName" placeholder="enter plant name" required="required" /></td>
						<td><form:errors path="plantName" cssClass="error"></form:errors></td>
						
					</tr>
					<tr>
						<td>Location<span class="required"> *</span></td>
						<td><form:input path="location"
								placeholder="enter plant location" required="required" /></td>
						<td><form:errors path="location" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td>Plant Manager<span class="required"> *</span></td>
						<td><form:input path="plantManager"
								placeholder="enter plant manager name" required="required" /></td>
						<td><form:errors path="plantManager" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td>Contact<span class="required"> *</span></td>
						<td><form:input path="contact" placeholder="enter contact"
								required="required" /></td>
						<td><form:errors path="contact" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td colspan="3" class="text-center"><input type="submit" value="Save Plant"
							class="btn btn-primary" />
							<a href="/plant/all" class="btn btn-secondary">Cancel</a>
							</td>
					</tr>
				</thead>
			</table>

		</form:form>

	</div>
	<footer class="footer">
			<div class="container pt-2 pb-2">
				<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights
					reserved.</span>
			</div>
		</footer>
</body>
</html>