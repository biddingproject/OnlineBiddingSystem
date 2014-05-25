package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Transaction;
import com.bidding.model.ItemCategory;

@Stateless
public class ItemRepository {
	
	@Inject
    private EntityManager em;
	
	public Transaction findById(Long id) {
        return em.find(Transaction.class, id);
    }
	
	public ItemCategory createItemCategory(ItemCategory itemCategory){
		em.persist(itemCategory);
		return null;
	}
	
	public void deleteItemCategory(){
		
	}
}
