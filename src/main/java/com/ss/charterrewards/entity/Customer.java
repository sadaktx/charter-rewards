package com.ss.charterrewards.entity;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Customer implements ICustomerRewards {

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
	public Long getTotalRewardPoints() {
		Long totalRewardPoints = 0L;
		for (CustomerTransaction customerTransaction : this.customerTransactions) {
			totalRewardPoints += customerTransaction.getRewardPoints();
		}
		return totalRewardPoints;
		
	}
	public HashMap<String, Long> getMonthlyRewardPoints() {
		this.monthlyRewardPoints = new HashMap<> ();
		for (CustomerTransaction customerTransaction : this.customerTransactions) {
			String month = customerTransaction.getTransactionDate().getMonth().toString();
			Long monthlyPoints = this.monthlyRewardPoints.get(month);
			if( this.monthlyRewardPoints.containsKey(month)) {
				this.monthlyRewardPoints.put(month, Long.valueOf(monthlyPoints + customerTransaction.getRewardPoints()));
			} else {
				this.monthlyRewardPoints.put(month, Long.valueOf(customerTransaction.getRewardPoints()));
			}
		}
		return this.monthlyRewardPoints;
	}
	public Long getTotalPurchaseAmount() {
		Long totalPurchaseAmount = 0L;
		for (CustomerTransaction customerTransaction : this.customerTransactions) {
			totalPurchaseAmount += customerTransaction.getAmount();
		}
		return totalPurchaseAmount;
	}
}
