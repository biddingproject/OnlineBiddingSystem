package com.bidding.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.bidding.model.Customer;
import com.bidding.model.ItemList;
import com.bidding.model.ShoppingCartItem;
import com.bidding.model.User;

@Stateless
public class ShoppingCartRepository {

	@Inject
	private EntityManager em;

	@Inject
	private ItemListRepository itemListRepository;

	@Inject
	private UserRepository userRepository;

	/**
	 * 
	 * @param cartItemId
	 * @return
	 */
	public ShoppingCartItem getShoppingCartItemById(long cartItemId) {
		return em.find(ShoppingCartItem.class, cartItemId);
	}

	/**
	 * 
	 * @param itemListId
	 * @param quantity
	 * @param email
	 */
	public void addShoppingCartItem(long itemListId, int quantity, String email) {

		User user = userRepository.getUserByEmail(email);
		ItemList itemList = itemListRepository.findById(itemListId);
		ShoppingCartItem item = new ShoppingCartItem();
		Customer customer = user.getCustomer();

		item.setQuantity(quantity);
		item.setCustomer(customer);
		item.setItemList(itemList);

		em.persist(item);
		customer.getShoppingCartItems().add(item);

	}

	/**
	 * 
	 * @param shoppingCartItemId
	 */
	public void removeItemFromCart(long shoppingCartItemId) {

		ShoppingCartItem item = getShoppingCartItemById(shoppingCartItemId);
		if (item != null) {
			em.remove(item);
		}
		// em.remove(em.contains(item) ? item : em.merge(item));
	}

	/**
	 * 
	 * @param email
	 */
	public void clearCart(String email) {

		Customer customer = userRepository.getUserByEmail(email).getCustomer();
		List<ShoppingCartItem> cartItems = customer.getShoppingCartItems();

		if (cartItems != null & cartItems.size() > 0) {

			for (ShoppingCartItem shoppingCartItem : cartItems) {
				em.remove(shoppingCartItem);
				// em.remove(em.contains(shoppingCartItem) ? shoppingCartItem :
				// em.merge(shoppingCartItem));
			}

		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public List<ShoppingCartItem> getShoppingCartItemsByEmail(String email) {
		Customer customer = userRepository.getUserByEmail(email).getCustomer();
		List<ShoppingCartItem> cartItems = customer.getShoppingCartItems();
		for (ShoppingCartItem shoppingCartItem : cartItems) {

		}
		return cartItems;
	}
}
