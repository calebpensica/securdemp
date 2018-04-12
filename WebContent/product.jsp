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

	<title>Product Page</title>

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
		<header>
			<%@include file="includes/header.jsp" %>
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
				<li><a href="showproducts.jsp">Products</a></li>
				<li class="active">${product.name}</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!--  Product Details -->
				<div class="product product-details clearfix">
					<div class="col-md-6">
						<div id="product-main-view">
							<div class="product-view">
								<img src="./img/main-product01.jpg" alt="">
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="product-body">
							<h2 class="product-name">${product.name}</h2>
							<h3 class="product-price">Php ${product.price}0</h3>
							
							<p><strong>Availability:</strong> In Stock</p>
							<p>Product Description</p>
						

							<div class="product-btns">
							<%if (session.getAttribute("userType") != "Staff" &&  session.getAttribute("userType") != "Manager" && session.getAttribute("userType") != "Admin"){  %>
								<form method = "post" action = "buyproduct">
									<input type="hidden" name="productid" value="<c:out value="${product.id}"/>" >
									<div class="qty-input">
										<span class="text-uppercase">QTY: </span>
										<input class="input" type="number" name = "quantity" value = "1">
									</div>
									<button class="primary-btn add-to-cart" type = "submit"><i class="fa fa-shopping-cart"></i> Add to Cart</button>
								</form>
								<%} %>
								<% if (session.getAttribute("user") != null) {
										if (session.getAttribute("userType") == "Staff" || session.getAttribute("userType") == "Manager" || session.getAttribute("userType") == "Admin") {%>
											<form method ="post" action="editproduct">
												<input type="hidden" name="productid" value="<c:out value="${product.id}"/>" >
												<button class="primary-btn add-to-cart" type = "submit">Edit Product</button><br>
											</form>
											<form method = "post" action="deleteproduct">
												<input type="hidden" name="productid" value="<c:out value="${product.id}"/>" >	
												<button class="primary-btn add-to-cart" type = "submit">Delete Product</button><br>
											</form>
										<% }
								}%>
							</div>
						</div>
					</div>
				</div>
				<!-- /Product Details -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /section -->

	<!-- section -->
	
	<!-- /section -->


	<!-- FOOTER -->
		<footer id="footer" class="section section-grey">
		<%@include file="includes/footer.jsp" %>
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
