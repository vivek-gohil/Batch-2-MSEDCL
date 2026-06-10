package com.msedcl.main.department.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msedcl.main.department.dto.DepartmentDTO;

@Service
public class DepartmentService {
	private List<DepartmentDTO> departmentList = new ArrayList<>();

	public DepartmentDTO addNewDepartment(DepartmentDTO departmentDTO) {
		if (departmentList.add(departmentDTO))
			return departmentDTO;
		else
			return null;
	}

	public DepartmentDTO getDepartmentByDepartmentId(int departmentId) {
		for (DepartmentDTO departmentDTO : departmentList) {
			if (departmentDTO.getDepartmentId() == departmentId)
				return departmentDTO;
		}
		return null;
	}

}
