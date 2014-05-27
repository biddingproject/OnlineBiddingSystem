package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class ShoppingCartRepository {

	@Inject
	private EntityManager em;
}
