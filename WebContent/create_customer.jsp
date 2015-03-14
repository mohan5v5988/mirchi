<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a Customer</title>
</head>
<body>
	<form method="POST" action="rest/customer">
		NID : <input type="text" name="nid"><br />
		Name: <input type="text" name="Name"><br />
		Phone Number : <input type="int" name="Number"><br />
		Address : <input type="text" name="Address"><br />
		<input type="submit">
	</form>
</body>
</html>