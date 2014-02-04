package com.bidding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="item_list")
public class ItemList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="seller_id")
	private Seller seller;
	
	@Column(name = "current_bid")
	private Float currentBid;
	
	@Column(name = "base_bid")
	private Float baseBid;
	
	@Column(name = "buy_it_now_price")
	private Float buyItNowPrice;
	
	@OneToMany(mappedBy="itemList")
	private List<Item> itemList = new ArrayList<Item>();
	
	@OneToMany(mappedBy="itemList")
	private List<Bid> bidList = new ArrayList<Bid>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Float getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(Float currentBid) {
		this.currentBid = currentBid;
	}

	public Float getBaseBid() {
		return baseBid;
	}

	public void setBaseBid(Float baseBid) {
		this.baseBid = baseBid;
	}

	public Float getBuyItNowPrice() {
		return buyItNowPrice;
	}

	public void setBuyItNowPrice(Float buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Bid> getBidList() {
		return bidList;
	}

	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}

}
