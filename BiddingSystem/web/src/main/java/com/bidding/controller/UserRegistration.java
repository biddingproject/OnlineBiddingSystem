package com.bidding.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.misc.IOUtils;

import com.bidding.model.User;

/**
 * user registration/deregister actions
 * 
 * @author madhumal
 * 
 */
@Controller
public class UserRegistration {

	@Autowired
	ShaPasswordEncoder shaPasswordEncoder;

	@EJB(mappedName = "java:app/BiddingSystem-ejb/UserRegistration")
	com.bidding.service.UserRegistration userRegistration;

	/**
	 * load the registration form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterForm(ModelMap model) {
		model.addAttribute(new User());
		return "register";
	}

	/**
	 * add the user to the db
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterUser(@ModelAttribute("user") User user,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("binding result has errors");
		} else {

			String encodedPass = shaPasswordEncoder.encodePassword(
					user.getPassword(), null);
			user.setPassword(encodedPass);
			userRegistration.registerUser(user);
			return "registrationSuccess";
		}
		return "register";
	}
}
