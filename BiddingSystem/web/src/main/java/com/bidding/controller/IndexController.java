package com.bidding.controller;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidding.data.UserRepository;
import com.bidding.model.Customer;
import com.bidding.model.User;

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
		user.setName("lahiru");
		user.setPhoneNumber("888888888888");
		
		Customer customer= new Customer();
		customer.setUser(user);
		user.setCustomer(customer);
		
		userRepository.saveUser(user);
        return "test";
    }

}
