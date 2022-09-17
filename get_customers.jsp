<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display customers</title>
</head>
<body>
 <%@page import="jsp_azure_test.DataHandler"%>
 <%@page import="java.sql.ResultSet"%>
 <%
 // Instantiating the data handler here, and get all the customers from the database
 final DataHandler handler = new DataHandler();
 final ResultSet cust = handler.getAllCustomers();
 %>
 <!-- The table for displaying all the customer records -->
 <table cellspacing="2" cellpadding="2" border="1">
 <tr> <!-- The table headers row -->
 <td align="center">
 <h4>Name</h4>
 </td>
 <td align="center">
 <h4>Address</h4>
 </td>
 <td align="center">
 <h4>Category</h4>
 </td>
 </tr>
 <%
 while(cust.next()) { // For each customer record returned, extracting the attribute values for every row returned

 final String name = cust.getString("name");
 final String address = cust.getString("address");
 final String category = cust.getString("category");

 out.println("<tr>"); // Start printing out the new table row
 out.println( // Print each attribute value
 "<td align=\"center\">" + name +
 "</td><td align=\"center\"> " + address +
 "</td><td align=\"center\"> " + category + "</td>");
 out.println("</tr>");
 }
 %>
 </table>
</body>
</html>