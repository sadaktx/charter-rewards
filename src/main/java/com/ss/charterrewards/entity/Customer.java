package com.ss.charterrewards.entity;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

/**
* Customer is the main entity that will have the Customer information
* It has a One to Many relationship with the Customer Transactions entity
* 
* @author Sadak Shaik
* 
*/
@Entity
public class Customer {

	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY)
	private List<CustomerTransaction> customerTransactions;
	@JsonInclude
	@Transient
	private HashMap<String, Long> monthlyRewardPoints;
	@JsonInclude
	@Transient
	private long totalRewardPoints;
	@JsonInclude
	@Transient
	private long totalPurchaseAmount;
	

	public Customer() {
		super();
	}
	
	public Customer(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public long getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<CustomerTransaction> getCustomerTransactions() {
		return customerTransactions;
	}
	public void setCustomerTransactions(List<CustomerTransaction> customerTransactions) {
		this.customerTransactions = customerTransactions;
	}
	public HashMap<String, Long> getMonthlyRewardPoints() {
		return monthlyRewardPoints;
	}
	public long getTotalRewardPoints() {
		return totalRewardPoints;
	}
	public long getTotalPurchaseAmount() {
		return totalPurchaseAmount;
	}
	public void setMonthlyRewardPoints(HashMap<String, Long> monthlyRewardPoints) {
		this.monthlyRewardPoints = monthlyRewardPoints;
	}
	public void setTotalRewardPoints(long totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}
	public void setTotalPurchaseAmount(long totalPurchaseAmount) {
		this.totalPurchaseAmount = totalPurchaseAmount;
	}
}
