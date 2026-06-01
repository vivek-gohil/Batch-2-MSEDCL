package com.msedcl.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msedcl.main.entity.Employee;
import com.msedcl.main.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService {

	private final EmployeeRepository repository;

	public List<Employee> getEmployeesByDepartment(String deptName) {
		return repository.getEmployeesByDepartment(deptName);
	}

	public List<Employee> getHighSalaryEmployees(Double salary) {
		return repository.findEmployeesWithSalaryGreaterThan(salary);
	}

	public List<Employee> getEmployeesUsingNamedQuery(String deptName) {
		return repository.findByDepartmentName(deptName);
	}
}
