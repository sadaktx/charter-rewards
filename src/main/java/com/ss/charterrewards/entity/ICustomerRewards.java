package com.ss.charterrewards.entity;

import java.util.HashMap;

public interface ICustomerRewards {
	public abstract Long getTotalRewardPoints();
	public abstract HashMap<String, Long> getMonthlyRewardPoints();
	public abstract Long getTotalPurchaseAmount();
}
