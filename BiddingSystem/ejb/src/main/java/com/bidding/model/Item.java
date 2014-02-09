package com.bidding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8948953875453666442L;

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
	@JoinColumn(name="item_list_id")
	private ItemList itemList;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@DecimalMax(value = "5")
	private int rating;
	
	@Size(max = 180)
	private String customerReview;

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

	public String getCustomerReview() {
		return customerReview;
	}

	public void setCustomerReview(String customerReview) {
		this.customerReview = customerReview;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	

}
