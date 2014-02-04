package com.bidding.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class UserRepository {
	
	@Inject
    private EntityManager em;

}
