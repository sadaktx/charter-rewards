package com.ss.charterrewards.rewards;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.charterrewards.entity.Customer;
import com.ss.charterrewards.entity.CustomerTransaction;

/**
* RewardsService has all the business logic for processing all customer transactions
* and calculating the corresponding reward points
* 
* @author Sadak Shaik
* 
*/
@Service
public class RewardsServiceImpl implements RewardsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;
	
	/**
	* Get all the customers in the application.  
	* 
	* @return      all the customers in the application
	* 
	*/
	public List<Customer> getCustomerAll() {
		List<Customer> customers =  customerRepository.findAll();
		customers.forEach( customer -> {
			processCustomerRewards(customer);
		});
		return customers;
	}

	/**
	* Returns the customer that is identified by the id that is passed 
	*
	* @param  id  id of the customer that this method returns
	* @return     Customer object that matches the id
	*/
	public Optional<Customer> getCustomerById(Integer id) {
		Optional<Customer> customer = customerRepository.findById(id);
		customer.ifPresent( value -> {
			processCustomerRewards(value);
		});
		return customer;
	}
	
	/**
	* Returns the customers that were added 
	*
	* @param  customers  a list of customers that need to be added
	* @return     List of Customer objects that were added
	*/
	public List<Customer> addCustomers(List<Customer> customers) {
		return customerRepository.saveAll(customers);
	}

	/**
	* Updates the customer that is identified by the id that is passed based on 
	* the customer information that is provided in the request body
	*
	* @param  updatedCustomer  customer information to be updated
	* @param  id  id of the customer that this method returns
	* @return     Customer object that matches the id
	*/
	public Customer updateCustomer(Customer updatedCustomer, Integer id) {
		return customerRepository.findById(id)
			      .map(customer -> {
			        customer.setFirstName(updatedCustomer.getFirstName());
			        customer.setLastName(updatedCustomer.getLastName());
			        return customerRepository.save(customer);
			      })
			      .orElseGet(() -> {
			    	  updatedCustomer.setId(id);
			        return customerRepository.save(updatedCustomer);
			      });
		
	}

	/**
	* Adds customer transactions 
	*
	* @param  customerTransactions  a list of customer transactions
	* 
	* @return     Customer object that matches the id
	*/
	public List<CustomerTransaction> addCustomerTransactions(List<CustomerTransaction> customerTransactions, Integer customerId) {
		 
		return customerRepository.findById(customerId)
		 	.map(customer -> {
				customerTransactions.forEach(customerTransaction -> {
					customerTransaction.setCustomer(customer);
				});
				return customerTransactionRepository.saveAll(customerTransactions);
			})
		 	.orElseGet(() -> {
		 		return null;
		 	});
		 
		
	}

	public CustomerTransaction updateCustomerTransaction(CustomerTransaction updatedCustomerTransaction, Integer id) {
		return customerTransactionRepository.findById(id)
				.map(customerTransaction -> {
					if (updatedCustomerTransaction.getDescription() != null) {
						customerTransaction.setDescription(updatedCustomerTransaction.getDescription());
					}
					if (updatedCustomerTransaction.getAmount() != null) {
						customerTransaction.setAmount(updatedCustomerTransaction.getAmount());
					}
					if (updatedCustomerTransaction.getTransactionDate() != null) {
						customerTransaction.setTransactionDate(updatedCustomerTransaction.getTransactionDate());
					}
					return customerTransactionRepository.save(customerTransaction);
				})
				.orElseGet(() -> {
			    	  updatedCustomerTransaction.setId(id);
			        return customerTransactionRepository.save(updatedCustomerTransaction);
			      });
	}
	
	/**
	* Calculates the totalRewards, totalPurchaseAmount and monthlyRewards for a customer 
	*
	* @param  customer  customer object that needs rewards calculated and updated
	* 
	*/
	private void processCustomerRewards(Customer customer) {
		Long totalRewardPoints = 0L;
		Long totalPurchaseAmount = 0L;
		HashMap<String, Long> monthlyRewardPoints = new HashMap<> ();
		for (CustomerTransaction customerTransaction : customer.getCustomerTransactions()) {
			totalPurchaseAmount += customerTransaction.getAmount();
			calculateRewardPoints(customerTransaction);
			totalRewardPoints += customerTransaction.getRewardPoints();
			calculateMonthlyRewardPoints(monthlyRewardPoints, customerTransaction);
		}
		customer.setTotalPurchaseAmount(totalPurchaseAmount);
		customer.setTotalRewardPoints(totalRewardPoints);
		customer.setMonthlyRewardPoints(monthlyRewardPoints);
	}

	/**
	* Calculates the monthlyRewards for a customer 
	*
	* @param monthlyRewardPoints  monthly reward points to be updated based on customer transaction
	* @param customerTransaction  customer transaction to calculate the reward points	
	* 
	*/
	private void calculateMonthlyRewardPoints(HashMap<String, Long> monthlyRewardPoints,
			CustomerTransaction customerTransaction) {
		String month = customerTransaction.getTransactionDate().getMonth().toString();
		Long monthlyPoints = monthlyRewardPoints.get(month);
		if( monthlyRewardPoints.containsKey(month)) {
			monthlyRewardPoints.put(month, Long.valueOf(monthlyPoints + customerTransaction.getRewardPoints()));
		} else {
			monthlyRewardPoints.put(month, Long.valueOf(customerTransaction.getRewardPoints()));
		}
	}
	
	/**
	* Calculates the reward points for a customer transaction (business logic)
	* receives 2 points for every dollar spent over $100 in each transaction, 
	* plus 1 point for every dollar spent between $50 and $100 in each transaction
	*
	* @param monthlyRewardPoints  monthly reward points to be updated based on customer transaction
	* @param customerTransaction  customer transaction to calculate the reward points	
	* 
	*/
	private void calculateRewardPoints(CustomerTransaction customerTransaction) {
		Long rewardPoints = 0L;
		Long transactionAmount = customerTransaction.getAmount();
		if (transactionAmount > 100) {
			rewardPoints = 50 + (transactionAmount - 100) * 2;
		} else if (transactionAmount > 50 && transactionAmount <= 100){
			rewardPoints = transactionAmount - 50;
		}
		customerTransaction.setRewardPoints(rewardPoints);
	}
}
