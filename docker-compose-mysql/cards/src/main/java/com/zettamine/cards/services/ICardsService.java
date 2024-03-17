package com.zettamine.cards.services;

import com.zettamine.cards.dto.CardsDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public interface ICardsService {

	void createCard(CardsDto cardDto, String mobileNumber);

	CardsDto getByMobileNumber(String mobileNumber);

	boolean updateCard(CardsDto cardsDto);

	boolean deleteCard(String mobileNumber);

}
