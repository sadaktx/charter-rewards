package com.ss.charterrewards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ss.charterrewards.entity.Customer;
import com.ss.charterrewards.entity.CustomerTransaction;
import com.ss.charterrewards.rewards.CustomerRepository;
import com.ss.charterrewards.rewards.RewardsService;

@ExtendWith(MockitoExtension.class)
public class RewardsServiceMockTest {

	@InjectMocks
	private RewardsService rewardsService;
	@Mock
	private CustomerRepository customerRpository;
	
	private List<Customer> customers;
	private List<CustomerTransaction> customerTransactions;
	
	@BeforeEach 
	public void setUp()
    {
		customers = new ArrayList<Customer>();
		customers.add(new Customer(1, "Test 1", "Last 1"));
		customers.add(new Customer(2, "Test 2", "Last 2"));
		customerTransactions = new ArrayList<CustomerTransaction>();
		customerTransactions.add(new CustomerTransaction(1, customers.get(0), "Test Transaction 1", LocalDateTime.parse("2023-01-01T09:00:00"), Long.valueOf(150)));
		customerTransactions.add(new CustomerTransaction(1, customers.get(0), "Test Transaction 2", LocalDateTime.parse("2023-01-15T09:00:00"), Long.valueOf(250)));
		customers.get(0).setCustomerTransactions(customerTransactions);
		customers.get(1).setCustomerTransactions(new ArrayList<CustomerTransaction>());
    }

	@Test
	public void testFindCustomerById() {
		when(customerRpository.findById(1)).thenReturn(Optional.ofNullable(customers.get(0)));
		Optional<Customer> testCustomer = rewardsService.getCustomerById(1);
		HashMap<String, Long> monthlyRewards = new HashMap<>();
		monthlyRewards.put("JANUARY", Long.valueOf(500));
		assertEquals("Test 1", testCustomer.get().getFirstName());
		assertEquals("Last 1", testCustomer.get().getLastName());
		assertEquals(400, testCustomer.get().getTotalPurchaseAmount());
		assertEquals(500, testCustomer.get().getTotalRewardPoints());
		assertEquals(monthlyRewards, testCustomer.get().getMonthlyRewardPoints());
	}
	
	@Test
	public void testFindCustomerAll() {
		when(customerRpository.findAll()).thenReturn((customers));
		List<Customer> testCustomers = rewardsService.getCustomerAll();
		assertEquals(2, testCustomers.size());
		Customer testCustomer2 = testCustomers.get(1);
		HashMap<String, Long> monthlyRewards = new HashMap<>();
		assertEquals("Test 2", testCustomer2.getFirstName());
		assertEquals("Last 2", testCustomer2.getLastName());
		assertEquals(0, testCustomer2.getTotalRewardPoints());
		assertEquals(monthlyRewards, testCustomer2.getMonthlyRewardPoints());
	}
}
