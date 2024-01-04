package com.jsp.ecommerce.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.jsp.ecommerce.dto.Customer;

@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	

	

	List<Customer> findByEmailOrMobile(String email, long mobile);

}
