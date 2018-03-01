<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>All Products</title>
	<script src = "js/jquery-min.js"></script>
	<script src = "js/scripts.js"></script>
</head>
<body>
<form action="search">
<input type="text" name="searchkey"><br>
<input type="submit"><br>
</form>


<form action="showproducts">
	<c:forEach items="${products}" var="p">
	<div>
		<table>
			<tr>
				<td class = "product" id="${p.id}">
					${p.name}<br>
					${p.price}<br>
				</td>
			</tr>	
		</table>
	</div>
	</c:forEach>
</form>

</body>
</html>