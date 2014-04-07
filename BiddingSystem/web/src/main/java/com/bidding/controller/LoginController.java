package com.bidding.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.apache.log4j.Logger;

@Controller
public class LoginController {

	static Logger log = Logger.getLogger(LoginController.class.getName());

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {
		log.info("Going to create HelloWord Obj");
		String name = principal.getName();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "loginSuccess";

	}

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

	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String loadDashboard(ModelMap model) {
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