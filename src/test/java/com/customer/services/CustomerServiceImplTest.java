package com.customer.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.customer.controller.vo.CustomerVO;
import com.customer.model.Customer;
import com.customer.model.repository.CustomerRepository;

@WebMvcTest(CustomerServiceImpl.class)
public class CustomerServiceImplTest {
	
	
	@Autowired
	private CustomerServiceImpl service;

	@MockBean
	private CustomerRepository repository;

	@Test
	public void getCustomerThenReturnList() throws Exception {
		Customer p1 = new Customer("Juan","1234", 10, true);
		List<Customer> persons = Arrays.asList(p1);
		when(repository.findAll()).thenReturn(persons);
		List<Customer> customers = service.getAllCustomer();
		Assert.assertEquals(1, customers.size());
	}
	
	@Test
	public void createCustomerThenReturnCustomerNull() throws Exception {
		Customer p1 = new Customer("Juan","1234", 10, true);
		p1.setId(1);
		CustomerVO pVo = new CustomerVO("Juan","1234", 10, true);
		List<Customer> persons = Arrays.asList(p1);
		when(repository.findByIdentificator("1234")).thenReturn(persons);
		when(repository.save(p1)).thenReturn(p1);
		Customer customerResponse = service.createCustomer(pVo);
		Assert.assertNull(customerResponse);
	}
	
	@Test
	public void createCustomerThenReturnCustomer() throws Exception {
		Customer p1 = new Customer("Juan","1234", 10, true);
		p1.setId(1);
		CustomerVO pVo = new CustomerVO("Juan","1234", 10, true);
		when(repository.findByIdentificator("1234")).thenReturn(new ArrayList<>());
		when(repository.save(Mockito.any(Customer.class))).thenReturn(p1);
		Customer customerResponse = service.createCustomer(pVo);
		Assert.assertEquals(p1.getId(),customerResponse.getId());
	}
	
	@Test
	public void updateCustomerThenReturnCustomerNull() throws Exception {
		Customer p1 = new Customer("Juan","1234", 10, true);
		p1.setId(1);
		CustomerVO pVo = new CustomerVO("Juan","1234", 10, true);
		when(repository.findById(p1.getId())).thenReturn(Optional.empty());
		when(repository.save(Mockito.any(Customer.class))).thenReturn(p1);
		Customer customerResponse = service.updateCustomer(p1.getId(), pVo);
		Assert.assertNull(customerResponse);
	}
	
	@Test
	public void updateCustomerThenReturnCustomer() throws Exception {
		Customer p1 = new Customer("Juan","1234", 10, true);
		p1.setId(1);
		CustomerVO pVo = new CustomerVO("Juan","1234", 10, true);
		when(repository.findById(p1.getId())).thenReturn(Optional.of(p1));
		when(repository.save(Mockito.any(Customer.class))).thenReturn(p1);
		Customer customerResponse = service.updateCustomer(p1.getId(), pVo);
		Assert.assertEquals(p1.getIdentificator(),customerResponse.getIdentificator());
	}
	
	

}
