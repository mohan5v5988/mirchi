<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@ page import="model.Type" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>All Types</title>
</head>
<body>
  <h1>Types are : </h1>
  <p>
    <% HashMap<String, Object> it = (HashMap<String, Object>)pageContext.findAttribute("it");
       for(Type t : (ArrayList<Type>)it.get("Type")){
    	   out.println("<br><b>"+t.getType()+"</b> = <i>"+t.getRate()+"</i>");
       }
    %>
  </p>
</body>
</html>