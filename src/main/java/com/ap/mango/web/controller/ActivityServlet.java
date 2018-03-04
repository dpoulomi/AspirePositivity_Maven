package com.ap.mango.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

/**
 * Servlet implementation class ActivityServlet
 */
@Singleton
public class ActivityServlet extends HttpServlet {
    /**
	 * Serial id 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
		
        final String button = request.getParameter("button");
        if ("button1".equals(button)) {
        	 response.sendRedirect("soundRecord.jsp");
        }
		if ("button2".equals(button)) {
			response.sendRedirect("artscrafts.jsp");
		}
		if ("button3".equals(button)) {
			response.sendRedirect("socialContribution.jsp");
		}
}
 
}
