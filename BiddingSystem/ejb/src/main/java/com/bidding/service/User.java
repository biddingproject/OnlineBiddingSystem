package com.bidding.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.UserRepository;

@Stateless
public class User {
	
	@Inject
	UserRepository userRepository;
	
	public com.bidding.model.User getUserByEmail(String email){
		return userRepository.getUserByEmail(email);
	}

	public com.bidding.model.User getUserById(Long id) {
		return userRepository.findById(id);
	}
	
}
