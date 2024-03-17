package com.zettamine.loans.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loans extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loanId;
	private String mobileNumber;
	private String loanNumber;
	private String loanType;
	private int totalLoan;
	private int amountPaid;
	private int outstandingAmount;
	

}