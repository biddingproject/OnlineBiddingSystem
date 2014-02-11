package com.bidding.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.UserRepository;

@Stateless
public class UserRegistration {
	
	@Inject
	UserRepository userRepository;

}
