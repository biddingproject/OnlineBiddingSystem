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
	 * handle user registration form submit
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @param confirmPassword
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterUser(
			@ModelAttribute("user") User user,
			BindingResult result,
			ModelMap model,
			@RequestParam(value = "confirmPassword", required = true) String confirmPassword) {

		if (result.hasErrors()) {
			System.out.println("binding result has errors");
		} else {

			if (!confirmPassword.equals(user.getPassword())) {
				return "registrationError";
			}
			try {
				String encodedPass = shaPasswordEncoder.encodePassword(
						user.getPassword(), null);
				user.setPassword(encodedPass);
				userRegistrationService.registerUser(user);
				return "registrationSuccess";
			} catch (Exception e) {
				e.printStackTrace();
				return "registrationError";
			}

		}
		return "register";
	}

	/**
	 * 
	 * @param image
	 * @return
	 */
	@RequestMapping(value = "/changeProfilePic", method = RequestMethod.POST)
	public String changeProfilePic(
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

	/**
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param confirmPassword
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(
			@RequestParam(value = "oldPassword", required = true) String oldPassword,
			@RequestParam(value = "newPassword", required = true) String newPassword,
			@RequestParam(value = "confirmPassword", required = true) String confirmPassword) {

		boolean passwordChanged = false;

		if (newPassword.equals(confirmPassword)) {

			String email = SecurityContextHolder.getContext()
					.getAuthentication().getName();

			String encodedOldPassword = shaPasswordEncoder.encodePassword(
					oldPassword, null);
			String encodedNewPassword = shaPasswordEncoder.encodePassword(
					newPassword, null);

			passwordChanged = userService.changePassword(email,
					encodedOldPassword, encodedNewPassword);
		}
		return "redirect:/dashboard";
	}

	/**
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateUserInformation", method = RequestMethod.POST)
	public String updateUserInformation(@ModelAttribute("user") User user,
			BindingResult result, ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();

		userService.updateUserInformation(email, user);
		return "redirect:/dashboard";
	}
}
