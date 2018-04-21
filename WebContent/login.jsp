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
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Log In</title>

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
				<li class="active">Login</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->
	
	<!-- LOGIN SECTION -->
	<div class = "section">
		<div class = "container">
			<div class = "row">
				<form method = "post" action="login">
					<div class="section-title">
						<h3 class="title">Login</h3>
					</div>
					<div class = "login-section">
						<strong>Username:</strong> <input class = "login-section-content" pattern="[A-Za-z�._-\s]{1,50}" type = "text" name = "username" /><br>
						<strong>Password:</strong> <input class = "login-section-content" type = "password" name = "password" /><br>
						<input class = "main-btn login-section-content" type="submit" value="Login">
						<a a href = "signup.jsp"><button class = "main-btn login-section-content" type="button" >Create an Account</button></a>
						<% if (request.getAttribute("error") != null)  {
							System.out.println("ERROR");
							boolean error = (boolean) request.getAttribute("error");
							if (error) { %>
								<strong><font color = "red"><p class = "login-section-content">${errorMessage } </p></font></strong>
							<% } 
						}%>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- /LOGIN SECTION -->

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
