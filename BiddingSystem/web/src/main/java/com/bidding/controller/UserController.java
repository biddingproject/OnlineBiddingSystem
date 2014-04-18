package com.bidding.controller;

import java.io.IOException;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bidding.model.User;
import com.bidding.service.UserRegistrationService;
import com.bidding.service.UserService;

/**
 * user registration/deregister actions
 * 
 * @author madhumal
 * 
 */
@Controller
public class UserController {

	@Autowired
	ShaPasswordEncoder shaPasswordEncoder;

	@EJB(mappedName = "java:app/BiddingSystem-ejb/UserRegistrationService")
	UserRegistrationService userRegistrationService;

	@EJB(mappedName = "java:app/BiddingSystem-ejb/UserService")
	UserService userService;

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
			userRegistrationService.registerUser(user);
			return "registrationSuccess";
		}
		return "register";
	}

	@RequestMapping(value = "/changeProfilePic", method = RequestMethod.POST)
	public String addPersonFromForm(
			@RequestParam(value = "image", required = false) MultipartFile image) {

		if (!image.isEmpty()) {
			try {
				byte[] imageBytes = image.getBytes();
				String email = SecurityContextHolder.getContext()
						.getAuthentication().getName();
				userService.updateProfilePicture(imageBytes, email);
			} catch (IOException e) {
				 e.printStackTrace();
			}
		}

		return "redirect:/dashboard";
	}
}
