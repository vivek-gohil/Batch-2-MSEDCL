package com.msedcl.main.account.service;

import java.util.List;

import com.msedcl.main.account.dto.AccountRequestDTO;
import com.msedcl.main.account.dto.AccountResponseDTO;
import com.msedcl.main.account.dto.BalanceUpdateRequestDTO;

public interface AccountService {

	AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO);

	AccountResponseDTO getAccountById(int accountId);

	List<AccountResponseDTO> getAccountsByCustomer(int customerId);

	AccountResponseDTO updateAccountBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO);
}