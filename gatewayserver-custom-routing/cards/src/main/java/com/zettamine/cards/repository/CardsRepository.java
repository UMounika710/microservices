package com.zettamine.cards.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.cards.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Serializable> {

	Optional<Cards> findByMobileNumber(String mobileNumber);

}
