<%@page import="java.util.*"%>
<%@page import="com.isi.manager.SessionManager"%>
<%@page import="com.isi.entity.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User user = null;
if (!SessionManager.checkSessionExistsOrNot(request, "User")) {
	request.getRequestDispatcher("LoginServlet").forward(request, response);
}
user = (User) SessionManager.findSpecificSessionValue(request, "User");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/home.css">
</head>
<body>
	<header class="header">
		<h1 class="logo">
			<a href="#">Shopping App</a>
		</h1>
		<ul class="main-nav">
			<li><a href="#" style="color: green;">Welcome <%=user.getUserName()%>!!
			</a></li>
			<li><a href="AdminServlet" style="color: green;">Home</a></li>
			<li><a href="AdminServlet?logout=true">Logout</a></li>
		</ul>
	</header>
	
	<div class="main-content">
	<h1>Welcome to Admin Dashboard!</h1>
	</div>
</body>
</html>