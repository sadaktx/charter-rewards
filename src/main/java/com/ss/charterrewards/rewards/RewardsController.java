package com.ss.charterrewards.rewards;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.charterrewards.entity.Customer;
import com.ss.charterrewards.entity.CustomerTransaction;

/**
* RewardsController has all the CRUD REST end points for the clients to access
* customer and their rewards information. All responses are returned in JSON.
* 
* @author Sadak Shaik
* 
*/
@RestController
@RequestMapping(value="/charter-rewards")
public class RewardsController {
	
	@Autowired
	private RewardsService rewardsService;
	
	/**
	* Handles GET requests to /customers endpoint and returns all the customers in the application.  
	* 
	* @return all the customers in the application
	*/
	@GetMapping(value = "/customers")
	public List<Customer>getCustomerAll() {
		return rewardsService.getCustomerAll();
	}
	
	/**
	* Handles GET requests to /customer/{id} endpoint and returns the customer with {id}.  
	* 
	* @param id the customer id
	* @return all the customers in the application
	*/
	@GetMapping(value = "/customer/{id}")
	public Optional<Customer>getCustomer(@PathVariable("id") Integer id) {
		return rewardsService.getCustomerById(id);
	}
	
	/**
	* Handles POST requests to /customers/add endpoint and adds the customer(s).  
	* 
	* @param customers the list of customers to be added in JSON
	* @return all the customers in the application
	*/
	@PostMapping("/customers/add")
	public List<Customer> addCustomers(@RequestBody List<Customer> customers) {
		return rewardsService.addCustomers(customers);
	}
	
	/**
	* Handles PUT requests to /customer/{id} endpoint and updates the customer with {id}.  
	* 
	* @param customer the customer to be updated in JSON
	* @param id the customer id 
	* @return all the customers in the application
	*/
	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
		return rewardsService.updateCustomer(customer, id);
	}
	
	/**
	* Handles POST requests to /customer/{customerId}/transactions/add endpoint 
	* and adds the customer transactions(s) to the customer with {customerId}  
	* 
	* @param customerTransactions the list of customerTransactions to be added in JSON
	* @param customerId the customer id 
	* @return all the customerTransactions 
	*/
	@PostMapping("/customer/{customerId}/transactions/add")
	public List<CustomerTransaction> addCustomerTransactions(@RequestBody List<CustomerTransaction> customerTransactions, @PathVariable Integer customerId) {
		return rewardsService.addCustomerTransactions(customerTransactions, customerId);
	}
	
	/**
	* Handles PUT requests to /customer/transaction/{id} endpoint and updates the customer transaction with {id}.  
	* 
	* @param customerTransaction the customer transaction to be updated in JSON
	* @param id the customer transaction id 
	* @return the updated customer transaction
	*/
	@PutMapping("/customer/transaction/{id}")
	public CustomerTransaction updateCustomerTransaction(@RequestBody CustomerTransaction customerTransaction, @PathVariable Integer id) {
		return rewardsService.updateCustomerTransaction(customerTransaction, id);
	}

}
