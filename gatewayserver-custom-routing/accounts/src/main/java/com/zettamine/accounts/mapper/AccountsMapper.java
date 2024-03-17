package com.zettamine.accounts.mapper;

import com.zettamine.accounts.dto.AccountsDto;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.entity.Accounts;

public class AccountsMapper {
	
	public static AccountsDto mapToAccountsDto(Accounts account, AccountsDto accountsDto) {
		//AccountsDto accountsDto = new AccountsDto();
		accountsDto.setAccountNumber(account.getAccountNumber());
		accountsDto.setAccountType(account.getAccountType());
		accountsDto.setBranchAddress(account.getBranchAddress());

		return accountsDto;
	}
	
	public static Accounts mapToAccounts(CustomerDto customerDto, Accounts account) {
		account.setAccountNumber(customerDto.getAccount().getAccountNumber());
		account.setAccountType(customerDto.getAccount().getAccountType());
		account.setBranchAddress(customerDto.getAccount().getBranchAddress());
		
		return account;
	}

}
