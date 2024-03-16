<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Material Entry</title>
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
                    <a class="nav-link" href="/material/all">View All Materials</a>
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
<h3 class="text-center mt-4 mb-3">
${material.id != null ? 'Update Material Details' : 'Create a Material'} 
</h3>
</div>
	<div class="container" mt-2>

		<!--  success or failure message region after insertion or deletion -->
		<div class="w-25 p-2 rounded font-weight-bold" style="color: green; text-align:center; font-weight: bold;">
	  ${succMsg} ${errMsg}
	 </div>


		<form:form action="savematerial" method="POST" modelAttribute="material">
			
			<table class="table mt-3 w-50 table-light mx-auto table-bordered font-weight-bold">
				<thead>
					<tr>
						<td>Material Id<span class="required"> *</span></td>
						<td><form:input path="id" autofocus="autofocus" readonly="${ not empty material.id }" placeholder="enter material id" required="required" /></td>
					</tr>
					<tr>
						<td>Material Description<span class="required"> *</span></td>
						<td><form:input path="descpt" placeholder="enter material desc" required="required" /></td>
					</tr>
					<tr>
						<td>Material Type<span class="required"> *</span></td>
						<td><form:input path="type"
								placeholder="enter material type" required="required" /></td>
					</tr>
					<tr>
						<td>Price<span class="required"> *</span></td>
						<td><form:input path="price"
								placeholder="enter material price" required="required" /></td>
					</tr>
					<tr>
						<td>Currency Key<span class="required"> *</span></td>
						<td><form:input path="ck" placeholder="enter currency key"
								required="required" /></td>
					</tr>
					<tr>
						<td colspan="3" class="text-center"><input type="submit" value="Save Material"
							class="btn btn-primary" />
							<a href="/material/all" class="btn btn-secondary">Cancel</a>
							</td>
					</tr>
				</thead>
			</table>

		</form:form>

	</div>
</body>
<footer class="footer">
			<div class="container pt-2 pb-2">
				<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights
					reserved.</span>
			</div>
		</footer>
</html>