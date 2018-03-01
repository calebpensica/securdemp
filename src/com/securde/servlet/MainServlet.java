package com.securde.servlet;

import com.securde.bean.*;
import com.securde.service.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
	public final static String LOGIN = "/login";
	public final static String SEARCH = "/search";
}

/**
 * Servlet implementation class MainServlet
 */
@WebServlet({"/MainServlet", 
			 URLPatterns.REGISTERUSER,
			 URLPatterns.REGISTERADMIN,
			 URLPatterns.REGISTERMANAGER,
			 URLPatterns.REGISTERSTAFF,
			 URLPatterns.LOGIN,
			 URLPatterns.SEARCH})
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
				request.getRequestDispatcher("signup.jsp").forward(request, response);
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
			case URLPatterns.LOGIN:
				loginUser(request, response);
				break;	
			case URLPatterns.SEARCH:
				searchProducts(request, response);
				break;
		}
		
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Client c = ClientService.findClient(username, password);
		Admin a = AdminService.findAdmin(username, password);
		InventoryStaff i = InventoryStaffService.findStaff(username, password);
		StoreManager s = StoreManagerService.findManager(username, password);
		
		if(c != null)
			request.getRequestDispatcher("index.jsp").forward(request, response);
		else {
			if(i != null)
				request.getRequestDispatcher("index.jsp").forward(request, response);
			else {
				if(s != null)
					request.getRequestDispatcher("index.jsp").forward(request, response);
				else {
					if(a != null)
						request.getRequestDispatcher("index.jsp").forward(request, response);
					else
						request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
		}
			
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
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
}
