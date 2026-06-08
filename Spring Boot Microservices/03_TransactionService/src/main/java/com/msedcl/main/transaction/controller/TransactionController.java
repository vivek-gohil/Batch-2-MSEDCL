package com.msedcl.main.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.transaction.common.ApiResponse;
import com.msedcl.main.transaction.dto.TransactionContactDTO;
import com.msedcl.main.transaction.dto.TransactionRequestDTO;
import com.msedcl.main.transaction.dto.TransactionResponseDTO;
import com.msedcl.main.transaction.service.TransactionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("transactionsapi")
public class TransactionController {

	private final TransactionService transactionService;
	private TransactionContactDTO transactionContactDTO;
	@Value("${build.version}")
	private String buildVersion;

	public TransactionController(TransactionService transactionService, TransactionContactDTO transactionContactDTO) {
		super();
		this.transactionService = transactionService;
		this.transactionContactDTO = transactionContactDTO;
	}

	@GetMapping("contact-details")
	public ResponseEntity<TransactionContactDTO> printCustomerContactDetails() {
		return ResponseEntity.status(HttpStatus.OK).body(transactionContactDTO);
	}

	@GetMapping("build-version")
	public ResponseEntity<String> printBuildVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}

	/**
	 * Deposit Money
	 */
	@PostMapping("transactions/deposit")
	public ResponseEntity<ApiResponse<TransactionResponseDTO>> depositMoney(
			@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {

		log.info("Request Received To Deposit Money");
		log.info(transactionRequestDTO.toString());

		TransactionResponseDTO transactionResponseDTO = transactionService.deposit(transactionRequestDTO);
		log.info("Transaction Completed Successfully!!");
		log.info(transactionResponseDTO.toString());

		ApiResponse<TransactionResponseDTO> response = ApiResponse.<TransactionResponseDTO>builder().status("SUCCESS")
				.message("Money deposited successfully").data(transactionResponseDTO).build();

		return ResponseEntity.ok(response);
	}

	/**
	 * Withdraw Money
	 */
	@PostMapping("transactions/withdraw")
	public ResponseEntity<ApiResponse<TransactionResponseDTO>> withdrawMoney(
			@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) {

		log.info("Request Received To Deposit Money");
		log.info(transactionRequestDTO.toString());

		TransactionResponseDTO transactionResponseDTO = transactionService.withdraw(transactionRequestDTO);
		log.info("Transaction Completed Successfully!!");
		log.info(transactionResponseDTO.toString());

		ApiResponse<TransactionResponseDTO> response = ApiResponse.<TransactionResponseDTO>builder().status("SUCCESS")
				.message("Money Deposited successfully").data(transactionResponseDTO).build();

		return ResponseEntity.ok(response);
	}

	/**
	 * View Transaction History by Account
	 */
	@GetMapping("transactions/account/{accountId}")
	public ResponseEntity<ApiResponse<List<TransactionResponseDTO>>> getTransactionHistory(
			@PathVariable Integer accountId) {
		log.info("Request Received To Fetch Transactions For AccountId :: " + accountId);

		List<TransactionResponseDTO> transactions = transactionService.getTransactionsByAccount(accountId);
		log.info("All Transactions Retrived Successfully");

		ApiResponse<List<TransactionResponseDTO>> response = ApiResponse.<List<TransactionResponseDTO>>builder()
				.status("SUCCESS").message("Transaction history retrieved successfully").data(transactions).build();

		return ResponseEntity.ok(response);
	}
}
