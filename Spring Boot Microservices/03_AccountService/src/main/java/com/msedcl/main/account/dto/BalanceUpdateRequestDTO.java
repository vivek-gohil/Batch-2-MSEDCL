package com.msedcl.main.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BalanceUpdateRequestDTO {

	@Positive(message = "accountId must be greater than zero")
	private int accountId;

	@Positive(message = "Amount must be greater than zero")
	private double amount;

	@NotBlank(message = "Transaction Type is required")
	private String transactionType;

}
