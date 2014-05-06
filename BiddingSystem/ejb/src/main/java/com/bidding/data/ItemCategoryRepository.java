package com.bidding.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	 * @return
	 */
	public List<ItemCategory> getAllItemCategories() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ItemCategory> cq = cb.createQuery(ItemCategory.class);
		Root<ItemCategory> rootEntry = cq.from(ItemCategory.class);
		CriteriaQuery<ItemCategory> all = cq.select(rootEntry);
		TypedQuery<ItemCategory> allQuery = em.createQuery(all);
		
		return allQuery.getResultList();
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
