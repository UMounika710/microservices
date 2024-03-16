package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.boot.mi.entity.ActualsId;
import com.zettamine.boot.mi.entity.InspectionLot;
import com.zettamine.boot.mi.entity.InspectionActuals;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.repository.InspectionActualsRepository;

@Service
public class InspectionActualsServiceImpl implements InspectionActualsService {
	private InspectionActualsRepository actualsRepo;

	public InspectionActualsServiceImpl(InspectionActualsRepository ispRepo) {
		super();
		this.actualsRepo = ispRepo;
	}

	@Override
	public boolean saveActual(InspectionActuals ispAct) {
		System.out.println("------------------------------------------>" + ispAct.getCharacter().getChDesc());
		InspectionActuals savedObj = actualsRepo.save(ispAct);
		return ((savedObj.getCharacter().getChId() != null) && (savedObj.getLot().getLotId() != null));

	}

	@Override
	public List<InspectionActuals> getActualsDetails() {
		List<InspectionActuals> ispactList = actualsRepo.findAll();
		// List<InspectionActuals> ispactList = actualsRepo.getAllActuals();
		return ispactList;
	}

	@Override
	public Optional<InspectionActuals> getActualDetailsById(ActualsId id) {
		Optional<InspectionActuals> act = actualsRepo.findById(id);
		return act;
	}

	@Override
	public List<InspectionActuals> getActualsbyLotId(Integer lotId) {
		List<InspectionActuals> actualsList = actualsRepo.findActualsByLotId(lotId);
		return actualsList;
	}

	@Override
	public boolean deleteActualsById(ActualsId id) {
		actualsRepo.deleteById(id);
		return actualsRepo.findById(id).isEmpty();
		// return true ;
	}

	/*
	 * @Override public boolean deleteActualsById(ActualsId id);
	 * 
	 * {
	 */
	  //actualsRepo.deleteById(); return actualsRepo.deleteById(id).isEmpty() ; }
	 
	

}
