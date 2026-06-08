package com.msedcl.main.customer.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.msedcl.main.customer.common.ApiResponse;
import com.msedcl.main.customer.dto.CustomerRequestDTO;
import com.msedcl.main.customer.dto.CustomerResponseDTO;
import com.msedcl.main.customer.exception.CustomerNotFoundException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerRESTClient {
	private final RestTemplate restTemplate;

	public CustomerResponseDTO addNewCustomer(CustomerRequestDTO customerRequestDTO) {

		String url = "http://localhost:8080/customerapi/customers/customer";
		// Calling CustomerService - getCustomerByCustomerId
		HttpEntity<CustomerRequestDTO> entity = new HttpEntity<CustomerRequestDTO>(customerRequestDTO);
		ResponseEntity<ApiResponse<CustomerResponseDTO>> response = restTemplate.exchange(url, HttpMethod.POST, entity,
				// Converting JSON into Object of ApiResponse<CustomerResponseDTO>
				new ParameterizedTypeReference<ApiResponse<CustomerResponseDTO>>() {
				});
		return response.getBody().getData();

	}

	public List<CustomerResponseDTO> getAllCustomers() {

		String url = "http://localhost:8080/customerapi/customers";
		// Calling CustomerService - getCustomerByCustomerId
		ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> response = restTemplate.exchange(url, HttpMethod.GET,
				null,
				// Converting JSON into Object of ApiResponse<CustomerResponseDTO>
				new ParameterizedTypeReference<ApiResponse<List<CustomerResponseDTO>>>() {
				});
		return response.getBody().getData();

	}

	public CustomerResponseDTO getCustomerByCustomerId(int customerId) {

		String url = "http://localhost:8080/customerapi/customers/" + customerId;
		try {
			// Calling CustomerService - getCustomerByCustomerId
			ResponseEntity<ApiResponse<CustomerResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null,
					// Converting JSON into Object of ApiResponse<CustomerResponseDTO>
					new ParameterizedTypeReference<ApiResponse<CustomerResponseDTO>>() {
					});

			return response.getBody().getData();
		} catch (HttpClientErrorException.NotFound e) {
			throw new CustomerNotFoundException("Invalid CustomerId :: " + customerId);
		}
	}
}
