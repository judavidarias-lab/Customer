package com.customer.services;

import java.util.List;
import java.util.Optional;

import com.customer.controller.vo.CustomerVO;
import com.customer.model.Customer;

public interface ICustomerService {
	
	public List<Customer> getAllCustomer();

	public Optional<Customer> getCustomerById(Long id);

	public Customer createCustomer(CustomerVO customerVo);

	public Customer updateCustomer(Long id, CustomerVO customerVo);

	public Optional<Customer> findCustomerByIdentificator(String identificator);

	public void deleteCustomer(Long id);

	public void deleteAllCustomer() ;

}
