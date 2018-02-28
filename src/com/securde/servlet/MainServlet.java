package com.securde.servlet;

import com.securde.bean.*;
import com.securde.service.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet({"/MainServlet", "/signup", "/login"})
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
		/* CODE TO ADD TO THE TEST TABLE FEEL FREE TO DELETE
		Test t = new Test("the newest label");
		TestService.addTest(t);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pattern = request.getServletPath();
		
		switch(pattern)
		{
			case "/MainServlet":
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				break;
			case "/signup":
				register(request, response);
				break;
			case "/login":
				break;
			
		}
		
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		System.out.println(username);
		System.out.println(password);
		System.out.println(fName);
		System.out.println(lName);
		System.out.println(email);
		
		Admin a = new Admin();
		a.setUsername(username);
		a.setPassword(password);
		a.setfName(fName);
		a.setlName(lName);
		a.setEmail(email);
		System.out.println(AdminService.addAdmin(a)); 
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

}
