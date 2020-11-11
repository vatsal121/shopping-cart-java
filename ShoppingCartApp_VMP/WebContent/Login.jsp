<%@page import="com.isi.manager.SessionManager"%>
<%@page import="com.isi.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if (SessionManager.checkSessionExistsOrNot(request, "User")) {
	User user = (User) SessionManager.findSpecificSessionValue(request, "User");
	if (user.getRole().equalsIgnoreCase("admin")) {
		request.getRequestDispatcher("AdminServlet").forward(request, response);
	} else {
		request.getRequestDispatcher("HomeServlet").forward(request, response);
	}

}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Login</title>
</head>
<body>

	<div class="container">
		<form action="LoginServlet" method="post">
			<h1>Sign in</h1>
			<div class="form-content">
				<input id="user-name" name="username" placeholder="User name" /> 
				<input
					id="password" name="password" placeholder="Password"
					type="password" required="required"> <br>
				<%
					if (request.getAttribute("userError") != null && (boolean) request.getAttribute("userError")) {
				%>
				<div class="signup-message" style="color: red;">
					<span>Incorrect user name or password!.</span>
				</div>
				<br>
				<%
					}
				%>

				<input type="submit" Value="Log In" class="button" /> <br>
				<div class="signup-message">
					<a href="Register.jsp">New user? Click here to Register!!</a>
				</div>
			</div>
		</form>
	</div>

</body>
</html>