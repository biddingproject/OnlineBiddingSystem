package com.bidding.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.ItemListRepository;
import com.bidding.model.ItemList;

@Stateless
public class ItemListService {
	
	@Inject
	ItemListRepository itemListRepository;

	public void createItemLIst(ItemList itemList, String userName) {
		itemListRepository.createItemList(itemList);
	}
}