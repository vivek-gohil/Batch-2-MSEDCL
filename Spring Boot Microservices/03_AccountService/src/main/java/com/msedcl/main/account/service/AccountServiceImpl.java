package com.msedcl.main.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.msedcl.main.account.client.CustomerRESTClient;
import com.msedcl.main.account.dto.AccountRequestDTO;
import com.msedcl.main.account.dto.AccountResponseDTO;
import com.msedcl.main.account.dto.BalanceUpdateRequestDTO;
import com.msedcl.main.account.dto.CustomerResponseDTO;
import com.msedcl.main.account.entity.Account;
import com.msedcl.main.account.exception.AccountNotFoundException;
import com.msedcl.main.account.exception.InsufficientBalanceException;
import com.msedcl.main.account.mapper.AccountMapper;
import com.msedcl.main.account.repository.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final CustomerRESTClient customerRESTClient;

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {

		CustomerResponseDTO customerResponseDTO = customerRESTClient
				.getCustomerByCustomerId(accountRequestDTO.getCustomerId());
		log.info(customerResponseDTO.toString());

		Account savedAccount = accountRepository.save(AccountMapper.toEntity(accountRequestDTO));
		log.info("Account Details Saved");
		log.info(savedAccount.toString());

		return AccountMapper.toDTO(savedAccount, customerResponseDTO);
	}

	@Override
	public AccountResponseDTO getAccountById(int accountId) {

		Optional<Account> optionlAccount = accountRepository.findById(accountId);
		if (optionlAccount.isPresent()) {
			CustomerResponseDTO customerResponseDTO = customerRESTClient
					.getCustomerByCustomerId(optionlAccount.get().getCustomerId());
			log.info(customerResponseDTO.toString());
			return AccountMapper.toDTO(optionlAccount.get(), customerResponseDTO);
		} else
			throw new AccountNotFoundException("Invalid AccountId :: " + accountId);
	}

	@Override
	public List<AccountResponseDTO> getAccountsByCustomer(int customerId) {
		CustomerResponseDTO customerResponseDTO = customerRESTClient.getCustomerByCustomerId(customerId);

		List<Account> accountList = accountRepository.findByCustomerId(customerId);

		List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
		for (Account account : accountList) {
			accountResponseDTOList.add(AccountMapper.toDTO(account, customerResponseDTO));
		}
		return accountResponseDTOList;
	}

	@Override
	public AccountResponseDTO updateAccountBalance(BalanceUpdateRequestDTO balanceUpdateRequestDTO) {
		log.info("updateAccountBalance started");
		log.info(balanceUpdateRequestDTO.toString());

		Optional<Account> optionalAccount = accountRepository.findById(balanceUpdateRequestDTO.getAccountId());
		if (optionalAccount.isPresent()) {
			log.info("Account Found");
			log.info(optionalAccount.get().toString());
			CustomerResponseDTO customerResponseDTO = customerRESTClient
					.getCustomerByCustomerId(optionalAccount.get().getCustomerId());
			log.info("Customer Details Retrived");
			log.info(customerResponseDTO.toString());

			if (balanceUpdateRequestDTO.getTransactionType().equalsIgnoreCase("WITHDRAW")) {
				log.info("Starting Withdraw");
				if (optionalAccount.get().getBalance() >= balanceUpdateRequestDTO.getAmount()) {
					double balance = optionalAccount.get().getBalance();
					balance -= balanceUpdateRequestDTO.getAmount();
					optionalAccount.get().setBalance(balance);
					log.info("Updating Balance - Withdraw");
					log.info(optionalAccount.get().toString());
					accountRepository.save(optionalAccount.get());
					return AccountMapper.toDTO(optionalAccount.get(), customerResponseDTO);
				} else {
					throw new InsufficientBalanceException(
							"Insufficient Account Balance :: " + optionalAccount.get().getBalance());
				}
			}
			if (balanceUpdateRequestDTO.getTransactionType().equalsIgnoreCase("DEPOSIT")) {
				log.info("Starting Deposit");
				double balance = optionalAccount.get().getBalance();
				balance += balanceUpdateRequestDTO.getAmount();
				optionalAccount.get().setBalance(balance);
				log.info("Updating Balance - Deposit");
				log.info(optionalAccount.get().toString());
				accountRepository.save(optionalAccount.get());
				return AccountMapper.toDTO(optionalAccount.get(), customerResponseDTO);
			}
		}
		log.info("Throwing Exception :: AccountNotFoundException");
		throw new AccountNotFoundException("Invalid AccountId :: " + balanceUpdateRequestDTO.getAccountId());
	}

}