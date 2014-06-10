package com.bidding.controller;

import javax.ejb.EJB;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidding.service.TransactionService;

@Controller
public class TransactionController {

	@EJB(mappedName = "java:app/BiddingSystem-ejb/TransactionService")
	TransactionService transactionService;

	/**
	 * 
	 * @param model
	 * @param itemListId
	 * @param quantity
	 * @return
	 */
	@RequestMapping(value = "/buyItemsWithPaypal", method = RequestMethod.POST)
	public String buyItem(
			ModelMap model,
			@RequestParam(value = "itemListId", required = true) long itemListId,
			@RequestParam(value = "quantity", required = true) int quantity) {

		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();

		transactionService.buyItems(itemListId, quantity, email);

		return "/profile/buyItem";
	}
}
