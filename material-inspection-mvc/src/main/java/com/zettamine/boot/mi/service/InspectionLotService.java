package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import com.zettamine.boot.mi.Model.SearchCriteria;
import com.zettamine.boot.mi.entity.InspectionLot;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;

public interface InspectionLotService {
	public List<InspectionLot> getAllLotDetails();
	
	public boolean saveInspectionLot(InspectionLot lot);
	
	public Optional<InspectionLot> getLotById(Integer id);
	
	public List<InspectionLot> getLotsBySearchCriteria(SearchCriteria search);
	
	public boolean updateInspectionStatus(InspectionLot matInspLot);
	
	
	
	//public boolean updateLotBasedOnActualsResults(InspectionLot lot, String result);
	
	//public List<MaterialCharacteristics> getCharactersByMatId(Integer lotId);
	
	//public boolean updateResult(Integer lotId);
	
	public boolean deleteInspectionLot(Integer lotId);

}
