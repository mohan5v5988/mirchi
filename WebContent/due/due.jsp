<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@ page import="model.Due" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Due</title>
</head>
<body>
  <p>
    <% Due d = (Due)pageContext.findAttribute("it");
    	   out.println("<br> NID :"+d.getNid()+" Amount : "+d.getAmount());
    %>
  </p>
</body>
</html>