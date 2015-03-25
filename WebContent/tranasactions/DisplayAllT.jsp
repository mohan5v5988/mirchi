<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
 <%@ page import="model.Tranasactions" %>
 <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>All Tranasactions</title>
</head>
<body>
  <h1>Tranasactions are : </h1>
  <p>
    <% HashMap<String, Object> it = (HashMap<String, Object>)pageContext.findAttribute("it");
       for(Tranasactions t : (ArrayList<Tranasactions>)it.get("Tranasactions")){
    	   out.println("<br /> TID : "+t.getTid()+" Type : "+t.getType()+" NID : "+t.getNid()+
    			   " Date : "+t.getDate()+" No of bags :"+t.getC().getBags()+" Rate :"+
    			   t.getC().getRate()+" gunnie : "+t.getC().getGunnie()+" revaze : "+
    			   t.getC().getRevaze()+" coolie : "+t.getC().getCoolie()+" railway : "+
    			   t.getC().getRailway()+" vat : "+t.getC().getVat()+" total amount : "+t.getC().getTotam());
    	   out.println("<br />");
       }
    %>
  </p>
</body>
</html>