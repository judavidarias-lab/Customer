package com.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.controller.vo.CustomerVO;
import com.customer.model.Customer;
import com.customer.model.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements ICustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomer() {
		return this.customerRepository.findAll();
	}

	public Optional<Customer> getCustomerById(Long id) {
		return this.customerRepository.findById(id);
	}

	@Transactional
	public Customer createCustomer(CustomerVO customerVo) {
		Optional<Customer> customerOpt = findCustomerByIdentificator(customerVo.getIdentificator());
		if(!customerOpt.isPresent()) {
			Customer customer = new Customer(customerVo.getName(), customerVo.getIdentificator(), customerVo.getAge(),
					customerVo.isActive());
			customer = this.customerRepository.save(customer);
			return customer;
		}else {
			return null;
		}
		
	}

	@Transactional
	public Customer updateCustomer(Long id, CustomerVO customerVo) {
		Optional<Customer> optPerson = this.customerRepository.findById(id);
		if (optPerson.isPresent()) {
			Customer customer = optPerson.get();
			customer.setActive(customerVo.isActive());
			customer.setIdentificator(customerVo.getIdentificator());
			customer.setAge(customerVo.getAge());
			customer.setName(customerVo.getName());
			this.customerRepository.save(customer);
			return customer;
		}
		return null;
	}

	public Optional<Customer> findCustomerByIdentificator(String identificator) {
		List<Customer> customer = this.customerRepository.findByIdentificator(identificator);
		if(customer.isEmpty())
			return Optional.empty();
		else
			return Optional.of(customer.get(0));
	}

	@Transactional
	public void deleteCustomer(Long id) {
		this.customerRepository.deleteById(id);
	}

	@Transactional
	public void deleteAllCustomer() {
		this.customerRepository.deleteAll();
	}

}
