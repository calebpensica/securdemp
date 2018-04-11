package com.securde.servlet;

import com.securde.bean.*;
import com.securde.service.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class URLPatterns
{
	public final static String REGISTERUSER = "/reguser";
	public final static String REGISTERADMIN = "/regadmin";
	public final static String LOGIN = "/login";
	public final static String SEARCH = "/search";
	public final static String REGISTEREMPLOYEE = "/regemployee";
	public final static String ADDPRODUCT = "/addproduct";
	public final static String SHOWPRODUCTS = "/showproducts";
	public final static String PRODUCT = "/product";
	public final static String EDITPRODUCT = "/editproduct";
	public final static String COMMITEDITPRODUCT = "/commiteditproduct";
	public final static String BUYPRODUCT = "/buyproduct";
	public final static String ACCOUNTDETAILS = "/accountdetails";
	public final static String DELETEPRODUCT = "/deleteproduct";
	public final static String LOGOUT = "/logout";
	public final static String CONFIRMCHECKOUT = "/confirmcheckout";
	public final static String CONFIRMCONTACT = "/confirmcontact";
	public final static String CHECKOUT = "/checkout";
	public final static String TRANSACTIONS = "/transactions";
	public final static String DELETETRANSACTION = "/deletetransaction";
}

/**
 * Servlet implementation class MainServlet
 */
@WebServlet({"/MainServlet", 
			 URLPatterns.REGISTERUSER,
			 URLPatterns.REGISTERADMIN,
			 URLPatterns.LOGIN,
			 URLPatterns.SEARCH,
			 URLPatterns.REGISTEREMPLOYEE,
			 URLPatterns.ADDPRODUCT,
			 URLPatterns.PRODUCT,
			 URLPatterns.SHOWPRODUCTS,
			 URLPatterns.EDITPRODUCT,
			 URLPatterns.COMMITEDITPRODUCT,
			 URLPatterns.BUYPRODUCT,
			 URLPatterns.ACCOUNTDETAILS,
			 URLPatterns.DELETEPRODUCT,
			 URLPatterns.LOGOUT,
			 URLPatterns.CONFIRMCHECKOUT,
			 URLPatterns.CONFIRMCONTACT,
			 URLPatterns.CHECKOUT,
			 URLPatterns.TRANSACTIONS,
			 URLPatterns.DELETETRANSACTION
			 })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pattern = request.getServletPath();
		
		switch(pattern)
		{
			case "/MainServlet":
				showProducts(request,response);
				break;
			
			case URLPatterns.REGISTERUSER:
				registerUser(request, response);
				break;
			
			case URLPatterns.REGISTERADMIN:
				registerAdmin(request, response);
				break;
			
			case URLPatterns.REGISTEREMPLOYEE:
				registerEmployee(request, response);
				break;	
			case URLPatterns.LOGIN:
				loginUser(request, response);
				break;	
			case URLPatterns.SEARCH:
				searchProducts(request, response);
				break;
			case URLPatterns.ADDPRODUCT:
				addProduct(request, response);
				break;	
			case URLPatterns.SHOWPRODUCTS:
				showProducts(request, response);
				break;
			case URLPatterns.PRODUCT:
				viewProduct(request, response);
				break;
			case URLPatterns.EDITPRODUCT:
				editProduct(request, response);
				break;
			case URLPatterns.COMMITEDITPRODUCT:
				commitEditProduct(request, response);
				break;
			case URLPatterns.BUYPRODUCT:
				buyProduct(request, response);
				break;
			case URLPatterns.ACCOUNTDETAILS:
		//		accountDetails(request, response);
				break;
			case URLPatterns.DELETEPRODUCT:
				deleteProduct(request, response);
				break;
			case URLPatterns.LOGOUT:
				logoutUser(request, response);
				break;
			case URLPatterns.CONFIRMCHECKOUT:
				confirmproducts(request, response);
				break;
			case URLPatterns.CONFIRMCONTACT:
				confirmcontact(request, response);
				break;
			case URLPatterns.CHECKOUT:
				checkout(request, response);
				break;
			case URLPatterns.TRANSACTIONS:
				showTransactions(request, response);
				break;
			case URLPatterns.DELETETRANSACTION:
				deleteTransaction(request, response);
				break;
		}
		
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		MessageDigest MD5;
		HttpSession session = request.getSession();
		String url;
		Date date;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		/* Password Encryption */
		StringBuffer sb = new StringBuffer();
		try {
			MD5 = MessageDigest.getInstance("MD5");
			MD5.update(password.getBytes());
			byte[] messageDigestMD5 = MD5.digest();
			for(byte bytes : messageDigestMD5) {
				sb.append(String.format("%02x", bytes & 0xff));
			}
		} catch (NoSuchAlgorithmException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
		
		Client c = ClientService.findClient(username, sb.toString());
		Admin a = AdminService.findAdmin(username, sb.toString());
		InventoryStaff i = InventoryStaffService.findStaff(username, sb.toString());
		StoreManager s = StoreManagerService.findManager(username, sb.toString());
		


		
		if(c != null) {
			session.setAttribute("user", c);
			session.setAttribute("userType", "Client");
			request.getRequestDispatcher("showproducts.jsp").forward(request, response);
		} else {
			if(i != null) {
				session.setAttribute("user", i);
				session.setAttribute("userType", "Staff");
				request.getRequestDispatcher("showproducts.jsp").forward(request, response);
			} else {
				if(s != null) {
					session.setAttribute("user", s);
					session.setAttribute("userType", "Manager");
					request.getRequestDispatcher("showproducts.jsp").forward(request, response);
				} else {
					if(a != null) {
						session.setAttribute("user", a);
						session.setAttribute("userType", "Admin");
						request.getRequestDispatcher("showproducts.jsp").forward(request, response);
					} else {
						request.setAttribute("error", new Boolean(true));
						/* Brute Force Attack Prevention */
						int loginAttempt;
			            if (session.getAttribute("loginCount") == null)
			            {
			                session.setAttribute("loginCount", 0);
			                loginAttempt = 0;
			            }
			            else
			            {
			                 loginAttempt = (Integer) session.getAttribute("loginCount");
			            }

			            //this is 3 attempts counting from 0,1,2
			            if (loginAttempt >= 4 )
			            {
			            	
			                long lastAccessedTime = session.getLastAccessedTime();
			                date = new Date();
			                long currentTime = date.getTime();
			                long timeDiff = currentTime - lastAccessedTime;
			                // 20 minutes in milliseconds  
			                if (timeDiff >= 120000)
			                {
			                    //invalidate user session, so they can try again
			                    session.invalidate();
			                }
			                else
			                {
			                     // Error message 
			                     request.setAttribute("errorMessage","You have exceeded the 5 failed login attempt. Please try logging in in 20 minutes.");
			                }  

			            }
			            else
			            {
			                 loginAttempt++;
			                 int allowLogin = 5-loginAttempt;
			                 session.setAttribute("message","loginAttempt= "+loginAttempt+". Invalid username or password. You have "+allowLogin+" attempts remaining. Please try again! <br>Not a registered cusomer? Please <a href=\"register.jsp\">register</a>!");
			                 request.setAttribute("errorMessage", "Invalid username or password. You have "+allowLogin+" attempts remaining. Please try again!");
			            }
			            request.setAttribute("errorMessage", "Invalid Username and/or Password. Please try again");
			            session.setAttribute("loginCount",loginAttempt);
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}
			}
		}
			
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

private void registerEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDigest MD5;
	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		/* Password Encryption */
		StringBuffer sb = new StringBuffer();
		try {
			MD5 = MessageDigest.getInstance("MD5");
			MD5.update(password.getBytes());
			byte[] messageDigestMD5 = MD5.digest();
			for(byte bytes : messageDigestMD5) {
				sb.append(String.format("%02x", bytes & 0xff));
			}
		} catch (NoSuchAlgorithmException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }

		
		StoreManager s = null;
		InventoryStaff is = null;
		boolean exist = false;
		
		if(request.getParameter("employeetype").equals("StoreManager")) {
			
			List<StoreManager> managers = StoreManagerService.getAllManagers();
			for(StoreManager sm : managers)
				if(sm.getUsername().equalsIgnoreCase(username))
					exist = true;
			
			if(!exist)
			{
				s = new StoreManager();
				s.setUsername(username);
				s.setPassword(sb.toString());
				s.setfName(fName);
				s.setlName(lName);
				s.setEmail(email);
				System.out.println(StoreManagerService.addManager(s));
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
		else if(request.getParameter("employeetype").equals("Admin")) {
			registerAdmin(request,response);
		}
		else {
			
			if(!checkDuplicates(username))
			{
				is = new InventoryStaff();
				is.setUsername(username);
				is.setPassword(sb.toString());
				is.setfName(fName);
				is.setlName(lName);
				is.setEmail(email);
				System.out.println(InventoryStaffService.addStaff(is)); 
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("error", true);
				request.setAttribute("errorMessage", "Invalid Username.");
				request.getRequestDispatcher("employeesignup.jsp").forward(request, response);
			}
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MessageDigest MD5;
		StringBuffer sb = new StringBuffer();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String contactNo = request.getParameter("contact");
		String homeAdd = request.getParameter("address");

		/* Password Encryption */
		try {
			MD5 = MessageDigest.getInstance("MD5");
			MD5.update(password.getBytes());
			byte[] messageDigestMD5 = MD5.digest();
			for(byte bytes : messageDigestMD5) {
				sb.append(String.format("%02x", bytes & 0xff));
			}
		} catch (NoSuchAlgorithmException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
		
		List<Client> clients = ClientService.getAllClients();
		
		System.out.println(contactNo);
		
		if(checkDuplicates(username)) {
			Client c = new Client();
			c.setUsername(username);
			c.setPassword(sb.toString());
			c.setfName(fName);
			c.setlName(lName);
			c.setEmail(email);
			c.setContactNo(contactNo);
			c.setHomeAdd(homeAdd);
			
			System.out.println(ClientService.addClient(c)); 
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.setAttribute("error", true);
			request.setAttribute("errorMessage", "Invalid Username.");
			request.getRequestDispatcher("employeesignup.jsp").forward(request, response);
		}
		
	}

	private void registerAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDigest MD5;
		StringBuffer sb = new StringBuffer();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		/* Password Encryption */
		try {
			MD5 = MessageDigest.getInstance("MD5");
			MD5.update(password.getBytes());
			byte[] messageDigestMD5 = MD5.digest();
			for(byte bytes : messageDigestMD5) {
				sb.append(String.format("%02x", bytes & 0xff));
			}
		} catch (NoSuchAlgorithmException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }

		
		/* CHECKS FOR DUPLICATES */
		
		if(!checkDuplicates(username)) {
			Admin a = new Admin();
			a.setUsername(username);
			a.setPassword(password);
			a.setfName(fName);
			a.setlName(lName);
			a.setEmail(email);
			System.out.println(AdminService.addAdmin(a));
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.setAttribute("error", true);
			request.setAttribute("errorMessage", "Invalid Username.");
			request.getRequestDispatcher("employeesignup.jsp").forward(request, response);
		}
	}
	
	public boolean checkDuplicates(String username) {
		for(Client client: ClientService.getAllClients() ) {
			if(username==client.getUsername()) {
				return true;
			}
			System.out.println(username);
			System.out.println("client: " + client.getUsername() );
		}
		
		for(Admin admin: AdminService.getAllAdmins() ) {
			if(username==admin.getUsername())
				return true;
		}
		
		for(StoreManager manager: StoreManagerService.getAllManagers() ) {
			if(username==manager.getUsername())
				return true;
		}
		
		for(InventoryStaff staff: InventoryStaffService.getAllInventoryStaffs() ) {
			if(username==staff.getUsername())
				return true;
		}
		
		return false;
	}
	
	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Product p = new Product();
		Tag tag = new Tag();
		tag.setTag(request.getParameter("productTag"));
		p.setTags(new HashSet<Tag>());
		if(p.addProductTag(tag)) {
			if(TagService.addTag(tag))
				System.out.println("Tag added");
			else
				System.out.println("Tag add failure");
		}
		else
			System.out.println("Tag exists already");
		
		String name = request.getParameter("productName");
		float price = Float.parseFloat(request.getParameter("productPrice"));
		p.setName(name);
		p.setPrice(price);
		p.setStatus(true);

		
		System.out.println(ProductService.addProduct(p));
		
		request.getRequestDispatcher("showproducts.jsp").forward(request, response);
		
	}
	
	private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Product product = ProductService.getProduct(Integer.parseInt(request.getParameter("productid")));
		request.setAttribute("product", product);
		request.getRequestDispatcher("editproduct.jsp").forward(request, response);
	}
	
	private void commitEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("productid"));
		Product p = ProductService.getProduct(id);
	//	Tag tag = new Tag();
	//	tag.setTag(request.getParameter("productTag"));
	//	p.setTags(new HashSet<Tag>());
		//		if(p.addProductTag(tag)) {
	//		if(TagService.addTag(tag))
		//		System.out.println("Tag added");
		//	else
	//			System.out.println("Tag add failure");
	//	}
	//	else
		//	System.out.println("Tag exists already");
		
		String name = request.getParameter("productName");
		float price = Float.parseFloat(request.getParameter("productPrice"));
		p.setName(name);
		p.setPrice(price);

		System.out.println(ProductService.updateProduct(id, p));
		
		showProducts(request,response);
		
	}
	
	private void buyProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cart cart;
		if(session.getAttribute("cartID")==null){
			cart = new Cart();
			CartService.addCart(cart);	
			for(Cart newCart: CartService.getAllCarts()) {
				cart.setCartid(newCart.getCartid());
			}
			System.out.println("Cart Instantiated: " +cart.getCartid());
			session.setAttribute("cartID", cart.getCartid());
		}
		else {
			cart = CartService.getCart((int)session.getAttribute("cartID"));
		}
		
		
		System.out.println("CARTID: " + Integer.toString((int)session.getAttribute("cartID")));
		
		Product product = ProductService.getProduct(Integer.parseInt(request.getParameter("productid")));
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		item.setCart(cart);
		Client client = (Client)session.getAttribute("user");
		if(client==null) 
			loginUser(request,response);
		
		ArrayList<CartItem> items = (ArrayList<CartItem>)session.getAttribute("items");
		if(items==null){
			items = new ArrayList();
			session.setAttribute("items", items);
		}
		items.add(item);
		session.setAttribute("items", items);

		//transaction.setTimeReceived(timeReceived);
		
		showProducts(request,response);
		
	}
	
	private void confirmproducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart;
		if(session.getAttribute("cartID")==null) {
			cart = new Cart();
			System.out.println("Cart Null");
			showProducts(request,response);
		}
		else {
			cart = CartService.getCart((int)session.getAttribute("cartID"));
			System.out.print("ahh cart gotten: ");
			System.out.println(cart);
		}
		
		Client client = (Client)session.getAttribute("user");
		if(client==null) {
			System.out.println("Client Null");
			showProducts(request,response);
		}
		ArrayList<CartItem> items = (ArrayList<CartItem>)session.getAttribute("items");
		if(items==null) {
			System.out.println("CartItems Null");
			showProducts(request,response);
		}
		
		double total = 0.0;
		for(CartItem item: items) {
			total += (item.getQuantity()*item.getProduct().getPrice());
		}
		
		session.setAttribute("total", total);
		

		request.getRequestDispatcher("checkout.jsp").forward(request, response);
		
	}
	
	private void confirmcontact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart;
		if(session.getAttribute("cartID")==null) {
			cart = new Cart();
			System.out.println("Cart Null");
			showProducts(request,response);
		}
		else {
			cart = CartService.getCart((int)session.getAttribute("cartID"));
			System.out.print("ahh cart gotten: ");
			System.out.println(cart);
		}
		Client client = (Client)session.getAttribute("user");
		if(client==null) {
			System.out.println("Client Null");
			showProducts(request,response);
		}
		ArrayList<CartItem> items = (ArrayList<CartItem>)session.getAttribute("items");
		if(items==null) {
			System.out.println("CartItems Null");
			showProducts(request,response);
		}
		request.getRequestDispatcher("confirmaddress.jsp").forward(request, response);
	}
	
	private void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart;
		if(session.getAttribute("cartID")==null) {
			cart = new Cart();
			System.out.println("Cart Null");
			showProducts(request,response);
		}
		else {
			cart = CartService.getCart((int)session.getAttribute("cartID"));
			System.out.print("ahh cart gotten: ");
			System.out.println(cart);
		}
		Client client = (Client)session.getAttribute("user");
		if(client==null) {
			System.out.println("Client Null");
			showProducts(request,response);
		}
		
		ArrayList<CartItem> items = (ArrayList<CartItem>)session.getAttribute("items");
		if(items==null) {
			System.out.println("CartItems Null");
			showProducts(request,response);
		}
		for(CartItem item: items) {
			item.setCart(cart);
			CartItemService.addCartItem(item);
		}
		
		Transaction transaction = new Transaction();
		transaction.setBuyer(client);
		transaction.setCart(cart);
		System.out.print("CART: WIP");
		System.out.println(cart);
		transaction.setDeliveryAdd(request.getParameter("homeAdd"));
		transaction.setSum((float)((double)session.getAttribute("total")));
		transaction.setTimeOrder("ah");
		TransactionService.addTransaction(transaction);
		System.out.println("Transaction Created");
		session.removeAttribute("cart");
		session.removeAttribute("items");
		session.removeAttribute("total");
		session.setAttribute("total", 0.00);
		request.getRequestDispatcher("index.jsp").forward(request,response);
		
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("productid"));
		
		System.out.println(ProductService.deleteProduct(id));
		
		showProducts(request, response);
	}
	
	private void showTransactions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Transaction> transactions = TransactionService.getAllTransactions();
		for(Transaction transaction: transactions) {
			System.out.println(transaction.getId());
		}
		request.setAttribute("transactions", transactions);
		request.getRequestDispatcher("transactionlist.jsp").forward(request, response);
	}
	
	private void deleteTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println(TransactionService.deleteTransaction(id));
		
		showTransactions(request, response);
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("usertype");
		
		switch(type)
		{
			case "Client":
				System.out.println(ClientService.deleteClient(id));
				break;
			case "Inventory Staff":
				System.out.println(InventoryStaffService.deleteStaff(id));
				break;	
			case "StoreManager":
				System.out.println(StoreManagerService.deleteManager(id));
				break;	
			case "Admin":
				System.out.println(AdminService.deleteAdmin(id));
				break;	
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	
	private void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = ProductService.getAllProducts();
		for(Product product: products) {
			System.out.println(product.getName());
		}
		request.setAttribute("products", products);
		request.getRequestDispatcher("showproducts.jsp").forward(request, response);
	}
	
	private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("THE NUMBER IS: " + id);
		Product p = ProductService.getProduct(id);
		
		request.setAttribute("product", p);
		request.getRequestDispatcher("product.jsp").forward(request, response);
	}


	private void searchProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String key = request.getParameter("searchkey");
		String[] keywords = key.split(" ");
		
		List<Product> products = ProductService.getAllProducts();
		
		for(int i = 0; i < keywords.length; i++)
		{
			for(int j = 0; j < products.size(); j++)
			{
				// filtering by name
				boolean found = false;
				if(products.get(j).getName().toLowerCase().contains(keywords[i].toLowerCase()))
					found = true;
				else {
					//filtering by tags
					Set<Tag> tags = products.get(j).getTags();
					for(Tag t : tags)
						if(t.getTag().toLowerCase().contains(keywords[i].toLowerCase()))
							found = true;							
				}
				
				if(found == false)
					products.remove(j);
			}
		}
		
		request.setAttribute("searchkey", key);
		request.setAttribute("products", products);
		request.getRequestDispatcher("showproducts.jsp").forward(request, response);
		
	}
}
