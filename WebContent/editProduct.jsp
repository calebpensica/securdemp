<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit product</title>
</head>
<body>
<form method = "post" action="commiteditproduct">
		<input type="hidden" name="productid" value="<c:out value="${product.id}"/>" /><br>
		Name: <input type="text" name="productName" value="<c:out value="${product.name}"/>" /><br>
		Price : <input type = "number" name = "productPrice"  value="<c:out value="${product.price}"/>"required/><br>
		<input type="submit" name="Update Product" value="Update Product">
</form>
</body>
</html>