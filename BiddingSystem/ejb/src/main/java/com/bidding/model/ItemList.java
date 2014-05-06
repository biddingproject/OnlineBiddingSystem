package com.bidding.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "item_list")
public class ItemList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	private int numberOfItems;

	@Column(name = "current_bid")
	private Float currentBid;

	@Column(name = "base_bid")
	private Float baseBid;
	
	private boolean isBiddable;

	@OneToOne
	private ItemCategory itemCategory;

	@NotNull
	@Size(min = 3, max = 50)
	private String name;

	@Size(min = 10, max = 1000)
	@NotNull
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bidStartTimeStamp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expireTimeStamp;

	@Column(name = "buy_it_now_price")
	private Float buyItNowPrice;

	@OneToMany(mappedBy = "itemList")
	private List<Item> soldItems = new ArrayList<Item>();

	@OneToMany(mappedBy = "itemList")
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

	public List<Bid> getBidList() {
		return bidList;
	}

	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}

	public Date getExpireTimeStamp() {
		return expireTimeStamp;
	}

	public void setExpireTimeStamp(Date expireTimeStamp) {
		this.expireTimeStamp = expireTimeStamp;
	}

	public Date getBidStartTimeStamp() {
		return bidStartTimeStamp;
	}

	public void setBidStartTimeStamp(Date bidStartTimeStamp) {
		this.bidStartTimeStamp = bidStartTimeStamp;
	}

	public List<Item> getSoldItems() {
		return soldItems;
	}

	public void setSoldItems(List<Item> soldItems) {
		this.soldItems = soldItems;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public boolean getIsBiddable() {
		return isBiddable;
	}

	public void setIsBiddable(boolean isBiddable) {
		this.isBiddable = isBiddable;
	}

}
