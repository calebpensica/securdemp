<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Signup</title>
</head>
<body>
	Sign up:
	<form method = "post" action="regemployee">
		Employee Type: 
		<select name="employeetype" id="employeetype">
			<option value="InventoryStaff">Inventory Staff</option>
			<option value="StoreManager">Store Manager</option>
		</select><br>
		Username: <input type = "text" name = "username" required/><br>
		Password: <input type = "password" name = "password" required/><br>
		First Name: <input type = "text" name = "fName" required/><br>
		Last Name: <input type = "text" name = "lName" required/><br>
		Email: <input type = "email" name = "email" required/><br>
		<input type="submit" value="Add">
	</form>
</body>
</html>