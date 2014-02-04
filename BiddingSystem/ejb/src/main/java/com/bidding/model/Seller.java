package com.bidding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Seller {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="seller")
	private List<ItemList> auctionedItemLists = new ArrayList<ItemList>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemList> getAuctionedItemLists() {
		return auctionedItemLists;
	}

	public void setAuctionedItemLists(List<ItemList> auctionedItemLists) {
		this.auctionedItemLists = auctionedItemLists;
	}
	

}
