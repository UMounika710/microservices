package com.zettamine.cards.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.cards.constants.CardsConstants;
import com.zettamine.cards.dto.CardsContactInfoDto;
import com.zettamine.cards.dto.CardsDto;
import com.zettamine.cards.dto.ResponseDto;
import com.zettamine.cards.services.ICardsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
//@AllArgsConstructor
@NoArgsConstructor
public class CardsController {
	private static final Logger logger = LoggerFactory.getLogger(CardsController.class);
	private ICardsService cardService;
	
	@Autowired
	public CardsController(ICardsService cardService) {
		super();
		this.cardService = cardService;
	}

	
	@PostMapping(path = "/create")
	public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam String mobileNumber, @RequestBody CardsDto cardsDto){
		
		cardService.createCard(cardsDto, mobileNumber);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}
	
	@GetMapping(path = "/fetch")
	public ResponseEntity<CardsDto> fetchCard(@RequestHeader("zettabank-correlation-id")
    String correlationId, @RequestParam String mobileNumber){
		logger.debug("zettaBank-correlation-id found: {} ", correlationId);
		CardsDto  cardsDto = cardService.getByMobileNumber(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(cardsDto);
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto){
		boolean isUpdated = cardService.updateCard(cardsDto);
		 if(isUpdated) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
	        }
		
	}
	
	  @DeleteMapping("/delete/{mobileNumber}")
	    public ResponseEntity<ResponseDto> deleteCard(@Valid @PathVariable
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
	        boolean isDeleted = cardService.deleteCard(mobileNumber);
	        if(isDeleted) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
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
		private CardsContactInfoDto cardsContactInfoDto;

		@GetMapping("/contact-info")
		public ResponseEntity<CardsContactInfoDto> getContactInfo() {
			return ResponseEntity.status(HttpStatus.OK)
					.body(cardsContactInfoDto);
		}

		

}