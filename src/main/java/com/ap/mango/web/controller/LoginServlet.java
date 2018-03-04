package com.ap.mango.web.controller;
 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ap.mango.dao.soundrecorder.UsersDao;
import com.ap.mango.entity.Users;
import com.ap.mango.services.LoginService;
import com.google.inject.Singleton;
 
@Singleton
public class LoginServlet extends HttpServlet {
    /**
	 * Serial id 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
 
     final String username = request.getParameter("username");   
     final String password = request.getParameter("password");
     final LoginService loginService = new LoginService();
     final boolean result = loginService.authenticateUser(username, password);
     if(result == true){
         final UsersDao usersDao=new UsersDao();
         final Users user = usersDao.getUserByUserName(username,password);
         request.getSession().setAttribute("user", user);      
         response.sendRedirect("home.jsp");
     }
     else{
         response.sendRedirect("error.jsp");
     }
}
 
}
