<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.isi.manager.SessionManager"%>
<%@page import="com.isi.entity.*"%>
<%@page import="java.util.*"%>
<%
	User user = null;
if (!SessionManager.checkSessionExistsOrNot(request, "User")) {
	request.getRequestDispatcher("LoginServlet").forward(request, response);
}
user = (User) SessionManager.findSpecificSessionValue(request, "User");

HashMap<Integer, Cart> cartList = request.getAttribute("cartList") != null
		? (HashMap<Integer, Cart>) request.getAttribute("cartList")
		: null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/home.css">
<title>Cart</title>
</head>
<body>
	<header class="header">
		<h1 class="logo">
			<a href="#">Shopping App</a>
		</h1>
		<ul class="main-nav">
			<li><a href="LoginServlet">Welcome <%=user.getUserName()%>!!
			</a></li>
			<li><a href="HomeServlet" style="color: green;">Home</a></li>
			<li><a href="CartServlet" style="color: green;">Cart</a></li>
			<li><a href="HomeServlet?logout=true">Logout</a></li>
		</ul>
	</header>
	<%
		if (cartList != null && cartList.size() > 0) {
	%>
	<div>
		<br> <a href="CartServlet?IsCheckOut=true"><input
			type="button" Value="Check out" class="button" /> </a><br> <br>
	</div>
	<div>
		<label>Total Price of all products in cart: <%
			float totalFinalPrice = 0;

		for (Cart c : cartList.values()) {
			totalFinalPrice += c.getFinalProductPrice();
		}
		%><%=totalFinalPrice%> $
		</label>
	</div>
	<%
		} else {
	if (request.getParameter("IsCheckOut") != null && Boolean.parseBoolean(request.getParameter("IsCheckOut")) == true) {
	%>
	<h2>Checked out successfully!</h2>
	<%
		} else {
	%>
	<h2>No Products in cart!!</h2>
	<%
		}
	}
	%>
	<div class="main-content">
		<%
			int counter = 0;
		if (cartList != null && cartList.size() > 0) {
		%>
		<div class="row">
			<%
				for (Cart c : cartList.values()) {
				counter++;
				Product p = c.get_Product();
			%>

			<div class="column">
				<div class="card">
					<img src="https://www.w3schools.com/w3images/jeans3.jpg"
						alt="Denim Jeans" style="width: 100%">
					<h3><%=p.getProductName()%></h3>
					<p class="price">
						$<%=c.getFinalProductPrice()%></p>
					<p><%=p.getDescription()%></p>
					<p>
						Quantity:
						<%=c.getQtyOrdered()%></p>
					<p>
						<a href="CartServlet?cid=<%=c.getId()%>">
							<button>Remove Product</button>
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