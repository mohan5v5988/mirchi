<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Type" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Types in database</h1><br />
	<% 
    	for(Type t : (ArrayList<Type>)pageContext.findAttribute("it")){
 	   		out.println("<br><strong>"+t.getType()+"</strong> by <i>"+t.getRate()+"</i>");
    	}
	%>

</body>
</html>