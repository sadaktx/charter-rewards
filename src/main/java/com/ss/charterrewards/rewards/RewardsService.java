package com.ss.charterrewards.rewards;

import java.util.List;
import java.util.Optional;

import com.ss.charterrewards.entity.Customer;
import com.ss.charterrewards.entity.CustomerTransaction;

public interface RewardsService {
	public abstract List<Customer> getCustomerAll();
	public abstract Optional<Customer> getCustomerById(Integer id);
	public abstract List<Customer> addCustomers(List<Customer> customers);
	public abstract Customer updateCustomer(Customer customer, Long id);
	public abstract List<Customer> addCustomerTransactions(List<CustomerTransaction> customerTransactions);
	public abstract Customer updateCustomerTransaction(CustomerTransaction customerTransaction, Long id);
}
