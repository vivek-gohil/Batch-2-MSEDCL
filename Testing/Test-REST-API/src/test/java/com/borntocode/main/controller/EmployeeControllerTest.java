package com.borntocode.main.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.borntocode.main.entity.Employee;
import com.borntocode.main.service.EmployeeService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockitoBean
	EmployeeService service;

	@Test
	void shouldReturnEmployeeById() throws Exception {

		Employee employee = new Employee(1L, "Vivek", "vivek@gmail.com");

		when(service.getEmployee(1L)).thenReturn(employee);

		mockMvc.perform(get("/employees/1")).andExpect(status().isOk()).andExpect(jsonPath("$.employeeId").value(1))
				.andExpect(jsonPath("$.employeeName").value("Vivek"));
	}

	@Test
	void shouldCreateEmployee() throws Exception {

		Employee employee = new Employee(1L, "Vivek", "vivek@gmail.com");

		when(service.saveEmployee(any())).thenReturn(employee);

		mockMvc.perform(
				post("/employees").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(employee)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.employeeId").value(1));
	}
}
