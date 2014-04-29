package com.bidding.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.ItemCategory;

@Stateless
public class ItemCategoryRepository {

	@Inject
	private EntityManager em;

	/**
	 * 
	 * @param parentCategoryId
	 * @return
	 */
	public ItemCategory getItemCategoryById(long parentCategoryId) {
		return em.find(ItemCategory.class, parentCategoryId);
	}

	/**
	 * 
	 * @param categoryName
	 * @param parentCategory
	 */
	public void createItemCategory(String categoryName,
			ItemCategory parentCategory) {

		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setCategoryName(categoryName);
		itemCategory.setParentCategory(parentCategory);
		em.persist(itemCategory);
		
		if (parentCategory != null) {
			parentCategory.getSubCategories().add(itemCategory);
		}
	}
}
