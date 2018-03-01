<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<form method = "post" action="addproduct">
		Name: <input type = "text" name = "productName"required/><br>
		Price : <input type = "number" name = "productPrice"required/><br>
		Quantity : <input type = "number" name = "productQuantity" required/><br>
		Description : <input type = "text" name = "productDescription"required/><br>
		Tag : <input type = "text" name = "productTag" required/><br>
		<input type="submit" value="Add Product">
</form>
</body>
</html>