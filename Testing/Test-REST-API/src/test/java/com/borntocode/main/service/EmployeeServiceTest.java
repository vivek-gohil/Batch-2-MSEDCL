package com.borntocode.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.borntocode.main.entity.Employee;
import com.borntocode.main.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeRepository repository;

	@InjectMocks
	private EmployeeService service;

	@Test
	void shouldReturnEmployee() {

		Employee employee = new Employee(1L, "Vivek", "vivek@gmail.com");

		when(repository.findById(1L)).thenReturn(Optional.of(employee));

		Employee result = service.getEmployee(1L);

		assertEquals("Vivek", result.getEmployeeName());

		verify(repository).findById(1L);
	}
}
