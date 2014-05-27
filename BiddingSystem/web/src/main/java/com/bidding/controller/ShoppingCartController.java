package com.bidding.controller;

import java.util.List;

import javax.ejb.EJB;

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
			@RequestParam(value = "itemId", required = true) long itemId) {
		shoppingCartService.addItemToCart(itemId);
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/removeItemFromCart", method = RequestMethod.POST)
	public String removeItemFromCart(ModelMap model,
			@RequestParam(value = "itemId", required = true) long itemId) {
		shoppingCartService.removeItemFromCart(itemId);
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/clearCart", method = RequestMethod.POST)
	public String clearCart(ModelMap model) {
		shoppingCartService.clearCart();
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/checkOutCart", method = RequestMethod.POST)
	public String checkOutCart(ModelMap model) {
		shoppingCartService.checkOutCart();
		return "/profile/itemList";
	}
	
	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.POST)
	public String getShoppingCart(ModelMap model) {
		List<ShoppingCartItem> cart = shoppingCartService.getShoppingCart();
		return "/profile/itemList";
	}

}
