package com.zettamine.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByMobileNumber(String mobileNumber);

}
