package com.msedcl.main.transaction.dto;

import java.time.LocalDate;

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
public class TransactionResponseDTO {

//	private Integer transactionId;
//	private Integer accountId;
//	private String transactionType;
//	private Double amount;
//	private LocalDate transactionDate;

	private int transactionId;
	private AccountResponseDTO account;
	private String transactionType;
	private double amount;
	private LocalDate transactionDate;

}
