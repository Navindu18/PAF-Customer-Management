<%@ page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/cus.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Customer Management</h1>
				<form id="formCustomer" name="formCustomer" method="post" action="CustomerUI.jsp">  
					Customer Name:  
 	 				<input id="name" name="name" type="text"  class="form-control form-control-sm">
					<br>Address:   
  					<input id="address" name="address" type="text" class="form-control form-control-sm">   
  					<br>Email:   
  					<input id="email" name="email" type="text"  class="form-control form-control-sm">
					<br>Contact Number:   
  					<input id="contactnumber" name="contactnumber" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidIDSave" name="hidIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divCustomerGrid">
					<%
						Customer CustomerObj = new Customer();
									out.print(CustomerObj.readCustomer());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>