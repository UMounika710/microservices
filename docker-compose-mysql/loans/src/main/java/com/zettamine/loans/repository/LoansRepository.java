package com.zettamine.loans.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.loans.entity.Loans;

public interface LoansRepository extends JpaRepository<Loans, Serializable> {

	Optional<Loans> findByMobileNumber(String mobileNumber);

}
