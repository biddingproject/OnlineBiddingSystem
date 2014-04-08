package com.bidding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ItemCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String categoryName;

	@ManyToOne
	@JoinColumn(name = "PARENT_CATEGORY")
	private ItemCategory parentCategory;

	@OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
	private List<ItemCategory> subCategories = new ArrayList<ItemCategory>();

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ItemCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<ItemCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<ItemCategory> subCategories) {
		this.subCategories = subCategories;
	}
}
