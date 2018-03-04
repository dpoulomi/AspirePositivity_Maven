<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>logout Page</title>
</head>
<body>
	<%     
         session.removeAttribute("userId");
         session.removeAttribute("password");
         session.invalidate();
     %>
	<center>
		<h1>You have successfully logged out</h1>
		To login again <a href="login.jsp">click here</a>.
	</center>
</body>
</html>
