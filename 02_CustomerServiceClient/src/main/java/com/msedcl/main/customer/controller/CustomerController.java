package com.msedcl.main.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.msedcl.main.customer.dto.CustomerRequestDTO;
import com.msedcl.main.customer.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping("savecustomer")
	public String saveCustomer(@Valid CustomerRequestDTO customerRequestDTO, 
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.info("bindingResult.hasErrors = true");
			return "customer-form";
		}
		customerService.addNewCustomer(customerRequestDTO);
		return "redirect:/";
	}

	@GetMapping("newcustomer")
	public ModelAndView showAddNewCustomerPage() {
		log.info("showAddNewCustomerPage() called");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("customer-form");
		modelAndView.addObject("customer", new CustomerRequestDTO());
		return modelAndView;
	}

	@GetMapping("/")
	public ModelAndView printAllCustomers() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("customers", customerService.getAllCustomers());
		return modelAndView;
	}
}
