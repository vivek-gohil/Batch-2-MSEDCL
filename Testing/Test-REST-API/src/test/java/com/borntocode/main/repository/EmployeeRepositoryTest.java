package com.borntocode.main.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.borntocode.main.entity.Employee;

@DataJpaTest
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	void shouldFindEmployeeByEmail() {

		Employee employee = new Employee(null, "Vivek", "vivek@gmail.com");

		repository.save(employee);

		Optional<Employee> result = repository.findByEmail("vivek@gmail.com");

		assertTrue(result.isPresent());
		assertEquals("Vivek", result.get().getEmployeeName());
	}
}
