package com.zettamine.accounts.mapper;

import com.zettamine.accounts.dto.AccountsDto;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.entity.Accounts;
import com.zettamine.accounts.entity.Customer;

public class CustomerMapper {
	
	public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
		//Customer customer = new Customer();
		customer.setEmail(customerDto.getEmail());
		customer.setName(customerDto.getName());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}
	
	public static CustomerDto mapToCustomerDto(Customer customer, Accounts account) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName(customer.getName());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setEmail(customer.getEmail());
		customerDto.setAccount(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
		return customerDto;
	}
	
	
}
