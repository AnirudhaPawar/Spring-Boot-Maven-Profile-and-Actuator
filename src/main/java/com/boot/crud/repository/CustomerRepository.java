package com.boot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.crud.entity.Customer;

/**
 * @author Anirudha Pawar
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findByFirstName(String fName);
	
	/**** Procedure call****/
	@Query(name="callCustomerProc", value="call GetCustomerById(:id)", nativeQuery=true)
	Customer callCustomerProc(@Param(value = "id") int id);
	
}
