<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Information</title>
</head>
<body>
	<p>${user.name}</p>
	<p>${user.fname} ${user.lname}</p>
	<p>${user.email}</p>
	
	<c:choose>
    <c:when test="${usertype=='client'}">
        <p>${user.contactNo}</p>
        <p>${user.homeAdd}</p>
        <br />
    </c:when>    
</c:choose>
<form method="post" action="editAccount">
	<input type="submit" value="Edit Account">
</form>

</body>
</html>