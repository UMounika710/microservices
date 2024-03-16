package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import com.zettamine.boot.mi.entity.ActualsId;
import com.zettamine.boot.mi.entity.InspectionLot;
import com.zettamine.boot.mi.entity.InspectionActuals;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;

public interface InspectionActualsService {
	public boolean saveActual(InspectionActuals ispAct);
	
	public List<InspectionActuals> getActualsDetails();
	
	public List<InspectionActuals> getActualsbyLotId(Integer lotId);
	
	public Optional<InspectionActuals> getActualDetailsById(ActualsId id);
	
	public boolean deleteActualsById(ActualsId id);
	
	

}
