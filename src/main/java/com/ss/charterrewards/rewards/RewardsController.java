package com.ss.charterrewards.rewards;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.charterrewards.entity.Customer;

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

}
