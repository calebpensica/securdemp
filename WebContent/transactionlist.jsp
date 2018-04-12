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

	<title>Transactions</title>

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

				<div class="order-summary clearfix">
							<div class="section-title">
								<h3 class="title">Order Review</h3>
							</div>
						<table class="shopping-cart-table table">
								<thead>
									<tr>
										<th>Transaction ID</th>
										<th class="text-center">Sold To</th>
										<th class="text-center">Delivered Address</th>
										<th class="text-center">Total</th>
										<th class="text-center">Action</th>
									</tr>
								</thead>
								<tbody>
								
							<c:forEach items="${transactions}" var ="transaction">
									<tr>
										<td class="thumb">${transaction.id}</td>
										<td class="price text-center"><strong>${transaction.buyer.fName} ${transaction.buyer.lName}</strong></td>
										<td class="qty text-center">${transaction.deliveryAdd}</td>
										<td class="total text-center"><strong class="primary-color">${transaction.sum }</strong></td>
										<td class="text-right"><button class="main-btn icon-btn" onClick="deleteTransaction(${transaction.id})" ><i class="fa fa-close"></i></button></td>
									</tr>
								</c:forEach>
								</tbody>
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
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	function deleteTransaction(id) {
		window.location.href = "deletetransaction?id=" + id;	
	}
</script>
</html>

	