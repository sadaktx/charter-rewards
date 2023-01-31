package com.ss.charterrewards.rewards;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.charterrewards.entity.Customer;

@Service
public class RewardsService {
	
	@Autowired
	private CustomerJpaRepository customerRepository;
	
	public List<Customer> getCustomerAll() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerById(Integer id) {
		return customerRepository.findById(id);
	}

}
