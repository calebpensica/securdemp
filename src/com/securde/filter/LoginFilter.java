package com.securde.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.securde.bean.Admin;
import com.securde.bean.Client;
import com.securde.bean.InventoryStaff;
import com.securde.bean.StoreManager;
import com.securde.service.AdminService;
import com.securde.service.ClientService;
import com.securde.service.InventoryStaffService;
import com.securde.service.StoreManagerService;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/MainServlet", "/search", "/regemployee", "/addproduct", "/showproducts", "/product", "/editproduct", "/commiteditproduct", "/buyproduct", "/accountdetails", "/deleteproduct"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("Filtering");	
		//cast to HttpServlet for use
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		//retrieve use from cookie
		Cookie[] cookies = httpRequest.getCookies();
		String uuid = null;
		//check if username cookie exists
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie currCookie = cookies[i];
				if (currCookie.getName().equals("uuid")) {
					uuid = currCookie.getValue();
					currCookie.setMaxAge(60*60*24*365*2);
					currCookie.setHttpOnly(true);
					httpResponse.addCookie(currCookie);
					System.out.println(uuid);
				}
			}
		}
		
		//if exists
		if (!session.isNew()) {
			if (uuid != null) {
				boolean found = false;
				List<Client> clientList = ClientService.getAllClients();
				List<Admin> adminList = AdminService.getAllAdmins();
				List<StoreManager> managerList = StoreManagerService.getAllManagers();
				List<InventoryStaff> staffList = InventoryStaffService.getAllInventoryStaffs();
				//Use cookie value and set it as attr to session
		
				int i = 0;
				while (!found && i < clientList.size()) {
					System.out.println("Client Finding");
					Client client = clientList.get(i);
					System.out.println("IF " + uuid + " IS EQUAL TO " + client.getUserHash());
					if(client.getUserHash() != null) {
						if (client.getUserHash().equals(uuid)) {
							session.setAttribute("user", client);
							session.setAttribute("userType", "Client");
							found = true;
						} else 
							i++;
					} else
						i++;
				}
				
				i = 0;
				while (!found && i < adminList.size()) {
					System.out.println("Admin Finding");
					Admin admin = adminList.get(i);
					
					if(admin.getUserHash() != null) {
						if (admin.getUserHash().equals(uuid)) {
							session.setAttribute("user", admin);
							session.setAttribute("userType", "Admin");
							found = true;
						} else 
							i++;
					} else
						i++;
				}
				
				
				i = 0;
				while (!found && i < managerList.size()) {
					System.out.println("Manager Finding");
					StoreManager manager = managerList.get(i);
					
					if(manager.getUserHash() != null) {
						if (manager.getUserHash().equals(uuid)) {
							session.setAttribute("user", manager);
							session.setAttribute("userType", "Manager");
							found = true;
						} else 
							i++;
					} else
						i++;
				}
				
				
				i = 0;
				while (!found && i < staffList.size()) {
					System.out.println("Staff Finding");
					InventoryStaff staff = staffList.get(i);
					
					if(staff.getUserHash() != null) {
						if (staff.getUserHash().equals(uuid)) {
							session.setAttribute("user", staff);
							session.setAttribute("userType", "Staff");
							found = true;
						} else
							i++;
					} else
						i++;
				}	
	
				// pass the request along the filter chain
				if (found) {
					System.out.println("FOUND");
					chain.doFilter(request, response);
				} else {
					System.out.println("NOT FOUND");
					httpResponse.sendRedirect("/Papema/login");
				}
				
			} else {
				httpResponse.sendRedirect("/Papema/login");
			}
		} else {
			httpResponse.sendRedirect("/Papema/logout");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
