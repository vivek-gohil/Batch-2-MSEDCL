package com.msedcl.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msedcl.main.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Custom JPQL Query
	@Query("SELECT e FROM Employee e WHERE e.salary > :salary")
	List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);

	// Join Query
	@Query("""
			SELECT e
			FROM Employee e
			JOIN e.department d
			WHERE d.departmentName = :deptName
			""")
	List<Employee> getEmployeesByDepartment(@Param("deptName") String deptName);

	// Named Query
	List<Employee> findByDepartmentName(@Param("deptName") String deptName);
}