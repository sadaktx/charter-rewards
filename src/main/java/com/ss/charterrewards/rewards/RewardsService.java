package com.ss.charterrewards.rewards;

import java.util.List;
import java.util.Optional;

import com.ss.charterrewards.entity.Customer;
import com.ss.charterrewards.entity.CustomerTransaction;

public interface RewardsService {
	public abstract List<Customer> getCustomerAll();
	public abstract Optional<Customer> getCustomerById(Integer id);
	public abstract List<Customer> addCustomers(List<Customer> customers);
	public abstract Customer updateCustomer(Customer customer, Integer id);
	public abstract List<CustomerTransaction> addCustomerTransactions(List<CustomerTransaction> customerTransactions, Integer customerId);
	public abstract CustomerTransaction updateCustomerTransaction(CustomerTransaction customerTransaction, Integer id);
}
