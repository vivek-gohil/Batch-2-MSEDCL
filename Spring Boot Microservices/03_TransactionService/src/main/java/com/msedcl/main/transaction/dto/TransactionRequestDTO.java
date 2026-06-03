package com.msedcl.main.transaction.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequestDTO {

	@NotNull
	private int accountId;

	@NotNull
	private double amount;
	 
}
