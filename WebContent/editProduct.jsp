<%@page import="com.securde.bean.Client"%>
<%@page import="com.securde.bean.InventoryStaff"%>
<%@page import="com.securde.bean.StoreManager"%>
<%@page import="com.securde.bean.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Edit Product</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Slick -->
	<link type="text/css" rel="stylesheet" href="css/slick.css" />
	<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

	<!-- nouislider -->
	<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	
	<script src = "js/jquery-min.js"></script>
	<script src = "js/scripts.js"></script>

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

</head>

<body>
	<!-- HEADER -->
	<header>
		<!-- top Header -->
		
		<!-- /top Header -->

		<!-- header -->
		<div id="header">
			<div class="container">
				<div class="pull-left">
					<!-- Logo -->
					<div class="header-logo">
						<a class="logo" href="index.jsp">
							<img src="./img/Papema_Logo.png" alt="">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Search -->
					<div class="header-search">
						<form action = "search">
							<input class="input search-input" type="text" placeholder="Enter your keyword" name = "searchkey">
							<select class="input search-categories">
								<option value="0">By Name</option>
								<option value="1">By Tag</option>
							</select>
							<button type = 'submit' class="search-btn"><i class="fa fa-search"></i></button>
						</form>
					</div>
					<!-- /Search -->
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<strong class="text-uppercase">
								<% if (session.getAttribute("user") != null){ %>
										Welcome back!
								<%		if (session.getAttribute("userType") == "Client"){
											Client c = (Client) session.getAttribute("user");
											out.print(c.getUsername());
										} else if  (session.getAttribute("userType") == "Staff") {
											InventoryStaff i = (InventoryStaff) session.getAttribute("user");
											out.print(i.getUsername());
										} else if (session.getAttribute("userType") == "Manager") {
											StoreManager s = (StoreManager) session.getAttribute("user");
											out.print(s.getUsername());
										} else if (session.getAttribute("userType") == "Admin") {
											Admin a = (Admin) session.getAttribute("user");
											out.print(a.getUsername());
										}
								} else { %>
									My Account
								<% } %>
								<i class="fa fa-caret-down"></i></strong>
							</div>
							<%	if (session.getAttribute("user") == null) { %>
								<a href="login.jsp" class="text-uppercase">Login</a> / <a href="signup.jsp" class="text-uppercase">Join</a>
							<% } %>
							<ul class="custom-menu">
								<li><a href="#"><i class="fa fa-user-o"></i> My Account</a></li>
								<li><a href="#"><i class="fa fa-check"></i> Checkout</a></li>
								<% if (session.getAttribute("user") == null) { %>
									<li><a href="login.jsp"><i class="fa fa-unlock-alt"></i> Login</a></li>
									<li><a href="signup.jsp"><i class="fa fa-user-plus"></i> Create An Account</a></li>
								<% } else {
									if (session.getAttribute("userType") != "Client") { %>
									<li><a href="addproduct.jsp"><i class="fa fa-user-plus"></i> Add Product</a></li>
								<%	if (session.getAttribute("userType") == "Admin") { %>
									<li><a href="employeesignup.jsp"><i class="fa fa-user-plus"></i> Create An Employee Account</a></li>
								<% } %>	
							<% } %>
							<form method = "post" action = "logout">
								<li> <input class = "main-btn" type = "submit" value = "Logout"></li>
							</form>
						<% }	%>
						</ul>
						</li>
						<!-- /Account -->

						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-shopping-cart"></i>
									<span class="qty">3</span>
								</div>
								<strong class="text-uppercase">My Cart:</strong>
								<br>
								<span>35.20$</span>
							</a>
							<div class="custom-menu">
								<div id="shopping-cart">
									<div class="shopping-cart-list">
										<div class="product product-widget">
											<div class="product-thumb">
												<img src="./img/thumb-product01.jpg" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">$32.50 <span class="qty">x3</span></h3>
												<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
											</div>
											<button class="cancel-btn"><i class="fa fa-trash"></i></button>
										</div>
										<div class="product product-widget">
											<div class="product-thumb">
												<img src="./img/thumb-product01.jpg" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">$32.50 <span class="qty">x3</span></h3>
												<h2 class="product-name"><a href="#">Product Name Goes Here</a></h2>
											</div>
											<button class="cancel-btn"><i class="fa fa-trash"></i></button>
										</div>
									</div>
									<div class="shopping-cart-btns">
										<button class="main-btn">View Cart</button>
										<button class="primary-btn">Checkout <i class="fa fa-arrow-circle-right"></i></button>
									</div>
								</div>
							</div>
						</li>
						<!-- /Cart -->

						<!-- Mobile nav toggle-->
						<li class="nav-toggle">
							<button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
						</li>
						<!-- / Mobile nav toggle -->
					</ul>
				</div>
			</div>
			<!-- header -->
		</div>
		<!-- container -->
	</header>
	<!-- /HEADER -->

	<!-- NAVIGATION -->
	<hr>
	<!-- /NAVIGATION -->

	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="index.jsp">Home</a></li>
				<li class="active">Blank</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->
	
	<!-- BLANK SECTION -->
	
	
	<!-- /BLANK SECTION -- >
	
	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<form method = "post" action="commiteditproduct">
					<div class="section-title">
						<h3 class="title">Edit Product</h3>
					</div>
					<div class = "login-section">
						<input type="hidden" name="productid" value="<c:out value="${product.id}"/>" /><br>
						<strong>Name:</strong> <input class = "login-section-content" type="text" name="productName" value="<c:out value="${product.name}"/>" /><br>
						<strong>Price:</strong> <input class = "login-section-content" type = "number" name = "productPrice"  value="<c:out value="${product.price}"/>"required/><br>
						<input class = "main-btn login-section-content" type="submit" name="Update Product" value="Update Product">
					</div>
				</form>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- FOOTER -->
	<footer id="footer" class="section section-grey">
		<!-- container -->
		<div class="container">
			<!-- row -->
			
			<!-- /row -->
			<hr>
			<!-- row -->
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<!-- footer copyright -->
					<div class="footer-copyright">
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</div>
					<!-- /footer copyright -->
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</footer>
	<!-- /FOOTER -->

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>

</body>

</html>
