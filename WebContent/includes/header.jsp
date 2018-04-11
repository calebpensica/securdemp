<%@page import="com.securde.bean.Client"%>
<%@page import="com.securde.bean.InventoryStaff"%>
<%@page import="com.securde.bean.StoreManager"%>
<%@page import="com.securde.bean.Admin"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- HEADER -->
		<!-- top Header -->
		
		<!-- /top Header -->

		<!-- header -->
		<div id="header">
			<div class="container">
				<div class="pull-left">
					<!-- Logo -->
					<div class="header-logo">
						<a class="logo" href="index.jsp">
							<img src="./img/Papema_Logo.png" alt="">
						</a>
					</div>
					<!-- /Logo -->

					<!-- Search -->
					<div class="header-search">
						<form action = "search">
							<input class="input search-input" type="text" pattern="[0-9A-Za-z.]{0,50}" placeholder="Enter your keyword" name = "searchkey">
							<select class="input search-categories">
								<option value="0">By Name</option>
								<option value="1">By Tag</option>
							</select>
							<button type = 'submit' class="search-btn"><i class="fa fa-search"></i></button>
						</form>
					</div>
					<!-- /Search -->
				</div>
				<div class="pull-right">
					<ul class="header-btns">
						<!-- Account -->
						<li class="header-account dropdown default-dropdown">
							<div class="dropdown-toggle" role="button" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-user-o"></i>
								</div>
								<strong class="text-uppercase">
								<% if (session.getAttribute("user") != null){ %>
										Welcome back!
								<%		if (session.getAttribute("userType") == "Client"){
											Client c = (Client) session.getAttribute("user");
											out.print(c.getUsername());
										} else if  (session.getAttribute("userType") == "Staff") {
											InventoryStaff i = (InventoryStaff) session.getAttribute("user");
											out.print(i.getUsername());
										} else if (session.getAttribute("userType") == "Manager") {
											StoreManager s = (StoreManager) session.getAttribute("user");
											out.print(s.getUsername());
										} else if (session.getAttribute("userType") == "Admin") {
											Admin a = (Admin) session.getAttribute("user");
											out.print(a.getUsername());
										}
								} else { %>
									My Account
								<% } %>
								<i class="fa fa-caret-down"></i></strong>
							</div>
							<%	if (session.getAttribute("user") == null) { %>
								<a href="login.jsp" class="text-uppercase">Login</a> / <a href="signup.jsp" class="text-uppercase">Join</a>
							<% } %>
							<ul class="custom-menu">
				
								<% if (session.getAttribute("user") == null) { %>
									<li><a href="login.jsp"><i class="fa fa-unlock-alt"></i> Login</a></li>
									<li><a href="signup.jsp"><i class="fa fa-user-plus"></i> Create An Account</a></li>
								<% } else {
								%>	<li><a href="#"><i class="fa fa-user-o"></i> My Account</a></li>
									<li><a href="#"><i class="fa fa-check"></i> Checkout</a></li> <%
									if (session.getAttribute("userType") != "Client") { %>
									<li><a href="addproduct.jsp"><i class="fa fa-user-plus"></i> Add Product</a></li>
								<%	if (session.getAttribute("userType") == "Admin") { %>
									<li><a href="employeesignup.jsp"><i class="fa fa-user-plus"></i> Create An Employee Account</a></li>
								<% } %>	
							<% } %>
							<form method = "post" action = "logout">
								<li> <input class = "main-btn" type = "submit" value = "Logout"></li>
							</form>
						<% }	%>
						</ul>
						</li>
						<!-- /Account -->

						<!-- Cart -->
						<li class="header-cart dropdown default-dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
								<div class="header-btns-icon">
									<i class="fa fa-shopping-cart"></i>
									<span class="qty">3</span>
								</div>
								<strong class="text-uppercase">My Cart:</strong>
								<br>
								<span>${total }</span>
							</a>
							<div class="custom-menu">
								<div id="shopping-cart">
									<div class="shopping-cart-list">
									<c:forEach items="${items}" var ="item">
										<div class="product product-widget">
											<div class="product-thumb">
												<img src="./img/thumb-product01.jpg" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-price">$${item.product.price } <span class="qty">x${item.quantity }</span></h3>
												<h2 class="product-name"><a href="#">${item.product.name }</a></h2>
											</div>
											<button class="cancel-btn"><i class="fa fa-trash"></i></button>
										</div>
										</c:forEach>
									</div>
									<div class="shopping-cart-btns">
										<form method="post" action="confirmcheckout">
											<button type="submit" class="primary-btn">Checkout</button>
										</form>
									</div>
								</div>
							</div>
						</li>
						<!-- /Cart -->

						<!-- Mobile nav toggle-->
						<li class="nav-toggle">
							<button class="nav-toggle-btn main-btn icon-btn"><i class="fa fa-bars"></i></button>
						</li>
						<!-- / Mobile nav toggle -->
					</ul>
				</div>
			</div>
			<!-- header -->
		</div>
		<!-- container -->
	<!-- /HEADER -->