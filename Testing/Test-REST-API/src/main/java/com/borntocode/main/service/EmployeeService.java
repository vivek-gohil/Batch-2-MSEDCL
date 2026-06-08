package com.borntocode.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.borntocode.main.entity.Employee;
import com.borntocode.main.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository repository;

	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee getEmployee(Long id) {

		return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
	}

	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}
}
