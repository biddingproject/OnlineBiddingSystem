package com.bidding.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Refund {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date refundDateTime;
	
	private Transaction transaction;
	
	@ManyToOne
	private Seller seller;
	
	@ManyToOne
	private Customer customer;

	public Date getRefundDateTime() {
		return refundDateTime;
	}

	public void setRefundDateTime(Date refundDateTime) {
		this.refundDateTime = refundDateTime;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
