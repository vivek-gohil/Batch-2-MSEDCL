package com.msedcl.main.department.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.department.dto.DepartmentDTO;
import com.msedcl.main.department.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("departmentapi")
@AllArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	@PostMapping("department")
	public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody DepartmentDTO departmentDTO) {
		if (departmentService.addNewDepartment(departmentDTO) != null) {
			return ResponseEntity.status(HttpStatus.OK).body(departmentDTO);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("department/{departmentId}")
	public ResponseEntity<DepartmentDTO> getDepartmentByDepartmentId(@PathVariable int departmentId) {
		DepartmentDTO departmentDTO = departmentService.getDepartmentByDepartmentId(departmentId);
		if (departmentDTO != null) {
			return ResponseEntity.status(HttpStatus.OK).body(departmentDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
