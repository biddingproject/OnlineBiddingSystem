package com.bidding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="customer")
	private List<Item> boughtItems = new ArrayList<Item>();
	
	@OneToMany(mappedBy="customer")
	private List<Bid> bidList = new ArrayList<Bid>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getBoughtItems() {
		return boughtItems;
	}

	public void setBoughtItems(List<Item> boughtItems) {
		this.boughtItems = boughtItems;
	}

	public List<Bid> getBidList() {
		return bidList;
	}

	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}

}
