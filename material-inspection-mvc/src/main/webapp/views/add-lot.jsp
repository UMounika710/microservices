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
<title>Lot Entry</title>
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
                  <a href="/lot/all" class="nav-link">Inspection Lot</a>
                </li>
                 <li class="nav-item">
                  <a href="/lot/search" class="nav-link">search Lot</a>
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
${lot.lotId != null ? 'Update Lot Details' : 'Create a Lot'} 
</h3>
</div>

   <div class="container" mt-2>
	
	<!--  success or failure message region after insertion or deletion -->	
	 <div class="w-25 p-2 rounded font-weight-bold" style="color: green; text-align:center; font-weight: bold;">
	  ${succMsg} ${errMsg}
	 </div>
	

	<form:form action="/lot/savelot" method="POST" modelAttribute="lot">
	   
		<table class="table mt-3 w-75 mx-auto table-light table-bordered font-weight-bold">
		 <thead>
			<tr>
				<td>Lot Id<span class="required"> *</span></td>
				<td><form:input path="lotId" readonly="${not empty lot.lotId and lot.lotId ne null}"  autofocus="autofocus" placeholder="enter lot id"/></td>
				<td><form:errors path="lotId" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Material Id</td>
				<td><form:input path="material.id" placeholder="enter mat id"/></td>
				<td><form:errors path="material.id" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Vendor Id</td>
				<td><form:input path="vendor.vendorId" placeholder="enter vendor id"/></td>
				<td ><form:errors path="vendor.vendorId" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Plant Id</td>
				<td><form:input path="plant.plantId" placeholder="enter plant id"/></td>
				<td><form:errors path="plant.plantId" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Created On</td>
				<td><form:input type="date" id="datepicker" name="selectedDate" path="createdOn" placeholder="enter date"/></td>
				<td><form:errors path="createdOn" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Isp_St_Dt</td>
				<td><form:input type="date" id="datepicker" name="selectedDate" path="ispStDate" placeholder="enter date"/></td>
				<td><form:errors path="ispStDate" cssClass="error"></form:errors></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Save Lot" class="btn btn-primary"/>
				<a class="btn btn-secondary" onclick="goBack()">Cancel</a>
				</td>
				<td></td>
			</tr>
		</thead>	
		</table>
			
	</form:form>
	
	</div>
	<script>
    function goBack() {
        window.history.back();
    }
</script>
	<footer class="footer">
			<div class="container pt-2 pb-2">
				<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights
					reserved.</span>
			</div>
		</footer>
	
</body>
</html>