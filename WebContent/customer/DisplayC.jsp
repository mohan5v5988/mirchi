<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@ page import="model.Customer" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Single Customers</title>
</head>
<body>
  <h1>Customer are : </h1>
  <p>
    <% Customer c = (Customer)pageContext.findAttribute("it");
    	   out.println("<br> NID :"+c.getNid()+" Name : "+c.getName()+" Email : "
       		+c.getEmail()+" Number : "+c.getPnumber()+" Address : "+c.getAddress());
    %>
  </p>
</body>
</html>