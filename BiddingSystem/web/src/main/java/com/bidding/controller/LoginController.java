package com.bidding.controller;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@EJB(mappedName = "java:app/BiddingSystem-ejb/User")
	com.bidding.service.User user;

	static Logger log = Logger.getLogger(LoginController.class.getName());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		SecurityContextHolder.clearContext();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "login";

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String loadDashboard(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		System.out.println(email);
		model.addAttribute("user", user.getUserByEmail(email));
		return "/profile/dashboard";
	}

	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String loadCustomerProfile(ModelMap model) {
		return "/profile/customer";
	}

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@RequestMapping(value = "/seller", method = RequestMethod.GET)
	public String loadSellerProfile(ModelMap model) {
		return "/profile/seller";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String loadAdminProfile(ModelMap model) {
		return "/profile/admin";
	}

}