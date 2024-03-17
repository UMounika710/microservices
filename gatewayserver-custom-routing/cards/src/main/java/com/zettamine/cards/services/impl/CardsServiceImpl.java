package com.zettamine.cards.services.impl;

import org.springframework.stereotype.Service;

import com.zettamine.cards.dto.CardsDto;
import com.zettamine.cards.entity.Cards;
import com.zettamine.cards.exception.CardAlreadyExistsException;
import com.zettamine.cards.exception.ResourceNotFoundException;
import com.zettamine.cards.mapper.CardsMapper;
import com.zettamine.cards.repository.CardsRepository;
import com.zettamine.cards.services.ICardsService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
	private CardsRepository cardRepo;

	@Override
	public void createCard(@Valid CardsDto cardDto, String mobileNumber) {
		Cards card = CardsMapper.mapToLoans(cardDto, new Cards());
		card.setCreatedBy("Anonymous User");
		if (cardRepo.findByMobileNumber(mobileNumber).isPresent()) {
			throw new CardAlreadyExistsException(
					"Card already available with the mobile number " + cardDto.getMobileNumber());
		}

		Cards savedCard = cardRepo.save(card);

	}

	@Override
	public CardsDto getByMobileNumber(String mobileNumber) {
		Cards optCard = cardRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", mobileNumber));

		CardsDto card = CardsMapper.mapToLoansDto(optCard, new CardsDto());

		return card;
	}

	@Override
	public boolean updateCard(CardsDto cardsDto) {
		boolean isSaved = false;
		String mobileNumber = cardsDto.getMobileNumber();
		Cards card = cardRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobile Number", mobileNumber));

		card = CardsMapper.mapToLoans(cardsDto, card);
		cardRepo.save(card);
		isSaved = true;

		return isSaved;
	}

	@Override
	@Transactional
	public boolean deleteCard(String mobileNumber) {
		Cards card = cardRepo.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobile Number", mobileNumber));

		cardRepo.delete(card);
		return true;
	}

}
