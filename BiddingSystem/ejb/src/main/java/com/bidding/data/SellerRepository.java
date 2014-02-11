package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Seller;

@Stateless
public class SellerRepository {
	
	@Inject
    private EntityManager em;
	
	public Seller findById(Long id) {
        return em.find(Seller.class, id);
    }
	
	public Seller findSellerByEmail(String email) {
        return em.find(Seller.class, email);
    }

}
