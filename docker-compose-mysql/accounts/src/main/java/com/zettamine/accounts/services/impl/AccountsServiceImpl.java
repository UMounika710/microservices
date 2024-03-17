package com.zettamine.accounts.services.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.zettamine.accounts.constants.AccountsConstants;
import com.zettamine.accounts.dto.AccountsDto;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.entity.Accounts;
import com.zettamine.accounts.entity.Customer;
import com.zettamine.accounts.exception.CustomerAlreadyExistsException;
import com.zettamine.accounts.exception.ResourceNotFoundException;
import com.zettamine.accounts.mapper.AccountsMapper;
import com.zettamine.accounts.mapper.CustomerMapper;
import com.zettamine.accounts.repository.AccountsRepository;
import com.zettamine.accounts.repository.CustomerRepository;
import com.zettamine.accounts.services.IAccountsService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
	private CustomerRepository customerRepo;
	private AccountsRepository accountsRepo;
	
	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = createCustomer(customerDto);
		Accounts account = new Accounts();
		
		account.setCustomerId(customer.getCustomerId());
		account.setAccountNumber(1000000000L + new Random().nextLong(90000000));
		account.setAccountType(AccountsConstants.SAVINGS);
		account.setBranchAddress(AccountsConstants.ADDRESS);
		account.setCreatedAt(LocalDateTime.now());
		account.setCreatedBy("Anonymous User");
		
		accountsRepo.save(account);
		
	}

	@Override
	public Customer createCustomer(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("Anonymous User");
		if(customerRepo.findByMobileNumber(customer.getMobileNumber()).isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already exists with the mobile number " + customerDto.getMobileNumber());
		}
		
		Customer savedCustomer = customerRepo.save(customer);
		return savedCustomer;
		
	}

	@Override
	public CustomerDto getByMobileNumber(String mobileNumber) {
		Customer optCustomer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
				()-> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));
		
		Accounts optAccount = accountsRepo.findByCustomerId(optCustomer.getCustomerId()).orElseThrow(
				()-> new ResourceNotFoundException("Account", "Customer ID", optCustomer.getCustomerId().toString()));
		
			CustomerDto customer = CustomerMapper.mapToCustomerDto(optCustomer, optAccount);

		return customer;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isSaved = false;
		AccountsDto accountDto = customerDto.getAccount();
		if(accountDto != null) {
			Accounts account = accountsRepo.findById(accountDto.getAccountNumber()).orElseThrow(()->
			new ResourceNotFoundException("Account", "Account Number", accountDto.getAccountNumber().toString()));
			
			account = AccountsMapper.mapToAccounts(customerDto, account);
			accountsRepo.save(account);
			
			Long customerId = account.getCustomerId();
			
			Customer customer = customerRepo.findById(customerId).orElseThrow(()->
			new ResourceNotFoundException("Customer", "Customer Id", customerId.toString()));
			
			customer = CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepo.save(customer);
			isSaved = true;
		}
		
		return isSaved;
		
	}

	@Override
	@Transactional
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());
        return true;
	}

}
