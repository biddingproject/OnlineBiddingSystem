package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	public ItemList findItemListBySeller(Long id){
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemList> criteria = cb.createQuery(ItemList.class);
        Root<ItemList> itemList = criteria.from(ItemList.class);
        criteria.select(itemList).where(cb.equal(itemList.get("seller.id"), id));
        return em.createQuery(criteria).getSingleResult();
	}
	
	public void deleteItemList(ItemList itemList){
		em.remove(em.contains(itemList)? itemList : em.merge(itemList));
	}

}
