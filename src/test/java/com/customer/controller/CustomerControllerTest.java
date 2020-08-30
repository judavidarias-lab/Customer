package com.customer.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.customer.controller.vo.CustomerVO;
import com.customer.model.Customer;
import com.customer.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerServiceImpl service;

	@Test
	public void getCustomerThenReturnJsonResponse() throws Exception {
		Customer p1 = getCustomer();
		List<Customer> customers = Arrays.asList(p1);
		when(service.getAllCustomer()).thenReturn(customers);
		this.mockMvc.perform(get("/api/customer")).andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name", is(p1.getName())))
			.andExpect(jsonPath("$[0].age", is(p1.getAge())))
			.andExpect(jsonPath("$[0].active", is(p1.isActive())));
	}
	
	@Test
	public void createCustomerThenReturnJsonCustomerCreate() throws Exception {
		Customer p1 =getCustomer();
		CustomerVO p1vo = getCustomerVO();
		when(service.createCustomer(org.mockito.Mockito.any(CustomerVO.class))).thenReturn(p1);
		this.mockMvc.perform(post("/api/customer")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(p1vo)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is("Succes")))
			.andExpect(jsonPath("$.status", is(200)))
			.andExpect(jsonPath("$.messageError", is("")));
	}
	
	@Test
	public void putCustomerThenReturnJsonCustomerUpdate() throws Exception {
		Customer p1 = getCustomer();
		CustomerVO p1vo = getCustomerVO();
		when(service.updateCustomer(org.mockito.Mockito.anyLong(), org.mockito.Mockito.any(CustomerVO.class))).thenReturn(p1);
		this.mockMvc.perform(put("/api/customer/1")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(p1vo)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is(p1vo.getName())))
			.andExpect(jsonPath("$.age", is(p1vo.getAge())))
			.andExpect(jsonPath("$.active", is(p1vo.isActive())));
	}
	
	
	private Customer getCustomer() {
		Customer p1 = new Customer("Juan", "434", 10, true);
		p1.setId(1);
		return p1;
	}
	
	private CustomerVO getCustomerVO() {
		return new CustomerVO("Juan","434", 10, true);
	}
	
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
