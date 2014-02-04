package com.bidding.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Customer;

@ApplicationScoped
public class CustomerRepository {
	
	@Inject
    private EntityManager em;
	
	public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

}
