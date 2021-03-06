package com.bidding.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
 /**
	 * 
	 */
	private static final long serialVersionUID = -1957204164562716676L;

@Override
 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // Destroys the session for this user.
  request.getSession(false).invalidate();

  // Redirects back to the initial page.
  response.sendRedirect(request.getContextPath());
 }
}
