package com.msedcl.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.msedcl.main.domain.Employee;

public class EmployeeRecordMain {
	public static void main(String[] args) {
		Employee employee1 = new Employee(1, "Test 1", 10000);
		Employee employee2 = new Employee(2, "Test 2", 10000);
		Employee employee3 = new Employee(3, "Test 3", 10000);
		
		var employeeList = Arrays.asList(employee1,employee2,employee3);
		
		
		
		employeeList.forEach(e -> System.out.println(e));
		
		for (Employee employee : employeeList) {
			System.out.println(employee.employeeId());
			System.out.println(employee.name());
			System.out.println(employee.salary());
		}
	}
}
