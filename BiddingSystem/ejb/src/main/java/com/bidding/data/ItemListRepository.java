package com.bidding.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.ItemList;

@Stateless
public class ItemListRepository {
	
	@Inject
    private EntityManager em;
	
	public ItemList findById(Long id) {
        return em.find(ItemList.class, id);
    }
	
	public void createItemList(ItemList itemList){
		em.persist(itemList);
	}
	
	public List<ItemList> findItemListBySeller(){
		return null;
	}

}
