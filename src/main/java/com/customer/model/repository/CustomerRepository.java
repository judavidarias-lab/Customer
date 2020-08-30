package com.customer.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByIdentificator(String identificator);
}
