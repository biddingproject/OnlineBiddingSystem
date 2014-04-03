package com.bidding.service;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.UserRepository;
import com.bidding.model.User;
import com.bidding.model.UserRole;
import com.bidding.util.StringConstants;

@Stateless
public class UserRegistration {

	@Inject
	UserRepository userRepository;

	/**
	 * register any user to the system
	 * 
	 * @param user
	 * @param isCustomer
	 * @param isSeller
	 * @return
	 */
	public String registerUser(User user, boolean isSeller, boolean isCustomer) {
		user.setEnabled(true);
		UserRole role = new UserRole();
		role.setRoleName(StringConstants.ROLE_USER);
		role.setRoleDescription("This role indicates a registered user");
		role.setUser(user);
		
		user.setUserRoles(new ArrayList<UserRole>());
		user.getUserRoles().add(role);
		
		if(isSeller){
			UserRole role1 = new UserRole();
			role1.setRoleName(StringConstants.ROLE_SELLER);
			role1.setRoleDescription("This role indicates a seller");
			role1.setUser(user);
			user.getUserRoles().add(role1);
		}
		
		if(isCustomer){
			UserRole role2 = new UserRole();
			role2.setRoleName(StringConstants.ROLE_CUSTOMER);
			role2.setRoleDescription("This role indicates a customer");
			role2.setUser(user);
			user.getUserRoles().add(role2);
		}
		
		if (userRepository.saveUser(user)) {
			System.out.println("registration success");
			return "registration successfull";
		} else {
			System.out.println("registration un success");
			return "registration not successfull";
		}
	}

}
