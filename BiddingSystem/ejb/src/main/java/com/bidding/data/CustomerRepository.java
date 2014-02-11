package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Customer;


@Stateless
public class CustomerRepository {
	
	@Inject
    private EntityManager em;
	
	public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

}
