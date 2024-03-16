<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" />
<style>
 a:hover{  
  box-shadow:0 0 10px black;
  text-decoration:none;
  border-radius:10px;
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
<title>Vendor Entry</title>
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
                    <a class="nav-link" href="/vendor/all">View All Vendors</a>
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
${vendor.vendorId != null ? 'Update Vendor Details' : 'Create a Vendor'} 
</h3>
</div>
   <div class="container">
	
	<!--  success or failure message region after insertion or deletion -->	
	 <div class="w-25 p-2 rounded font-weight-bold" style="color: green; text-align:center; font-weight: bold;">
	  ${succMsg} ${errMsg}
	 </div>
	

	<form:form action="savevendor" method="POST" modelAttribute="vendor">
	    <form:hidden path="vendorId"/>
		<table class="table mt-3 w-75 table-light mx-auto table-bordered font-weight-bold">
		 <thead>
			<tr>
				<td>Vendor Name</td>
				<td><form:input path="vendorName" readonly="${not empty vendor.vendorId and vendor.vendorId ne null}" autofocus="autofocus" placeholder="enter vendor name"/></td>
				<td><form:errors path="vendorName" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Vendor Type</td>
				<td><form:input path="vendorType" placeholder="enter vendor type"/></td>
				<td><form:errors path="vendorType" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><form:input path="location" placeholder="enter vendor location"/></td>
				<td><form:errors path="location" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><form:input path="email" placeholder="enter vendor email"/></td>
				<td><form:errors path="email" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td colspan="3" class="text-center"><input type="submit" value="Save Vendor" class="btn btn-primary"/>
				<a href="/vendor/all" class="btn btn-secondary">Cancel</a>
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