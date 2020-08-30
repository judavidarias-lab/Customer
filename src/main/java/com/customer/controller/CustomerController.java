package com.customer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.controller.vo.CustomerVO;
import com.customer.controller.vo.ResponseVo;
import com.customer.model.Customer;
import com.customer.services.ICustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final ICustomerService customerService;
	
	@Autowired
	public CustomerController(ICustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<CustomerVO> getAllCustomer() {
		return this.customerService.getAllCustomer()
				.stream()
				.map( p ->  new CustomerVO(p.getId(), p.getName(), p.getIdentificator(), p.getAge(), p.isActive()))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}")
	public CustomerVO findByAge(@PathVariable Long id) {
		return  this.customerService.getCustomerById(id).map(this::toCustomerVO).orElse(null);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseVo> createCustomer(@RequestBody CustomerVO customerVo) {
		Customer customer = this.customerService.createCustomer(customerVo);
		if(customer != null)
			return new ResponseEntity<ResponseVo>(new ResponseVo("Succes",HttpStatus.OK.value(),""), HttpStatus.OK);
		else
			return new ResponseEntity<ResponseVo>(new ResponseVo("Error",HttpStatus.SERVICE_UNAVAILABLE.value(),"customer exist"),HttpStatus.SERVICE_UNAVAILABLE);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		this.customerService.deleteCustomer(id);
		return new ResponseEntity<>("Person has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAllCustomers() {
	

		this.customerService.deleteAllCustomer();

		return new ResponseEntity<>("All Persons have been deleted!", HttpStatus.OK);
	}

	@GetMapping(value = "/identificator/{identificator}")
	public CustomerVO findByAge(@PathVariable String identificator) {
		return this.customerService.findCustomerByIdentificator(identificator)
				
				.map( p ->  toCustomerVO(p))
				.orElse(null);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerVO> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerVO customerVo) {
		Customer customer = this.customerService.updateCustomer(id, customerVo);
		if (customer != null) {
			CustomerVO customerAux = new CustomerVO(customer.getId(), customer.getName(), customer.getIdentificator(), customer.getAge(), customer.isActive());
			return new ResponseEntity<>(customerAux, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	private CustomerVO toCustomerVO(Customer customer) {
		return new CustomerVO(customer.getId(), customer.getIdentificator(), customer.getName(), customer.getAge(), customer.isActive());
	}

}
