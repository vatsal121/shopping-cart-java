<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Register</title>
</head>
<body>
	<div class="container">
		<form action="RegisterServlet" method="post">
			<h1>Register</h1>
			<div class="form-content">
				<input id="user-name" name="username" placeholder="User name"
					type="text" required="required"> <input id="password"
					name="password" placeholder="Password" type="password"
					required="required"> <input id="confirmpassword"
					name="confirmpassword" placeholder="ConfirmPassword"
					type="password" required="required"> <br>
				<%
					if (request.getAttribute("userExists") != null && (boolean) request.getAttribute("userExists")) {
				%>
				<div class="signup-message" style="color: red;">
					<span>User name already exists!</span>
				</div>
				<br>
				<%
					}
				%>
				<%
					if (request.getAttribute("registeredSuccessfully") != null
						&& (boolean) request.getAttribute("registeredSuccessfully")) {
				%>
				<div class="signup-message" style="color: green;">
					<span>Registered Successfully!</span>
				</div>
				<br>
				<%
					}
				%>
				<input type="submit" Value="Register" class="button" onclick="return Validate()" /> <br>
				<div class="signup-message">
					<a href="Login.jsp">Already an user? Click here to Login!!</a>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function Validate() {
			var password = document.getElementById("password").value;
			var confirmPassword = document.getElementById("confirmpassword").value;
			if (password != confirmPassword) {
				alert("Passwords do not match.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>