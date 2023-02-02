package com.ss.charterrewards.rewards;

import com.ss.charterrewards.entity.Customer;

public interface IRewardsService {
	public abstract void processCustomerRewards(Customer customer);
}
