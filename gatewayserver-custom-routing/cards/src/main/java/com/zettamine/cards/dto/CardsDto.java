package com.zettamine.cards.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CardsDto {
	
	@Schema(description = "Mobile Number of the customer", example = "9059822696")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@NotEmpty(message = "cardNumber can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "Card Number must be 12 digits")
	//@JsonProperty(access = Access.READ_ONLY)
	private String cardNumber;
	
	//@JsonProperty(access = Access.READ_ONLY)
	@NotEmpty(message = "Card Type can not be a null or empty")
	private String cardType;
	
	@NotEmpty(message = "totalLimit can not be a null or empty")
	private int totalLimit;
	
	@NotEmpty(message = "amountUsed can not be a null or empty")
	private int amountUsed;
	
	@NotEmpty(message = "availableAmount can not be a null or empty")
	private int availableAmount;

}
