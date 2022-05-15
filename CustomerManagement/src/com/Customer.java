package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Customer {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrocustomer?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCustomer(String name, String address, String email, String contactnumber)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into customer1(`ID`,`name`,`address`,`email`,`contactnumber`)" + " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, email);
			 preparedStmt.setString(5, contactnumber);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newCustomer = readCustomer(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readCustomer()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Customer Name</th><th>Address</th><th>Email</th><th>Contact Number</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from customer1";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String ID = Integer.toString(rs.getInt("ID"));
				 String name = rs.getString("name");
				 String address = rs.getString("address");
				 String email = rs.getString("email");
				 String contactnumber = rs.getString("contactnumber");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidIDUpdate\' name=\'hidIDUpdate\' type=\'hidden\' value=\'" + ID + "'>" 
							+ name + "</td>"; 
				output += "<td>" + address + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + contactnumber + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-cusid='" + ID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Customer.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateCustomer(String ID, String name, String address, String email, String contactnumber)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE customer1 SET name=?,address=?,email=?,contactnumber=?"  + "WHERE ID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, name);
			 preparedStmt.setString(2, address);
			 preparedStmt.setString(3, email);
			 preparedStmt.setString(4, contactnumber);
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newCustomer = readCustomer();    
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Customer.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteCustomer(String ID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from customer1 where ID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newCustomer = readCustomer();    
			output = "{\"status\":\"success\", \"data\": \"" +  newCustomer + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Customer.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
