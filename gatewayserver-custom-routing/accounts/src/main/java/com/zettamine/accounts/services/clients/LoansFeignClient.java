package com.zettamine.accounts.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.zettamine.accounts.dto.LoansDto;

@FeignClient(name = "loans")
public interface LoansFeignClient {
	
	@GetMapping(value = "api/fetch")
	public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("zettabank-correlation-id")
    String correlationId,@RequestParam String mobileNumber);
	

}
