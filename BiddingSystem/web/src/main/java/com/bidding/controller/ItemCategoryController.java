package com.bidding.controller;

import javax.ejb.EJB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bidding.service.ItemCategoryService;

@Controller
public class ItemCategoryController {
	
	@EJB(mappedName = "java:app/BiddingSystem-ejb/ItemCategoryService")
	ItemCategoryService itemCategoryService;

	@RequestMapping(value = "/itemCategoryPage", method = RequestMethod.GET)
	public String itemCategoryPage(ModelMap model) {
		model.put("itemCategoryList", itemCategoryService.getAllItemCategories());
		return "/profile/admin";
	}

	@RequestMapping(value = "/createItemCategory", method = RequestMethod.POST)
	public String createItemCategory(
			ModelMap model,
			@RequestParam(value = "categoryName", required = true) String categoryName,
			@RequestParam(value = "parentCategory", required = false) String parentCategoryId) {
		int parentCatId = 0;
		
		if(parentCategoryId!=null && (!parentCategoryId.equals(null))&&(!parentCategoryId.equals(""))){
			parentCatId = Integer.parseInt(parentCategoryId);
		}
		model.put("itemCategoryList", itemCategoryService.getAllItemCategories());
		itemCategoryService.createCategory(categoryName,parentCatId);
		return "/profile/admin";
	}

	@RequestMapping(value = "/deleteItemCategory", method = RequestMethod.POST)
	public String deleteItemCategory(ModelMap map) {

		return "/profile/admin";
	}

}
