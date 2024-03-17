package com.zettamine.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoansDto {
	
	@Schema(description = "Mobile Number of the customer", example = "9059822696")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@NotEmpty(message = "loanNumber can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Loan Number must be 10 digits")
	private String loanNumber;
	
	@NotEmpty(message = "Loan Type can not be a null or empty")
	private String loanType;
	
	@NotEmpty(message = "Total Loan can not be a null or empty")
	private int totalLoan;
	
	@NotEmpty(message = "AmountUsed can not be a null or empty")
	private int amountPaid;
	
	@NotEmpty(message = "Outstanding Amount can not be a null or empty")
	private int outstandingAmount;

}
