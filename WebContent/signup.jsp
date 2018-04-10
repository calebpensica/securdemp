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

	<title>Sign Up</title>

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

	<!-- NAVIGATION -->
	<hr>
	<!-- /NAVIGATION -->

	<!-- BREADCRUMB -->
	<div id="breadcrumb">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="index.jsp">Home</a></li>
				<li class="active">Sign Up</li>
			</ul>
		</div>
	</div>
	<!-- /BREADCRUMB -->
	
	<!-- SIGNUP SECTION -->
	<div class = "section">
		<div class = "container">
			<div class = "row">
				<form method = "post" action="reguser">
					<div class="section-title">
						<h3 class="title">Sign Up</h3>
					</div>
					<div class = "signup-section">
						<strong>Username:<font color="red">*</font></strong> <input class = "signup-section-content" type = "text" pattern="[A-Za-z.-_]{7,15}" oninvalid="UsernameInvalidMsg(this)" size="15" name = "username" required /><br>
						<strong>Password:<font color="red">*</font></strong> <input class = "signup-section-content" type = "password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&.])[A-Za-z\d$@$!%*?&.]{8,}" oninvalid="PasswordInvalidMsg(this)" name = "password" required /><br>
						<strong>First Name:<font color="red">*</font></strong> <input class = "signup-section-content" type = "text" pattern="[A-Za-zñ\s]{1,50}" oninvalid="NameInvalidMsg(this)" name = "fName" required /><br>
						<strong>Last Name:<font color="red">*</font></strong> <input class = "signup-section-content" type = "text" pattern="[A-Za-zñ\s]{1,50}" oninvalid="NameInvalidMsg(this)" name = "lName" required /><br>
						<strong>Email:<font color="red">*</font></strong> <input class = "signup-section-content" type = "email" name = "email" required /><br>
						<strong>Contact Number:<font color="red">*</font></strong> <input class = "signup-section-content" type = "number" name = "contact" required /><br>
						<strong>Address:<font color="red">*</font></strong> <input class = "signup-section-content" type = "text" name = "address" required /><br>
						<% if (request.getAttribute("error") != null)  {
							boolean error = (boolean) request.getAttribute("error");
							if (error) { %>
								<strong><font color = "red"><p class = "login-section-content">${errorMessage }</p></font></strong>
							<% } 
						}%>
						<input class = "main-btn signup-section-content" type="submit" value="Add">
					</div>
				</form>	
			</div>
		</div>
	</div>
	<!-- /SIGNUP SECTION -- >
	
	<!-- section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
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

<!-- JAVASCRIPT -->
<script>
function NameInvalidMsg(textbox) {

    if(textbox.validity.patternMismatch){
       textbox.setCustomValidity('Numbers and special characters are not allowed.');
   }    
   else {
       textbox.setCustomValidity('');
   }
   return true;
}

function PasswordInvalidMsg(textbox){
	if(textbox.validity.patternMismatch){
	    textbox.setCustomValidity('Password must contain at least 1 uppercase letter, 1 lower case letter, 1 number, 1 special character and at least 8 characters long.');
	}    
	else {
	    textbox.setCustomValidity('');
	}
	return true;
}

function UsernameInvalidMsg(textbox){
if(textbox.validity.patternMismatch){
    textbox.setCustomValidity('Username must be at least 7 characters.');
}    
else {
    textbox.setCustomValidity('');
}
return true;
}
</script>
<!-- /JAVASCRIPT -->
</body>

</html>
