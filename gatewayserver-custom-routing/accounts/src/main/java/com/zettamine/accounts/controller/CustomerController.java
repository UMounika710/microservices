package com.zettamine.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.accounts.dto.CustomerDetailsDto;
import com.zettamine.accounts.services.ICustomerService;

import jakarta.validation.constraints.Pattern;
@RestController
@Validated
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	private final ICustomerService iCustomersService;

    public CustomerController(ICustomerService iCustomersService){
        this.iCustomersService = iCustomersService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("zettabank-correlation-id") String correlationId,@RequestParam
                                                                   @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits") String mobileNumber){
    	logger.debug("zettaBank-correlation-id found: {} ", correlationId);
    	CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber, correlationId);
      return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }

}
