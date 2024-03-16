package com.zettamine.boot.mi.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.mi.Model.SearchCriteria;
import com.zettamine.boot.mi.entity.InspectionLot;

public interface InspectionLotRepository extends JpaRepository<InspectionLot, Serializable> {
	
	List<InspectionLot> findAllByCreatedOnBetween(LocalDate fromDate, LocalDate toDate);
	
	
	  
}
