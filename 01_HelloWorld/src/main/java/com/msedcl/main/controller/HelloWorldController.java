package com.msedcl.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.msedcl.main.Application;
import com.msedcl.main.dto.EmployeeDTO;

@Controller
public class HelloWorldController {

	private final Application application;

	private List<EmployeeDTO> employeeDTOList = new ArrayList<>();

	public HelloWorldController(Application application) {
		employeeDTOList.add(new EmployeeDTO(101, "Test 1", 1000));
		employeeDTOList.add(new EmployeeDTO(102, "Test 2", 1000));
		employeeDTOList.add(new EmployeeDTO(103, "Test 3", 1000));
		this.application = application;
	}

	@PostMapping("saveemp")
	public String saveEmployee(EmployeeDTO employeeDTO) {
		System.out.println("EmployeeDTO received in controller");
		System.out.println(employeeDTO.toString());
		employeeDTOList.add(employeeDTO);
		System.out.println("EmployeeDTO added into list");
		return "redirect:/employees";
	}

	@GetMapping("addnew")
	public ModelAndView showAddNewEmployee() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("empoyee-form");

		// Creating empty object to connect form/view
		EmployeeDTO employeeDTO = new EmployeeDTO(0, "Test", 100);
		modelAndView.addObject("employee", employeeDTO);
		return modelAndView;
	}

	@GetMapping("employees")
	public ModelAndView printAllEmployees() {
		System.out.println("printAllEmployees() called");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("employee-list");
		modelAndView.addObject("employeeList", employeeDTOList);
		return modelAndView;
	}

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@GetMapping("process")
	public ModelAndView printMessage(String message) {
		System.out.println(message);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("third");
		modelAndView.addObject("msg", message);
		return modelAndView;
	}

	@GetMapping("next")
	public String showNextPage() {
		return "second";
	}

}
