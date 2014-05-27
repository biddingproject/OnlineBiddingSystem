package com.bidding.service;

import java.util.List;

import javax.ejb.Stateless;

import com.bidding.controller.ShoppingCart;
import com.bidding.model.ShoppingCartItem;

@Stateless
public class ShoppingCartService {

	public boolean addItemToCart(long itemId) {
		return false;
	}

	public boolean removeItemFromCart(long itemId) {
		return false;
	}

	public boolean clearCart() {
		return false;
	}

	public boolean checkOutCart() {
		return false;
	}

	public List<ShoppingCartItem> getShoppingCart() {
		return null;
	}
	
	

}
