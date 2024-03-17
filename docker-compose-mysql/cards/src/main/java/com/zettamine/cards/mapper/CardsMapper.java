 package com.zettamine.cards.mapper;

import com.zettamine.cards.dto.CardsDto;
import com.zettamine.cards.entity.Cards;

public class CardsMapper {
	public static CardsDto mapToLoansDto(Cards card, CardsDto cardsDto) {
		cardsDto.setMobileNumber(card.getMobileNumber());
		cardsDto.setCardNumber(card.getCardNumber());
		cardsDto.setCardType(card.getCardType());
		cardsDto.setTotalLimit(card.getTotalLimit());
		cardsDto.setAmountUsed(card.getAmountUsed());
		cardsDto.setAvailableAmount(card.getAvailableAmount());
		

		return cardsDto;
	}
	
	public static Cards mapToLoans(CardsDto cardsDto, Cards card) { 
		card.setMobileNumber(cardsDto.getMobileNumber());
		card.setCardNumber(cardsDto.getCardNumber());
		card.setCardType(cardsDto.getCardType());
		card.setTotalLimit(cardsDto.getTotalLimit());
		card.setAmountUsed(cardsDto.getAmountUsed());
		card.setAvailableAmount(cardsDto.getAvailableAmount());
		
		return card;
	}
}
