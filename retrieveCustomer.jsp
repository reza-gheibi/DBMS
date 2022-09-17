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
 
 //Instantiating the data handler and getting all the customers in given category range from the database
 final DataHandler hand = new DataHandler();
 String lb = request.getParameter("ll");
 String ub = request.getParameter("ul");
 int lll = Integer.parseInt(lb);
 int uuu = Integer.parseInt(ub);
 final ResultSet cust1 = hand.getCustomers(lll,uuu);
 %>
 
 <!-- Table for displaying all the customer records retrieved -->
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
 while(cust1.next()) { //For each customer record returned extracting the attribute values

 	final String name = cust1.getString("name");
 	final String address = cust1.getString("address");
 	final String category = cust1.getString("category");

 	out.println("<tr>"); // Start printing a new row in the table
 	out.println( // Printing each attribute value for a cutomer
 	"<td align=\"center\">" + name +
 	"</td><td align=\"center\"> " + address +
 	"</td><td align=\"center\"> " + category + "</td>");
 	out.println("</tr>");
 }
 %>
 </table>
</body>
</html>