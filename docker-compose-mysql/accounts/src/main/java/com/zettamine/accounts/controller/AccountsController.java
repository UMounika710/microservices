package com.zettamine.accounts.controller;

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

import com.zettamine.accounts.constants.AccountsConstants;
import com.zettamine.accounts.dto.AccountsContactInfoDto;
import com.zettamine.accounts.dto.CustomerDto;
import com.zettamine.accounts.dto.ResponseDto;
import com.zettamine.accounts.services.IAccountsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@NoArgsConstructor
@RefreshScope   //to get updated "build.version" value
public class AccountsController {

	@Autowired
	private IAccountsService accountService;
	
	@Autowired
    public AccountsController(IAccountsService accountService) {
		super();
		this.accountService = accountService;
	}

	@PostMapping(path = "/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

		accountService.createAccount(customerDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@GetMapping(path = "/fetch")
	public ResponseEntity<CustomerDto> fetchAccount(@RequestParam String mobileNumber) {
		CustomerDto customerDto = accountService.getByMobileNumber(mobileNumber);

		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = accountService.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}

	}

	@DeleteMapping("/delete/{mobileNumber}")
	public ResponseEntity<ResponseDto> deleteAccount(
			@Valid @PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		boolean isDeleted = accountService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
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
	private AccountsContactInfoDto accountsContactInfoDto;

	@GetMapping("/contact-info")
	public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(accountsContactInfoDto);
	}

}
