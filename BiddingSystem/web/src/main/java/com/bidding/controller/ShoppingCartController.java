package com.bidding.controller;

import java.util.Iterator;
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

	/**
	 * 
	 * @param model
	 * @param itemListId
	 * @param quantity
	 * @return
	 */
	@RequestMapping(value = "/addItemToCart", method = RequestMethod.POST)
	public String getItemListPage(
			ModelMap model,
			@RequestParam(value = "itemListId", required = true) long itemListId,
			@RequestParam(value = "quantity", required = true) int quantity) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		shoppingCartService.addItemToCart(itemListId, quantity, email);
		List<ShoppingCartItem> cartItems = shoppingCartService.getShoppingCart(email);
		model.put("cartItems", cartItems);
		return "/profile/shoppingCart";
	}

	/**
	 * 
	 * @param model
	 * @param shoppingCartItem
	 * @return
	 */
	@RequestMapping(value = "/removeItemFromCart", method = RequestMethod.POST)
	public String removeItemFromCart(
			ModelMap model,
			@RequestParam(value = "cartItemId", required = true) long cartItemId) {
		shoppingCartService.removeItemFromCart(cartItemId);
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		model.put("cartItems", shoppingCartService.getShoppingCart(email));
		return "/profile/shoppingCart";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/clearCart", method = RequestMethod.POST)
	public String clearCart(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		shoppingCartService.clearCart(email);
		model.put("cartItems", shoppingCartService.getShoppingCart(email));
		return "/profile/shoppingCart";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkOutCart", method = RequestMethod.POST)
	public String checkOutCart(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		model.put("cartItems", shoppingCartService.getShoppingCart(email));
		return "/profile/shoppingCart";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.GET)
	public String getShoppingCart(ModelMap model) {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		List<ShoppingCartItem> cart = shoppingCartService
				.getShoppingCart(email);
		for (ShoppingCartItem shoppingCartItem : cart) {
			System.out.println(shoppingCartItem.getId());
			System.out.println(shoppingCartItem.getQuantity());
			//System.out.println(shoppingCartItem.getCustomer().getId());
		}

		model.put("cartItems", shoppingCartService.getShoppingCart(email));
		return "/profile/shoppingCart";
	}

}
