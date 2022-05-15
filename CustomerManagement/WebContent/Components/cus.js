$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateCustomerForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "CustomerService",  
			type : type,  
			data : $("#formCustomer").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onCustomerSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onCustomerSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divCustomerGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidIDSave").val("");  
	$("#formCustomer")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(1)').text());
	$("#email").val($(this).closest("tr").find('td:eq(2)').text());
	$("#contactnumber").val($(this).closest("tr").find('td:eq(3)').text());     
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "CustomerService",   
		type : "DELETE",   
		data : "ID=" + $(this).data("cusid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onCustomerDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onCustomerDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divCustomerGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateCustomerForm() 
{  
	// CUSTOMER NAME  
	if ($("#name").val().trim() == "")  
	{   
		return "Insert Customer Name.";  
	} 
	
	// ADDRESS------------------------  
	if ($("#address").val().trim() == "")  
	{   
		return "Insert Address.";  
	}
	
	// EMAIL------------------------  
	if ($("#email").val().trim() == "")  
	{   
		return "Insert Email.";  
	} 
	
	//CONTACT NUMBER-------------------------------
	 var tmpContact_Number = $("#contactnumber").val().trim();
	if (!$.isNumeric(tmpContact_Number)) 
	 {
	 return "Insert Contact Number.";
	 }

	return true; 
}