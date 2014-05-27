package com.bidding.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.ShoppingCartRepository;
import com.bidding.model.ShoppingCartItem;

@Stateless
public class ShoppingCartService {

	@Inject
	private ShoppingCartRepository shoppingCartRepository;

	/**
	 * 
	 * @param itemListId
	 * @param quantity
	 * @param email
	 * @return
	 */
	public boolean addItemToCart(long itemListId, int quantity, String email) {
		shoppingCartRepository.addShoppingCartItem(itemListId, quantity, email);
		return false;
	}

	/**
	 * 
	 * @param shoppingCartItemId
	 * @return
	 */
	public boolean removeItemFromCart(long shoppingCartItemId) {
		shoppingCartRepository.removeItemFromCart(shoppingCartItemId);
		return false;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean clearCart(String email) {
		shoppingCartRepository.clearCart(email);
		return false;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkOutCart(String email) {
		return false;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public List<ShoppingCartItem> getShoppingCart(String email) {
		return shoppingCartRepository.getShoppingCartItemsByEmail(email);
	}

}
