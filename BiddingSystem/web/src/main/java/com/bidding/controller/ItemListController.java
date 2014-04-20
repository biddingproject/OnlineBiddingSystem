package com.bidding.controller;

import javax.ejb.EJB;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidding.model.ItemList;
import com.bidding.service.ItemListService;

/**
 * 
 * @author madhumal
 * 
 */
@Controller
public class ItemListController {

	@EJB(mappedName = "java:app/BiddingSystem-ejb/ItemListService")
	ItemListService itemListService;

	@PreAuthorize("hasRole('ROLE_SELLER')")
	@RequestMapping(value = "/createItemList", method = RequestMethod.POST)
	public String loadCustomerProfile(
			@ModelAttribute("itemList") ItemList itemList,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("binding result has errors");
		} else {
			String email = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			itemListService.createItemLIst(itemList, email);
			System.out.println(itemList.getBidStartTimeStamp());
			return "registrationSuccess";
		}

		model.put("itemList", new ItemList());
		return "/profile/seller";
	}
}
