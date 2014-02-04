package com.bidding.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Item;

@ApplicationScoped
public class ItemRepository {
	
	@Inject
    private EntityManager em;
	
	public Item findById(Long id) {
        return em.find(Item.class, id);
    }
}
