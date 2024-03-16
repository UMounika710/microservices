package com.zettamine.boot.mi.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zettamine.boot.mi.entity.InspectionLot;
import com.zettamine.boot.mi.entity.ActualsId;
import com.zettamine.boot.mi.entity.InspectionActuals;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;

public interface InspectionActualsRepository extends JpaRepository<InspectionActuals, ActualsId> {

	@Query("select act from InspectionActuals act")
	public List<InspectionActuals> getAllActuals();
	
	@Query(value = "select * from inspection_actuals where lot_id=:lotId", nativeQuery = true)
	public List<InspectionActuals> findActualsByLotId(Integer lotId);
	
	
	

	
	
	

	
}
