package com.bidding.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8948953875453666442L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
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
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date itemBoughtTime;
	
	private int quantity;
	
	private float disCountRate = 0.0f;
	
	private boolean isRefunded = false;
	
	private float transactionAmount;
	
	@OneToOne
	private Refund refund;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Date getItemBoughtTime() {
		return itemBoughtTime;
	}

	public void setItemBoughtTime(Date itemBoughtTime) {
		this.itemBoughtTime = itemBoughtTime;
	}

	public boolean isRefunded() {
		return isRefunded;
	}

	public void setRefunded(boolean isRefunded) {
		this.isRefunded = isRefunded;
	}

	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getDisCountRate() {
		return disCountRate;
	}

	public void setDisCountRate(float disCountRate) {
		this.disCountRate = disCountRate;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	

}
