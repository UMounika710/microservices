 package com.zettamine.loans.mapper;

import com.zettamine.loans.dto.LoansDto;
import com.zettamine.loans.entity.Loans;

public class LoansMapper {
	public static LoansDto mapToLoansDto(Loans loan, LoansDto loansDto) {
		loansDto.setMobileNumber(loan.getMobileNumber());
		loansDto.setLoanNumber(loan.getLoanNumber());
		loansDto.setLoanType(loan.getLoanType());
		loansDto.setTotalLoan(loan.getTotalLoan());
		loansDto.setAmountPaid(loan.getAmountPaid());
		loansDto.setOutstandingAmount(loan.getOutstandingAmount());
		

		return loansDto;
	}
	
	public static Loans mapToLoans(LoansDto loansDto, Loans loan) { 
		loan.setMobileNumber(loansDto.getMobileNumber());
		loan.setLoanNumber(loansDto.getLoanNumber());
		loan.setLoanType(loansDto.getLoanType());
		loan.setTotalLoan(loansDto.getTotalLoan());
		loan.setAmountPaid(loansDto.getAmountPaid());
		loan.setOutstandingAmount(loansDto.getOutstandingAmount());
		
		return loan;
	}
}
