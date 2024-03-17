package com.zettamine.loans.services;

import com.zettamine.loans.dto.LoansDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public interface ILoansService {

	void createLoan(LoansDto loanDto, String mobileNumber);

	LoansDto getByMobileNumber(String mobileNumber);

	boolean updateLoan(LoansDto loansDto);

	boolean deleteLoan(String mobileNumber);

}
