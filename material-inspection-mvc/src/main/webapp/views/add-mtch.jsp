<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
<title>Material Inspection Characters Entry</title>
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
                    <a class="nav-link" href="/material/all">Material</a>
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
${mtch.chId != null ? 'Update Material Characteristics Details' : 'Add  Material Character'} 
</h3>
   <div class="container mt-2">
	
	<!--  success or failure message region after insertion or deletion -->	
	 <div class="w-25 p-2 rounded font-weight-bold" style="color: green; text-align:center; font-weight: bold;">
	  ${succMsg} ${errMsg}
	 </div>
	

	<form:form action="savemtch" method="POST" modelAttribute="mtch">
	    <form:hidden path="chId"/>
	    <form:input type="hidden" path = "material" value="${materialId }"/>
	    
		<table class="table mt-3 w-50 table-light mx-auto table-bordered font-weight-bold">
		 <thead>
			<tr>
				<td>Character Description<span class="required"> *</span></td>
				<td><form:input path="chDesc" autofocus="autofocus" placeholder="enter character desc" required="required"/></td>
			</tr>
			<tr>
				<td>Tolerence Upper Limit<span class="required"> *</span></td>
				<td><form:input path="tolUl" placeholder="enter tol ul" required="required"/></td>
			</tr>
			<tr>
				<td>Tolerence Lower Limit<span class="required"> *</span></td>
				<td><form:input path="tolLl" placeholder="enter tol ll" required="required"/></td>
			</tr>
			<tr>
				<td>UOM<span class="required"> *</span></td>
				<td><form:input path="uom" placeholder="enter uom" required="required"/></td>
			</tr>
			<%-- <tr>
				<td>Mat Id<span class="required"> *</span></td>
				<td><form:input path="material.id"  placeholder="enter matId" required="required"/></td>
			</tr> --%>
			<tr>
				<td colspan="3" class="text-center"><input type="submit" value="Save Character" class="btn btn-primary"/>
				<!-- <a href="/material/all" class="btn btn-secondary">Cancel</a> -->
			
				<a href="javascript:history.back()" class="btn btn-secondary">Cancel</a>
			
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