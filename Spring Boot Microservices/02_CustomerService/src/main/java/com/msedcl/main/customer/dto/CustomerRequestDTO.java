package com.msedcl.main.customer.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
	@NotBlank(message = "Name cannot be blank")
	@Length(min = 2, max = 50)
	private String name;
	
	@Email(message="Invalid EmailId")
	@NotBlank(message ="Email cannot be blank")
	private String email;
	
	@NotBlank(message="Mobile number cannot be blank")
	@Length(min = 10, max = 13)
	private String mobileNumber;
}
