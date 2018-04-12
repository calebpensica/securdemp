package com.securde.servlet;

import com.securde.bean.*;
import com.securde.service.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		}
		
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		HttpSession session = request.getSession();
		
		System.out.println("Hello");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Client c = ClientService.findClient(username, password);
		Admin a = AdminService.findAdmin(username, password);
		InventoryStaff i = InventoryStaffService.findStaff(username, password);
		StoreManager s = StoreManagerService.findManager(username, password);
		
		if(c != null) {
			String uuid = UUID.randomUUID().toString().replace("-", "");
		    System.out.println("uuid = " + uuid);
			c.setUserHash(uuid);
			ClientService.updateClient(c.getId(), c);
			
			Cookie cookie = new Cookie("uuid", c.getUserHash());
			cookie.setMaxAge(60*60*24*365*2);
			response.addCookie(cookie);
			
			session.setMaxInactiveInterval(60*15);
			System.out.println(session.getMaxInactiveInterval());
			session.setAttribute("user", c);
			session.setAttribute("userType", "Client");
			request.getRequestDispatcher("showproducts.jsp").forward(request, response);
		} else {
			if(i != null) {
				String uuid = UUID.randomUUID().toString().replace("-", "");
			    System.out.println("uuid = " + uuid);
				i.setUserHash(uuid);
				InventoryStaffService.updateStaff(i.getId(), i);
				
				Cookie cookie = new Cookie("uuid", i.getUserHash());
				cookie.setMaxAge(60*60*24*365*2);
				response.addCookie(cookie);
				
				session.setMaxInactiveInterval(60*15);
				session.setAttribute("user", i);
				session.setAttribute("userType", "Staff");
				request.getRequestDispatcher("showproducts.jsp").forward(request, response);
			} else {
				if(s != null) {
					String uuid = UUID.randomUUID().toString().replace("-", "");
				    System.out.println("uuid = " + uuid);
					s.setUserHash(uuid);
					StoreManagerService.updateManager(s.getId(), s);
					
					Cookie cookie = new Cookie("uuid", s.getUserHash());
					cookie.setMaxAge(60*60*24*365*2);
					response.addCookie(cookie);
					
					session.setMaxInactiveInterval(60*15);
					session.setAttribute("user", s);
					session.setAttribute("userType", "Manager");
					request.getRequestDispatcher("showproducts.jsp").forward(request, response);
				} else {
					if(a != null) {
						String uuid = UUID.randomUUID().toString().replace("-", "");
					    System.out.println("uuid = " + uuid);
						a.setUserHash(uuid);
						AdminService.updateAdmin(a.getId(), a);
						
						Cookie cookie = new Cookie("uuid", a.getUserHash());
						cookie.setMaxAge(60*60*24*365*2);
						response.addCookie(cookie);
						
						session.setMaxInactiveInterval(60*15);
						session.setAttribute("user", a);
						session.setAttribute("userType", "Admin");
						request.getRequestDispatcher("showproducts.jsp").forward(request, response);
					} else {
						request.setAttribute("error", new Boolean(true));
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}
			}
		}
			
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.invalidate();
		// kill cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				if(currentCookie.getName().equals("uuid")){
					currentCookie.setMaxAge(0);
					response.addCookie(currentCookie);
				}
				if (currentCookie.getName().equals("JSESSIONID")) {
					System.out.println("FOUND JSESSIONID");
					currentCookie.setMaxAge(0);
					response.addCookie(currentCookie);
				}
			}
		}
		
		// go to index.jsp
		//response.sendRedirect("/Papema/MainServlet");
		response.sendRedirect("/Papema/login");
		
		//request.getRequestDispatcher("index.jsp").forward(request, response);
	}

private void registerEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
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
				String uuid = UUID.randomUUID().toString().replace("-", "");
			    System.out.println("uuid = " + uuid);
			    
				s.setUsername(username);
				s.setPassword(password);
				s.setfName(fName);
				s.setlName(lName);
				s.setEmail(email);
				s.setUserHash(uuid);
				
				Cookie cookie = new Cookie("uuid", s.getUserHash());
				cookie.setMaxAge(60*60*24*365*2);
				response.addCookie(cookie);
				
				System.out.println(StoreManagerService.addManager(s));
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else
				request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
		else if(request.getParameter("employeetype").equals("Admin")) {
			registerAdmin(request,response);
		}
		else {
			
			List<InventoryStaff> staffs = InventoryStaffService.getAllInventoryStaffs();
			for(InventoryStaff i : staffs)
				if(i.getUsername().equalsIgnoreCase(username))
					exist = true;
			
			if(!exist)
			{
				is = new InventoryStaff();
				String uuid = UUID.randomUUID().toString().replace("-", "");
			    System.out.println("uuid = " + uuid);
				
				is.setUsername(username);
				is.setPassword(password);
				is.setfName(fName);
				is.setlName(lName);
				is.setEmail(email);
				is.setUserHash(uuid);
				
				System.out.println(InventoryStaffService.addStaff(is)); 
				
				Cookie cookie = new Cookie("uuid", is.getUserHash());
				cookie.setMaxAge(60*60*24*365*2);
				response.addCookie(cookie);
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else
				request.getRequestDispatcher("signup.jsp").forward(request, response);	
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String contactNo = request.getParameter("contact");
		String homeAdd = request.getParameter("address");
		
		List<Client> clients = ClientService.getAllClients();
		boolean exist = false;
		
		System.out.println(contactNo);
		
		for(Client c : clients)
			if(c.getUsername().equalsIgnoreCase(username))
				exist = true;
		
		if(!exist) {
			Client c = new Client();
			String uuid = UUID.randomUUID().toString().replace("-", "");
		    System.out.println("uuid = " + uuid);
			
			c.setUsername(username);
			c.setPassword(password);
			c.setfName(fName);
			c.setlName(lName);
			c.setEmail(email);
			c.setContactNo(contactNo);
			c.setHomeAdd(homeAdd);
			c.setUserHash(uuid);
			
			Cookie cookie = new Cookie("uuid", c.getUserHash());
			cookie.setMaxAge(60*60*24*365*2);
			response.addCookie(cookie);
			
			System.out.println(ClientService.addClient(c)); 
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		
	}

	private void registerAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		List<Admin> admins = AdminService.getAllAdmins();
		boolean exist = false;
		
		for(Admin ad : admins)
			if(ad.getUsername().equalsIgnoreCase(username))
				exist = true;
		
		if(!exist) {
			Admin a = new Admin();
			String uuid = UUID.randomUUID().toString().replace("-", "");
		    System.out.println("uuid = " + uuid);
			
			a.setUsername(username);
			a.setPassword(password);
			a.setfName(fName);
			a.setlName(lName);
			a.setEmail(email);
			a.setUserHash(uuid);
			AdminService.addAdmin(a);
			
			Cookie cookie = new Cookie("uuid", a.getUserHash());
			cookie.setMaxAge(60*60*24*365*2);
			response.addCookie(cookie);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else
			request.getRequestDispatcher("signup.jsp").forward(request, response);
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
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			HashSet<CartItem> items = new HashSet<CartItem>();
			cart.setItems(items);
			CartService.addCart(cart);
		}
		
		Product product = ProductService.getProduct(Integer.parseInt(request.getParameter("productid")));
		CartItem item = new CartItem();
		item.setProduct(product);
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		item.setCart(cart);
		cart.addCartItem(item);
		Transaction transaction = new Transaction();
		Client client = (Client)session.getAttribute("user");
		transaction.setBuyer(client);
		transaction.setDeliveryAdd(client.getHomeAdd());
		transaction.setSum(product.getPrice()*item.getQuantity());
		Calendar c = Calendar.getInstance();
		transaction.setTimeOrder(c.toString());
		//transaction.setTimeReceived(timeReceived);
		CartItemService.addCartItem(item);
		TransactionService.addTransaction(transaction);
		
		showProducts(request,response);
		
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("productid"));
		
		System.out.println(ProductService.deleteProduct(id));
		
		showProducts(request, response);
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
		System.out.println("SHOWING");
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
