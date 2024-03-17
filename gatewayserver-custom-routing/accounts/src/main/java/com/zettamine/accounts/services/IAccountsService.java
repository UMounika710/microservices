package com.zettamine.accounts.services;

import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.entity.Customer;
import com.zettamine.accounts.mapper.CustomerMapper;

import jakarta.validation.constraints.Pattern;

public interface IAccountsService {
	/**
	 * 
	 * @param customerDto - CustomerDto object
	 */
	public void createAccount(CustomerDto customerDto);
	public Customer createCustomer(CustomerDto customerDto);
	public CustomerDto getByMobileNumber(String mobileNumber);
	public boolean updateAccount(CustomerDto customerDto);
	public boolean deleteAccount(String mobileNumber);

}
