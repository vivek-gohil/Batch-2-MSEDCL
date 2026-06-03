package com.msedcl.main.transaction.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msedcl.main.transaction.client.AccountServiceClient;
import com.msedcl.main.transaction.dto.AccountResponseDTO;
import com.msedcl.main.transaction.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.transaction.dto.TransactionRequestDTO;
import com.msedcl.main.transaction.dto.TransactionResponseDTO;
import com.msedcl.main.transaction.entity.Transaction;
import com.msedcl.main.transaction.mapper.TransactionMapper;
import com.msedcl.main.transaction.repository.TransactionRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final AccountServiceClient accountServiceClient;

	@Override
	public TransactionResponseDTO deposit(TransactionRequestDTO transactionRequestDTO) {

		AccountResponseDTO accountResponseDTO = accountServiceClient.getAccount(transactionRequestDTO.getAccountId());
		log.info("Account Details Fetched/Validated Successfully");
		log.info(accountResponseDTO.toString());

		BalanceUpdateRequestDTO balanceUpdateRequestDTO = new BalanceUpdateRequestDTO();
		balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
		balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getAmount());
		balanceUpdateRequestDTO.setTransactionType("DEPOSIT");

		accountServiceClient.updateBalance(balanceUpdateRequestDTO);

		log.info("Balance updated for :: accountId" + balanceUpdateRequestDTO.getAccountId());

		log.info("Creating New Transaction");
		Transaction transaction = TransactionMapper.toEntity(transactionRequestDTO, "DEPOSIT");
		transaction.setTransactionDate(LocalDate.now());
		Transaction saved = transactionRepository.save(transaction);
		log.info("Transaction details saved into table with transactionId :: " + saved.getTransactionId());

		TransactionResponseDTO transactionResponseDTO = TransactionMapper.toDTO(saved);

		// Adding AccountResponseDTO object into TransactionResponseDTO object
		transactionResponseDTO.setAccount(accountResponseDTO);

		return transactionResponseDTO;
	}

	@Override
	public TransactionResponseDTO withdraw(TransactionRequestDTO transactionRequestDTO) {

		AccountResponseDTO accountResponseDTO = accountServiceClient.getAccount(transactionRequestDTO.getAccountId());
		log.info("Account Details Fetched/Validated Successfully");
		log.info(accountResponseDTO.toString());

		BalanceUpdateRequestDTO balanceUpdateRequestDTO = new BalanceUpdateRequestDTO();
		balanceUpdateRequestDTO.setAccountId(transactionRequestDTO.getAccountId());
		balanceUpdateRequestDTO.setAmount(transactionRequestDTO.getAmount());
		balanceUpdateRequestDTO.setTransactionType("WITHDRAW");

		accountServiceClient.updateBalance(balanceUpdateRequestDTO);

		log.info("Balance updated for :: accountId" + balanceUpdateRequestDTO.getAccountId());

		log.info("Creating New Transaction");
		Transaction transaction = TransactionMapper.toEntity(transactionRequestDTO, "DEPOSIT");
		transaction.setTransactionDate(LocalDate.now());
		Transaction saved = transactionRepository.save(transaction);
		log.info("Transaction details saved into table with transactionId :: " + saved.getTransactionId());

		TransactionResponseDTO transactionResponseDTO = TransactionMapper.toDTO(saved);

		// Adding AccountResponseDTO object into TransactionResponseDTO object
		transactionResponseDTO.setAccount(accountResponseDTO);

		return transactionResponseDTO;
	}

	@Override
	public List<TransactionResponseDTO> getTransactionsByAccount(int accountId) {
		// return
		// repository.findByAccountId(accountId).stream().map(TransactionMapper::toDTO).toList();
		List<Transaction> transactionList = transactionRepository.findByAccountId(accountId);

		List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();

		for (Transaction transaction : transactionList) {
			TransactionResponseDTO transactionResponseDTO = TransactionMapper.toDTO(transaction);
			// Adding AccountResponseDTO object into TransactionResponseDTO object
			transactionResponseDTO.setAccount(accountServiceClient.getAccount(accountId));
			transactionResponseDTOList.add(transactionResponseDTO);
		}

		return transactionResponseDTOList;

	}
}
