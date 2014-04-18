package com.bidding.controller;

import javax.ejb.EJB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidding.data.UserRepository;

@Controller
class IndexController {

//	@EJB(mappedName = "java:app/BiddingSystem-ejb/UserRepository")
//	UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayIndex(ModelMap model) {
		return "index";
	}

}
