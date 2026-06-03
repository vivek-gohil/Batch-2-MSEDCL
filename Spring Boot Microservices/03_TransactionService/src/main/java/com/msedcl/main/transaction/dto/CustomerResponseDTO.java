package com.msedcl.main.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponseDTO {

	private Integer customerId;
	private String name;
	private String email;
	private String mobileNumber;

}