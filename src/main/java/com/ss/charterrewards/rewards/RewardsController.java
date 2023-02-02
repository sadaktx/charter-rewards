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
* customer and their rewards information
* 
* @author Sadak Shaik
* 
*/
@RestController
@RequestMapping(value="/charter-rewards")
public class RewardsController {
	
	@Autowired
	private RewardsService rewardsService;
	
	@GetMapping(value = "/customers")
	public List<Customer>getCustomerAll() {
		return rewardsService.getCustomerAll();
	}
	@GetMapping(value = "/customer/{Id}")
	public Optional<Customer>getCustomer(@PathVariable("Id") Integer id) {
		return rewardsService.getCustomerById(id);
	}
	@PostMapping("/customers/add")
	public List<Customer> addCustomers(@RequestBody List<Customer> customers) {
		return rewardsService.addCustomers(customers);
	}
	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
		return rewardsService.updateCustomer(customer, id);
	}
	@PostMapping("/customer/transactions/add")
	public List<Customer> addCustomerTransactions(@RequestBody List<CustomerTransaction> customerTransactions) {
		return rewardsService.addCustomerTransactions(customerTransactions);
	}
	@PutMapping("/customer/transaction/{id}")
	public Customer updateCustomerTransaction(@RequestBody CustomerTransaction customerTransaction, @PathVariable Long id) {
		return rewardsService.updateCustomerTransaction(customerTransaction, id);
	}

}
