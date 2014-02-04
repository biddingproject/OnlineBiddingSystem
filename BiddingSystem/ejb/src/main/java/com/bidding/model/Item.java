package com.bidding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@Column(name = "price_bought")
	private Float priceBought;
	
	@Size(min = 10, max = 1000)
	@NotNull
	private String description;
	
	@ManyToOne
	private ItemList itemList;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expireTimeStamp;
	
	@ManyToOne
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpireTimeStamp() {
		return expireTimeStamp;
	}

	public void setExpireTimeStamp(Date expireTimeStamp) {
		this.expireTimeStamp = expireTimeStamp;
	}

	public Float getPriceBought() {
		return priceBought;
	}

	public void setPriceBought(Float priceBought) {
		this.priceBought = priceBought;
	}

	public ItemList getItemList() {
		return itemList;
	}

	public void setItemList(ItemList itemList) {
		this.itemList = itemList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
