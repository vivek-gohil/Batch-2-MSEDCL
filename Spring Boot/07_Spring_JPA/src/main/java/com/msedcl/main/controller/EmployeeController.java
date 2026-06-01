package com.msedcl.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.entity.Employee;
import com.msedcl.main.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	// Test Custom Query
	// http://localhost:8080/employees/salary/50000
	@GetMapping("/salary/{salary}")
	public List<Employee> getEmployeesBySalary(@PathVariable Double salary) {
		return service.getHighSalaryEmployees(salary);
	}

	// Test JPQL Join Query
	// http://localhost:8080/employees/department/IT
	@GetMapping("/department/{deptName}")
	public List<Employee> getEmployeesByDepartment(@PathVariable String deptName) {
		return service.getEmployeesByDepartment(deptName);
	}

	// Test Named Query
	// http://localhost:8080/employees/named/HR
	@GetMapping("/named/{deptName}")
	public List<Employee> getEmployeesUsingNamedQuery(@PathVariable String deptName) {
		return service.getEmployeesUsingNamedQuery(deptName);
	}
}