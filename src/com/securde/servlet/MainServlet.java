package com.securde.servlet;

import com.securde.bean.*;
import com.securde.service.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			 URLPatterns.SHOWPRODUCTS})
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
				request.getRequestDispatcher("index.jsp").forward(request, response);
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
		}
		
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Client c = ClientService.findClient(username, password);
		Admin a = AdminService.findAdmin(username, password);
		InventoryStaff i = InventoryStaffService.findStaff(username, password);
		StoreManager s = StoreManagerService.findManager(username, password);
		
		
		
		if(c != null) {
			session.setAttribute("user", c);
			session.setAttribute("userType", "Client");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			if(i != null) {
				session.setAttribute("user", i);
				session.setAttribute("userType", "Staff");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				if(s != null) {
					session.setAttribute("user", s);
					session.setAttribute("userType", "Manager");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					if(a != null) {
						session.setAttribute("user", a);
						session.setAttribute("userType", "Admin");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} else {
						request.setAttribute("error", new Boolean(true));
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}
			}
		}
			
	}

	private void registerEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		StoreManager s = new StoreManager();
		InventoryStaff is = new InventoryStaff();
		if(request.getParameter("employeetype").equals("StoreManager")) {
			s.setUsername(username);
			s.setPassword(password);
			s.setfName(fName);
			s.setlName(lName);
			s.setEmail(email);
			System.out.println(StoreManagerService.addManager(s)); 
		}
		else {
			is.setUsername(username);
			is.setPassword(password);
			is.setfName(fName);
			is.setlName(lName);
			is.setEmail(email);
			System.out.println(InventoryStaffService.addStaff(is)); 
		}
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String contactNo = request.getParameter("contact");
		String homeAdd = request.getParameter("address");
		
		Client c = new Client();
		c.setUsername(username);
		c.setPassword(password);
		c.setfName(fName);
		c.setlName(lName);
		c.setEmail(email);
		c.setContactNo(contactNo);
		c.setHomeAdd(homeAdd);
		
		System.out.println(ClientService.addClient(c)); 
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	private void registerAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		Admin a = new Admin();
		a.setUsername(username);
		a.setPassword(password);
		a.setfName(fName);
		a.setlName(lName);
		a.setEmail(email);
		System.out.println(AdminService.addAdmin(a)); 
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("productName");
		float price = Float.parseFloat(request.getParameter("productPrice"));
		Tag tag = new Tag();
		tag.setTag(request.getParameter("productTag"));
		Product p = new Product();
		p.setName(name);
		p.setPrice(price);
		p.setStatus(true);
		p.setTags(new HashSet<Tag>());
		if(p.addProductTag(tag)) {
			if(TagService.addTag(tag))
				System.out.println("Tag added");
			else
				System.out.println("Tag add failure");
		}
		else
			System.out.println("Tag exists already");
		
		System.out.println(ProductService.addProduct(p));
		
		request.getRequestDispatcher("showProducts.jsp").forward(request, response);
		
	}
	
	private void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = ProductService.getAllProducts();
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
