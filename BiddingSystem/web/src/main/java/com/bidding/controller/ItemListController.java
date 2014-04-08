package com.bidding.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidding.model.ItemList;

@Controller
public class ItemListController {
	
	@PreAuthorize("hasRole('ROLE_SELLER')")
	@RequestMapping(value = "/createItemList", method = RequestMethod.POST)
	public String loadCustomerProfile(ModelMap model) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("E MMM dd, yyyy HH:mm a"); 
		//Date parsedDate = sdf2.parse(str);
		model.put("itemList", new ItemList());
		return "/profile/seller";
	}
}
