package com.msedcl.main.account.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.account.common.ApiResponse;
import com.msedcl.main.account.dto.AccountRequestDTO;
import com.msedcl.main.account.dto.AccountResponseDTO;
import com.msedcl.main.account.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.account.service.AccountService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/accountsapi")
public class AccountController {

	private AccountService accountService;

	/**
	 * Create Account
	 */
	@PostMapping("accounts/account")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> createAccount(
			@Valid @RequestBody AccountRequestDTO accountRequestDTO) {

		AccountResponseDTO accountResponseDTO = accountService.createAccount(accountRequestDTO);

		ApiResponse<AccountResponseDTO> apiResponse = new ApiResponse<AccountResponseDTO>("CREATED",
				"New Account Created Successfully", accountResponseDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	/**
	 * Get Account by Account ID
	 */
	@GetMapping("accounts/account/{accountId}")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountById(@PathVariable int accountId) {
		return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<AccountResponseDTO>("FOUND",
				"Account Details Retrived Successfully!", accountService.getAccountById(accountId)));
	}

	/**
	 * Get Accounts by Customer ID
	 */
	@GetMapping("accounts/customer/{customerId}")
	public ResponseEntity<ApiResponse<List<AccountResponseDTO>>> getAccountsByCustomer(
			@PathVariable Integer customerId) {
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ApiResponse<List<AccountResponseDTO>>("FOUND",
						"List of Accounts By CustomerId Retrived Successfully",
						accountService.getAccountsByCustomer(customerId)));
	}

	@PutMapping("accounts/account/balance")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> updateAccountBalance(
			@Valid @RequestBody BalanceUpdateRequestDTO balanceUpdateRequestDTO) {
		log.info("Request Received To Update Balance");
		log.info(balanceUpdateRequestDTO.toString());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<AccountResponseDTO>("UPDATED", "Account Balance Is Updated Successfully!",
						accountService.updateAccountBalance(balanceUpdateRequestDTO)));
	}

}