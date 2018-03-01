package com.securde.servlet;

import com.securde.bean.*;
import com.securde.service.*;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class URLPatterns
{
	public final static String REGISTERUSER = "/reguser";
	public final static String REGISTERADMIN = "/regadmin";
	public final static String REGISTERMANAGER = "/regmanager";
	public final static String REGISTERSTAFF = "/regstaff";
	public final static String LOGINUSER = "/loginuser";
	public final static String LOGINADMIN = "/loginadmin";
	public final static String LOGINMANAGER = "/loginmanager";
	public final static String LOGINSTAFF = "/loginstaff";
	public final static String ADDPRODUCT = "/addproduct";
}

/**
 * Servlet implementation class MainServlet
 */
@WebServlet({"/MainServlet", 
			 URLPatterns.REGISTERUSER,
			 URLPatterns.REGISTERADMIN,
			 URLPatterns.REGISTERMANAGER,
			 URLPatterns.REGISTERSTAFF,
			 URLPatterns.LOGINUSER,
			 URLPatterns.LOGINADMIN,
			 URLPatterns.LOGINMANAGER,
			 URLPatterns.LOGINSTAFF,
			 URLPatterns.ADDPRODUCT,})
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
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
				request.getRequestDispatcher("addproduct.jsp").forward(request, response);
				break;
			case URLPatterns.REGISTERUSER:
				registerUser(request, response);
				break;
			case URLPatterns.REGISTERADMIN:
				registerAdmin(request, response);
				break;
			case URLPatterns.REGISTERSTAFF:
				registerStaff(request, response);
				break;	
			case URLPatterns.REGISTERMANAGER:
				registerManager(request, response);
				break;
			case URLPatterns.LOGINUSER:
				loginUser(request, response);
				break;	
			case URLPatterns.LOGINADMIN:
				loginAdmin(request, response);
				break;	
			case URLPatterns.LOGINSTAFF:
				loginStaff(request, response);
				break;	
			case URLPatterns.LOGINMANAGER:
				loginManager(request, response);
				break;
			case URLPatterns.ADDPRODUCT:
				addProduct(request, response);
				break;	
		}
		
	}
	
	private void loginManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		StoreManager s = StoreManagerService.findManager(username, password);
		
		if(s != null)
			request.getRequestDispatcher("index.jsp").forward(request, response);
		else
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	private void loginStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		InventoryStaff i = InventoryStaffService.findStaff(username, password);
		
		if(i != null)
			request.getRequestDispatcher("index.jsp").forward(request, response);
		else
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admin a = AdminService.findAdmin(username, password);
		
		if(a != null)
			request.getRequestDispatcher("index.jsp").forward(request, response);
		else
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Client c = ClientService.findClient(username, password);
		
		if(c != null)
			request.getRequestDispatcher("index.jsp").forward(request, response);
		else
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}

	private void registerManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		StoreManager s = new StoreManager();
		s.setUsername(username);
		s.setPassword(password);
		s.setfName(fName);
		s.setlName(lName);
		s.setEmail(email);
		System.out.println(StoreManagerService.addManager(s)); 
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	private void registerStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		
		InventoryStaff i = new InventoryStaff();
		i.setUsername(username);
		i.setPassword(password);
		i.setfName(fName);
		i.setlName(lName);
		i.setEmail(email);
		System.out.println(InventoryStaffService.addStaff(i)); 
		
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
		String description = request.getParameter("productDescription");
		float price = Float.parseFloat(request.getParameter("productPrice"));
		int quantity = Integer.parseInt(request.getParameter("productQuantity"));
		Tag tag = new Tag();
		tag.setTag(request.getParameter("productTag"));
		Product p = new Product();
		p.setName(name);
		p.setPrice(price);
		p.setStatus(true);
		//p.setTags(tag);
		
		if(ProductService.addProduct(p))
			System.out.println("Success");
		else
			System.out.println("Failed");
	
		List<Product> products = ProductService.getAllProducts();
		
		request.setAttribute("products", products);
		request.getRequestDispatcher("showproducts.jsp").forward(request,response);
	}


}
