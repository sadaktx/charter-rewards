package com.ss.charterrewards.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

/**
* CustomerTransaction represents a single transaction that a customer made
* It has a Many to One relationship with the Customer entity
* 
* @author Sadak Shaik
* 
*/
@Entity
public class CustomerTransaction {
	@Id
	private Integer id;
	private String description;
	private LocalDateTime transactionDate;
	@JsonInclude
	@Transient
	private Long rewardPoints;
	private Long amount;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
    private Customer customer;
	
	public CustomerTransaction() {
		super();
	}
	
	public CustomerTransaction(Integer id, Customer customer, String description, LocalDateTime transactionDate, Long amount) {
		super();
		this.id = id;
		this.customer = customer;
		this.description = description;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}

	public long getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getRewardPoints() {
		return rewardPoints;
	}
	
	public void setRewardPoints(Long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public String toString() {
		return "CustomerTransaction [id=" + id + ", customer=" + customer + ", description=" + description + ", transactionDate=" + transactionDate
				+ "rewardPoints=" + rewardPoints + ", amount=" + amount + "]";
	}

}
