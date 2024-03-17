package com.zettamine.loans.services.impl;

import org.springframework.stereotype.Service;

import com.zettamine.loans.dto.LoansDto;
import com.zettamine.loans.entity.Loans;
import com.zettamine.loans.exception.LoanAlreadyExistsException;
import com.zettamine.loans.exception.ResourceNotFoundException;
import com.zettamine.loans.mapper.LoansMapper;
import com.zettamine.loans.repository.LoansRepository;
import com.zettamine.loans.services.ILoansService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
	private LoansRepository loanRepo;

	@Override
	public void createLoan(@Valid LoansDto loanDto, String mobileNumber) {
		Loans loan = LoansMapper.mapToLoans(loanDto, new Loans());
		loan.setCreatedBy("Anonymous User");
		if (loanRepo.findByMobileNumber(mobileNumber).isPresent()) {
			throw new LoanAlreadyExistsException(
					"Loan already sanctioned with the mobile number " + loanDto.getMobileNumber());
		}

		Loans savedLoan = loanRepo.save(loan);

	}

	@Override
	public LoansDto getByMobileNumber(String mobileNumber) {
		Loans optLoan = loanRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber));

		LoansDto loan = LoansMapper.mapToLoansDto(optLoan, new LoansDto());

		return loan;
	}

	@Override
	public boolean updateLoan(LoansDto loansDto) {
		boolean isSaved = false;
		String mobileNumber = loansDto.getMobileNumber();
		Loans loan = loanRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile Number", mobileNumber));

		loan = LoansMapper.mapToLoans(loansDto, loan);
		loanRepo.save(loan);
		isSaved = true;

		return isSaved;
	}

	@Override
	@Transactional
	public boolean deleteLoan(String mobileNumber) {
		Loans loan = loanRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile Number", mobileNumber));

		loanRepo.delete(loan);
		return true;
	}

}
