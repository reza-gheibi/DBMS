<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Query13:Retrieve customers in a category range</title>
</head>
<body>
<h2>Retrieve customers in a category range</h2>
<!--
Form for collecting user input. Upon form submission, retrieve_customer.jsp file will be invoked.
-->
<form action="retrieve_customer.jsp">
<!-- The form organized in an HTML table -->
<table border=1>
<tr>
<th colspan="2">Enter range for customer category:</th>
</tr>
<tr>
<td>Lower limit for category:</td>
<td><div style="text-align: center;">
<input type=text name=ll>
</div></td>
</tr>

<tr>
<td>Upper limit for category:</td>
<td><div style="text-align: center;">
<input type=text name=ul>
</div></td>
</tr>

<tr>
<td><div style="text-align: center;">
<input type=reset value=Clear>
</div></td>
<td><div style="text-align: center;">
<input type=submit value=Submit>
</div></td>
</tr>
</table>
</form>
</body>
</html>