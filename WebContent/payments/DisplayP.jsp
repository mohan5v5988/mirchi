<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@ page import="model.Payments" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>All Payments</title>
</head>
<body>
  <h1>Payments are : </h1>
  <p>
    <% HashMap<String, Object> it = (HashMap<String, Object>)pageContext.findAttribute("it");
       for(Payments p : (ArrayList<Payments>)it.get("Payments")){
    	   out.println("<br> ID : "+p.getId()+" NID : "+p.getNid()+" Amount : "+
       					p.getAmount()+" Date : "+p.getDate());
       }
    %>
  </p>
</body>
</html>