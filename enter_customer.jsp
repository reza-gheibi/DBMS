<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Query1:Enter a new customer</title>
</head>
<body>
<h2>Enter a new customer</h2>
<!--
Form for collecting user input.
Upon form submission, add_customer.jsp file will be invoked.
-->
<form action="add_customer.jsp">
<!-- The form is organized in an HTML table. -->
<table border=1>
<tr>
<th colspan="2">Enter new customer's data:</th>
</tr>
<tr>
<td>Customer's name:</td>
<td><div style="text-align: center;">
<input type=text name=name>
</div></td>
</tr>

<tr>
<td>Customer's address:</td>
<td><div style="text-align: center;">
<input type=text name=address>
</div></td>
</tr>

<tr>
<td>Category (1-10):</td>
<td><div style="text-align: center;">
<input type=text name=category>
</div></td>
</tr>
<tr>
<td><div style="text-align: center;">
<input type=reset value=Clear>
</div></td>
<td><div style="text-align: center;">
<input type=submit value=Insert>
</div></td>
</tr>
</table>
</form>
</body>
</html>