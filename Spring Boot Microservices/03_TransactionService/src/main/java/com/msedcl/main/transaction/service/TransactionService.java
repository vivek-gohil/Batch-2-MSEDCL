package com.msedcl.main.transaction.service;

import java.util.List;

import com.msedcl.main.transaction.dto.TransactionRequestDTO;
import com.msedcl.main.transaction.dto.TransactionResponseDTO;

public interface TransactionService {

	TransactionResponseDTO deposit(TransactionRequestDTO transactionRequestDTO);

	TransactionResponseDTO withdraw(TransactionRequestDTO transactionRequestDTO);

	List<TransactionResponseDTO> getTransactionsByAccount(int accountId);

}
