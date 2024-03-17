package com.zettamine.loans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.loans.constants.LoansConstants;
import com.zettamine.loans.dto.LoansContactInfoDto;
import com.zettamine.loans.dto.LoansDto;
import com.zettamine.loans.dto.ResponseDto;
import com.zettamine.loans.services.ILoansService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@NoArgsConstructor
@RefreshScope
public class LoansController {
	@Autowired
	private ILoansService loanService;
	
	@Autowired
	public LoansController(LoansContactInfoDto loansContactInfoDto) {
		super();
		this.loansContactInfoDto = loansContactInfoDto;
	}
	
	@PostMapping(path = "/create")
	public ResponseEntity<ResponseDto> createLoan(@Valid @RequestParam String mobileNumber, @RequestBody LoansDto loansDto){
		
		loanService.createLoan(loansDto, mobileNumber);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
	}
	
	@GetMapping(path = "/fetch")
	public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber){
		LoansDto  customerDto = loanService.getByMobileNumber(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(customerDto);
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto){
		boolean isUpdated = loanService.updateLoan(loansDto);
		 if(isUpdated) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
	        }
		
	}
	
	  @DeleteMapping("/delete/{mobileNumber}")
	    public ResponseEntity<ResponseDto> deleteLoan(@Valid @PathVariable
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
	        boolean isDeleted = loanService.deleteLoan(mobileNumber);
	        if(isDeleted) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
	        }
	    }
	  
		@Value("${build.version}")
	    private String buildVersion;

		@GetMapping("/build-info")
		public ResponseEntity<String> getBuildInfo() {
			return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
		}

		@Autowired
		private Environment environment;

		@GetMapping("/java-version")
		public ResponseEntity<String> getJavaVersion() {
			return ResponseEntity.status(HttpStatus.OK)
					.body(environment.getProperty("JAVA_HOME"));
		}

		@Autowired
		private LoansContactInfoDto loansContactInfoDto;

		@GetMapping("/contact-info")
		public ResponseEntity<LoansContactInfoDto> getContactInfo() {
			return ResponseEntity.status(HttpStatus.OK)
					.body(loansContactInfoDto);
		}

		

}
