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

ArrayList<Product> productList = request.getAttribute("productList") != null
		? (ArrayList<Product>) request.getAttribute("productList")
		: null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/home.css">
<title>Home</title>
</head>
<body>
	<header class="header">
		<h1 class="logo">
			<a href="#">Shopping App</a>
		</h1>
		<ul class="main-nav">
			<li><a href="#" style="color: green;">Welcome <%=user.getUserName()%>!!
			</a></li>
			<li><a href="HomeServlet" style="color: green;">Home</a></li>
			<li><a href="CartServlet">Cart</a></li>
			<li><a href="HomeServlet?logout=true">Logout</a></li>
		</ul>
	</header>
	<div class="main-content">
		<%
			int counter = 0;
		if (productList != null && productList.size() > 0) {
		%>
		<div class="row">
			<%
				for (Product p : productList) {
				counter++;
			%>

			<div class="column">
				<div class="card">
					<img src="https://www.w3schools.com/w3images/jeans3.jpg"
						alt="Denim Jeans" style="width: 100%">
					<h3><%=p.getProductName()%></h3>
					<p class="price">
						$<%=p.getPrice()%></p>
					<p><%=p.getDescription()%></p>
					<p>
						<a href="HomeServlet?pid=<%=p.getId()%>">
							<button>Add to Cart</button>
						</a>
					</p>
				</div>
			</div>

			<%
				if (counter == 4) {
			%>
		</div>
		<div class="row">
			<%
				counter = 0;
			}
			}
			}
			%>


		</div>
</body>
</html>