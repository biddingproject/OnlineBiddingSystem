package com.bidding.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidding.model.ItemCategory;
import com.bidding.model.ItemList;
import com.bidding.service.ItemCategoryService;
import com.bidding.service.UserService;

@Controller
public class LoginController {

	@EJB(mappedName = "java:app/BiddingSystem-ejb/UserService")
	UserService userService;

	@EJB(mappedName = "java:app/BiddingSystem-ejb/ItemCategoryService")
	ItemCategoryService itemCategoryService;

	static Logger log = Logger.getLogger(LoginController.class.getName());

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {

		return "login";

	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		SecurityContextHolder.clearContext();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "login";

	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String loadDashboard(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		model.addAttribute("user", userService.getUserByEmail(email));
		return "/profile/dashboard";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_CUSTOMER')")
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String loadCustomerProfile(ModelMap model) {
		return "/profile/customer";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_SELLER')")
	@RequestMapping(value = "/seller", method = RequestMethod.GET)
	public String loadSellerProfile(ModelMap model) {

		model.put("itemList", new ItemList());
		List<ItemCategory> itemCategories = itemCategoryService
				.getAllItemCategories();

		if (itemCategories != null && itemCategories.size() != 0) {
			model.put("itemCategoryList", itemCategories);
			model.put("itemCategoryListSize", itemCategories.size());
		} else {
			model.put("itemCategoryListSize", 0);
		}

		return "/profile/seller";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String loadAdminProfile(ModelMap model) {
		return "/profile/admin";
	}

	/**
	 * 
	 * @param response
	 * @param email
	 * @throws IOException
	 */
	@RequestMapping(value = "/getUserImage/{id}")
	public void getUserImage(HttpServletResponse response,
			@PathVariable("id") Long id) throws IOException {
		byte[] buffer = userService.getUserById(id).getProfilePicture();

		try {
			response.getOutputStream().write(buffer);
			response.getOutputStream().flush();
		} catch (Exception e) {
			System.out.println("buffer case");
		}

	}

}