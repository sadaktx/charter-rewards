package com.ss.charterrewards.rewards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.charterrewards.entity.Customer;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Integer>{
}
