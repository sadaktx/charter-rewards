package com.ss.charterrewards.rewards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.charterrewards.entity.CustomerTransaction;

/**
* CustomerTransactionRepository extends from the JpaRepository 
* and does all the CRUD operations for the Customer Transactions 
* 
* 
* @author Sadak Shaik
* 
*/
@Repository
public interface CustomerTransactionRepository  extends JpaRepository<CustomerTransaction, Integer> {

}
