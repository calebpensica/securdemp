<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product</title>
	<script src = "js/jquery-min.js"></script>
	<script src = "js/scripts.js"></script>
</head>
<body>
	<p>${product.name}</p>
	<p>${product.price}</p>
	<form method ="post" action="editproduct">
		<input type="hidden" name="productid" value="<c:out value="${product.id}"/>" >
		<input type="submit" value="Edit Product"><br>
	</form>
	<form method ="post" action="buyproduct">
		<input type="hidden" name="productid" value="<c:out value="${product.id}"/>" >
		Quantity: <input type="number" name="quantity" value="1"><br>
		<input type="submit" value="Add to Cart"><br>
	</form>
</body>
</html>