package com.bidding.service;

import java.util.List;

import com.bidding.model.ShoppingCartItem;
import com.bidding.model.Transaction;

public interface IPaymentService {
	
	public List<ShoppingCartItem> Pay(List<ShoppingCartItem> cartItems);
	
	public void Refund(Transaction transaction);

}
