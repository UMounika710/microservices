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
</style>
<title>Lot Update</title>
</head>
<body>
   <div class="container">
	<h4 class="bg-primary text-center p-1 rounded mt-1">Add Lot</h4>
	<a href="all" class="btn btn-link">View All Lots</a>
	
	<!--  success or failure message region after insertion or deletion -->	
	 <div class="w-25 p-2 rounded font-weight-bold">
	  ${succMsg} ${errMsg}
	 </div>
	

	<form:form action="updatelot" method="POST" modelAttribute="lot">
	   
		<table class="table mt-3 w-75 table-dark table-bordered font-weight-bold">
		 <thead>
			<tr>
				<td>Lot Id</td>
				<td><form:input path="lotId" autofocus="autofocus" placeholder="enter lot id"/></td>
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
				<td><form:errors path="vendor.vendorId" cssClass="error"></form:errors></td>
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
				<td>Isp_en_Dt</td>
				<td><form:input type="date" id="datepicker" name="selectedDate" path="ispEnDate" placeholder="enter date"/></td>
				<td><form:errors path="ispEnDate" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Result</td>
				<td><form:input path="result" placeholder="enter plant id"/></td>
				<td><form:errors path="result" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td>Remark</td>
				<td><form:input path="remark" placeholder="enter plant id"/></td>
				<td><form:errors path="remark" cssClass="error"></form:errors></td>
			</tr>
			
			<tr>
				<td></td>
				<td><input type="submit" value="Update Lot" class="btn btn-primary"/></td>
				<td></td>
			</tr>
		</thead>	
		</table>
			
	</form:form>
	
	</div>
</body>
</html>