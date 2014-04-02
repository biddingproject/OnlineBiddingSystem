package com.bidding.controller;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidding.data.UserRepository;
import com.bidding.model.Customer;
import com.bidding.model.User;
import com.bidding.model.UserRole;

@Controller
class IndexController {
	
	@EJB(mappedName="java:app/BiddingSystem-ejb/UserRepository")
	UserRepository userRepository;
	
    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/tester", method = RequestMethod.GET)
    public String showIndex(ModelMap model) {
    	
		User user = new User();
		user.setAddress("aa lsadj slkdfjlskdj flskdjfl skdlkfj ");
		user.setEmail("abc@abc.com");
		user.setPhoneNumber("888888888888");
		user.setPassword("aaaaaa");
		user.setEnabled(true);
		UserRole ur = new UserRole();
		ur.setRoleId(1);
		ur.setRoleName("abc");
		ur.setUser(user);
		List<UserRole> roles = user.getUserRoles();
		user.setUserRoles(roles);
		
		Customer customer= new Customer();
		customer.setUser(user);
		user.setCustomer(customer);
		
		userRepository.saveUser(user);
        return "test";
    }

}
