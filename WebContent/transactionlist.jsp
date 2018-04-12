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

	<title>Add Product</title>

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
<%@include file="includes/header.jsp" %>
	</header>
	<!-- /HEADER -->

	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="section-title">
					<h3 class="title">Transaction List</h3>
				</div>
				<div class = "login-section">
					<table width="100%" border="0" cellspacing="0" cellpadding="6">
							<tr>
								<th style="text-align: left;"><i aria-hidden="true"></i> TRANSACTION ID</th>
								<th style="text-align: left;"><i aria-hidden="true"></i> SOLD TO</th>
								<th style="text-align: left;"><i aria-hidden="true"></i> DELIVERY ADDRESS</th>
								<th style="text-align: left;"><i aria-hidden="true"></i> TOTAL</th>
								<th><i class="fa fa-pencil" aria-hidden="true"></i> ACTION</th>
							</tr>
							
							<c:forEach items="${transactions}" var="transaction">
								<tr ${(count%2 == 1) ? 'class="even"' : '' } >
									<c:set var="count" value="${count+1}" />
										<td style="text-align:left;">${transaction.id}</td>
										<td style="text-align:left;">${transaction.client.fName transaction.client.lName}</td>
										<td style="text-align:left;">${transaction.deliveryAdd}</td>
										<td style="text-align:left;">${transaction.sum}</td>
										<td align="center">
											<a href="deleteTransaction?id=${transaction.transactionid}">
												<i class="fa fa-trash fa-lg" style="color: #d03431;" aria-hidden="true"></i>
											</a> 
										</td>
								</tr>
							</c:forEach>	
						</table>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
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
	