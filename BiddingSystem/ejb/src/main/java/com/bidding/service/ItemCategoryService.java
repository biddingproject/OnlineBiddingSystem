package com.bidding.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.ItemCategoryRepository;
import com.bidding.model.ItemCategory;

@Stateless
public class ItemCategoryService {

	@Inject
	ItemCategoryRepository itemCategoryRepository;

	public void createCategory(String categoryName, int parentCategoryId) {
		ItemCategory parentCategory = null;
		if (parentCategoryId > 0) {
			parentCategory = itemCategoryRepository
					.getItemCategoryById(parentCategoryId);
			
		}
		itemCategoryRepository.createItemCategory(categoryName,parentCategory);
	}

}
