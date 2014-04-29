package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bidding.model.ItemCategory;
import com.bidding.model.ItemList;
import com.bidding.model.Seller;

@Stateless
public class ItemListRepository {

	@Inject
	private EntityManager em;

	@Inject
	private UserRepository userRepository;

	@Inject
	private ItemCategoryRepository itemCategoryRepository;

	public ItemList findById(Long id) {
		return em.find(ItemList.class, id);
	}

	/**
	 * 
	 * @param itemList
	 * @param itemCategory
	 * @param email
	 */
	public void createItemList(ItemList itemList, int itemCategory, String email) {
		ItemCategory itemCat = itemCategoryRepository
				.getItemCategoryById(itemCategory);
		Seller seller = userRepository.findByEmail(email).getSeller();
		itemList.setCurrentBid(itemList.getBaseBid());
		itemList.setItemCategory(itemCat);
		em.persist(itemList);
		itemList.setSeller(seller);
		seller.getAuctionedItemLists().add(itemList);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ItemList findItemListBySeller(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemList> criteria = cb.createQuery(ItemList.class);
		Root<ItemList> itemList = criteria.from(ItemList.class);
		criteria.select(itemList)
				.where(cb.equal(itemList.get("seller.id"), id));
		return em.createQuery(criteria).getSingleResult();
	}

	/**
	 * 
	 * @param itemList
	 */
	public void deleteItemList(ItemList itemList) {
		em.remove(em.contains(itemList) ? itemList : em.merge(itemList));
	}

}
