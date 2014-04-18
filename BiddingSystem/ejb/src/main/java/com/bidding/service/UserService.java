package com.bidding.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.UserRepository;

@Stateless
public class UserService {
	
	@Inject
	UserRepository userRepository;
	
	public com.bidding.model.User getUserByEmail(String email){
		return userRepository.getUserByEmail(email);
	}

	public com.bidding.model.User getUserById(Long id) {
		return userRepository.findById(id);
	}


	public void updateProfilePicture(byte[] imageBytes, String email) {
		userRepository.updateProfilePicture(imageBytes,email);
		
	}
	
}
