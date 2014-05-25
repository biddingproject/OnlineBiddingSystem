package com.bidding.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JoinColumn(name = "number_of_black_marks")
	private int numberOfBlackMarks = 0;

	@OneToMany(mappedBy = "customer")
	private List<Transaction> boughtItems = new ArrayList<Transaction>();

	@OneToMany(mappedBy = "customer")
	private List<Bid> bidList = new ArrayList<Bid>();

	@OneToMany(mappedBy = "customer")
	private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Transaction> getBoughtItems() {
		return boughtItems;
	}

	public void setBoughtItems(List<Transaction> boughtItems) {
		this.boughtItems = boughtItems;
	}

	public List<Bid> getBidList() {
		return bidList;
	}

	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumberOfBlackMarks() {
		return numberOfBlackMarks;
	}

	public void setNumberOfBlackMarks(int numberOfBlackMarks) {
		this.numberOfBlackMarks = numberOfBlackMarks;
	}

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

}
