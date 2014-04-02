package com.bidding.service;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.UserRepository;
import com.bidding.model.User;
import com.bidding.model.UserRole;

@Stateless
public class UserRegistration {

	@Inject
	UserRepository userRepository;
	
	/**
	 * register any user to the system
	 * @param user
	 * @return
	 */
	public String registerUser(User user){
		user.setEnabled(true);
		UserRole role = new UserRole();
		role.setRoleName("ROLE_USER");
		role.setRoleDescription("This role indicates a registered user");
		role.setUser(user);
		user.setUserRoles(new ArrayList<UserRole>());
		user.getUserRoles().add(role);
		if (userRepository.saveUser(user)) {
			System.out.println("registration success");
			return "registration successfull";
		} else {
			System.out.println("registration un success");
			return "registration not successfull";
		}
	}

}
