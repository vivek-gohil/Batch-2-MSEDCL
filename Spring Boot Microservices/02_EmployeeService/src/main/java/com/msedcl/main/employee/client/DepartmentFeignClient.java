package com.msedcl.main.employee.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.msedcl.main.employee.dto.DepartmentDTO;

@FeignClient(name = "DEPARTMENT-SERVICE", path = "/departmentapi", fallback = DepartmentFallback.class)
public interface DepartmentFeignClient {
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<DepartmentDTO> getDepartmentByDepartmentId(@PathVariable int departmentId);
}
