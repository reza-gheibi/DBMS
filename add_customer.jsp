<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add customer show status </title>
</head>
<body>

<%@page import="jsp_azure_test.DataHandler"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Array"%>
<%
// The handler is in charge of establishing the connection.
DataHandler handler = new DataHandler();

// Get the attribute values passed from the input form.
String name = request.getParameter("name");
String address = request.getParameter("address");
String category = request.getParameter("category");

// Check if the user has not filled out all the data. If so, redirect to data entry form 
if (name.equals("") || address.equals("") || category.equals("")) {
	response.sendRedirect("enter_customer_form.jsp");
}

else {
	int cat = Integer.parseInt(category);

	// Performing the query with the data from the form
	boolean success = handler.addCustomer(name, address, cat);
	if (!success) { // Something went wrong
	%>
	<h2>There was a problem inserting the customer</h2>
	<%
	} 
	else { // Confirming success to the user
	%>
	<h2>The new customer with</h2>
	<ul>
	<li>Name: <%=name%></li>
	<li>Address: <%=address%></li>
	<li>Category: <%=cat%></li>
	</ul>
	<h2>has been successfully inserted.</h2>
	<a href="get_customers.jsp">See all customers</a>
	<%
	}
}
%>
</body>
</html>