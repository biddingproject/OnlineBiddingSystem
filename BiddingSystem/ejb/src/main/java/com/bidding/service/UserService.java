package com.bidding.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.UserRepository;
import com.bidding.model.User;

@Stateless
public class UserService {

	@Inject
	UserRepository userRepository;

	public com.bidding.model.User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	public com.bidding.model.User getUserById(Long id) {
		return userRepository.findById(id);
	}

	public void updateProfilePicture(byte[] imageBytes, String email) {
		userRepository.updateProfilePicture(imageBytes, email);

	}

	public boolean changePassword(String email, String oldPassword,
			String newPassword) {
		
		User user = userRepository.findByEmail(email);
		
		if (user.getPassword().equals(oldPassword)) {
			System.out.println("old passwords match");
			try {
				userRepository.changePassword(user.getId(), newPassword);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public void updateUserInformation(String email, User user) {
		User userTmp = userRepository.findByEmail(email);
		user.setId(userTmp.getId());
		userRepository.updateUserInformation(user);
	}

}
