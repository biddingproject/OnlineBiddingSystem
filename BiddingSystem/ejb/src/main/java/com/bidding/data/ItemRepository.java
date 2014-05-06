package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Item;
import com.bidding.model.ItemCategory;

@Stateless
public class ItemRepository {
	
	@Inject
    private EntityManager em;
	
	public Item findById(Long id) {
        return em.find(Item.class, id);
    }
	
	public ItemCategory createItemCategory(ItemCategory itemCategory){
		em.persist(itemCategory);
		return null;
	}
	
	public void deleteItemCategory(){
		
	}
}
