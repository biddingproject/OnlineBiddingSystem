package com.bidding.controller;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidding.model.ShoppingCartItem;
import com.bidding.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	
    @EJB(mappedName = "java:app/BiddingSystem-ejb/ShoppingCartService")
    ShoppingCartService shoppingCartService;
    
	@RequestMapping(value = "/addItemToCart", method = RequestMethod.POST)
	public String getItemListPage(ModelMap model,
			@RequestParam(value = "itemListId", required = true) long itemListId,
			@RequestParam(value = "quantity", required = true) int quantity) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		shoppingCartService.addItemToCart(itemListId,quantity, email);
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/removeItemFromCart", method = RequestMethod.POST)
	public String removeItemFromCart(ModelMap model,
			@RequestParam(value = "shoppingCartItem", required = true) long shoppingCartItem) {
		shoppingCartService.removeItemFromCart(shoppingCartItem);
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/clearCart", method = RequestMethod.POST)
	public String clearCart(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		shoppingCartService.clearCart(email);
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/checkOutCart", method = RequestMethod.POST)
	public String checkOutCart(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		shoppingCartService.checkOutCart(email);
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.POST)
	public String getShoppingCart(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		List<ShoppingCartItem> cart = shoppingCartService.getShoppingCart(email);
		return "/profile/itemList";
	}

}
