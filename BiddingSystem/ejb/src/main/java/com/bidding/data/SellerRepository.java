package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Seller;
import com.bidding.model.User;

@Stateless
public class SellerRepository {

	@Inject
	private EntityManager em;

	@Inject
	private UserRepository userRepository;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Seller findById(Long id) {
		return em.find(Seller.class, id);
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public Seller findSellerByEmail(String email) {
		User user = userRepository.getUserByEmail(email);
		return user.getSeller();
	}

}
