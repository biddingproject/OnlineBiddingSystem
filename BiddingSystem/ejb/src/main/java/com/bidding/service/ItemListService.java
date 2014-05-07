package com.bidding.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.ItemListRepository;
import com.bidding.model.ItemList;

@Stateless
public class ItemListService {

	@Inject
	ItemListRepository itemListRepository;

	/**
	 * 
	 * @param itemList
	 * @param itemCategory
	 * @param email
	 */
	public void createItemLIst(ItemList itemList, int itemCategory, String email) {
		itemListRepository.createItemList(itemList, itemCategory, email);
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public List<ItemList> getItemLists(String email, short pageNumber) {
		return itemListRepository.getItemListsByEmail(email, pageNumber);
	}
}
