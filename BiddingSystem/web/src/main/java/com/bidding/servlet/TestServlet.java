package com.bidding.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bidding.data.UserRepository;
import com.bidding.model.User;
import com.bidding.model.Customer;

@WebServlet(name = "test Servlet", description = "This is a test servlet with annotations", urlPatterns = "/test")
public class TestServlet extends HttpServlet {
	
	@Inject
	UserRepository userRepository;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8272752832631194565L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		user.setAddress("aa lsadj slkdfjlskdj flskdjfl skdlkfj ");
		user.setEmail("abc@abc.com");
		user.setPhoneNumber("888888888888");
		
		Customer customer= new Customer();
		customer.setUser(user);
		user.setCustomer(customer);
		
		userRepository.saveUser(user);
		User user1 = new User();
		user1.setId(1L);
		userRepository.remove(user1);
		
		System.out.println("get request");
		response.sendRedirect("test.jsp");
	}

}
