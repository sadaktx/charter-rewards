package com.ss.charterrewards.rewards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.charterrewards.entity.Customer;

/**
* CustomerRepository extends from the JpaRepository and does all the CRUD operations for the Customer 
* 
* 
* @author Sadak Shaik
* 
*/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
