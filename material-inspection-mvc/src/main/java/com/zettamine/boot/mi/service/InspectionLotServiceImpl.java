package com.zettamine.boot.mi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zettamine.boot.mi.Model.SearchCriteria;
import com.zettamine.boot.mi.entity.InspectionLot;
import com.zettamine.boot.mi.entity.InspectionActuals;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.repository.InspectionLotRepository;
import com.zettamine.boot.mi.repository.InspectionActualsRepository;

@Service
public class InspectionLotServiceImpl implements InspectionLotService {
	@Autowired
	private InspectionLotRepository lotRepo;
	@Autowired
	private InspectionActualsRepository inspActualsRepo;

	public InspectionLotServiceImpl(InspectionLotRepository lotRepo) {
		super();
		this.lotRepo = lotRepo;
	}
	
	@Override
	public List<InspectionLot> getAllLotDetails() {
		List<InspectionLot> lot = lotRepo.findAll();
		return lot;
	}

	@Override
	public boolean saveInspectionLot(InspectionLot lot) {
		InspectionLot savedObj = lotRepo.save(lot);
		return savedObj.getLotId() != null;
	}
	
	@Override
	public Optional<InspectionLot> getLotById(Integer id) {
		Optional<InspectionLot> lot = lotRepo.findById(id);
		return lot;
	}


	@Override
	public List<InspectionLot> getLotsBySearchCriteria(SearchCriteria searchCriteria) {
		
		List<InspectionLot> searchResult = lotRepo.findAllByCreatedOnBetween(searchCriteria.getFromDate(), searchCriteria.getToDate());
		if(!searchCriteria.getPlantId().isEmpty()) {
			searchResult = searchResult.stream().filter(lot -> lot.getPlant().getPlantId().equals(searchCriteria.getPlantId())).collect(Collectors.toList());
		}
		if(!searchCriteria.getMaterialId().isEmpty()) {
			searchResult = searchResult.stream().filter(lot -> lot.getMaterial().getId().equals(searchCriteria.getMaterialId())).collect(Collectors.toList());
		}
		if(!searchCriteria.getStatus().isEmpty()) {
			searchResult = searchResult.stream().filter(lot -> lot.getResult().equals(searchCriteria.getStatus())).collect(Collectors.toList());
		}
		
		return searchResult;
	}
	
	@Override
	public boolean updateInspectionStatus(InspectionLot matInspLot) {


		InspectionLot materialInspLot = lotRepo.findById(matInspLot.getLotId()).get();
		
		List<InspectionActuals> matActuals = materialInspLot.getActuals();
		System.out.println(matInspLot.getMaterial());
		
		List<MaterialCharacteristics> matChars = matInspLot.getMaterial().getChList();
		
		for(InspectionActuals matActual :matActuals) {
			Integer matActualCharId = matActual.getCharacter().getChId();
			for(MaterialCharacteristics matChar:matChars) {
				Integer matCharId = matChar.getChId();
				if(matCharId.equals(matActualCharId)) {
					double minMes = matActual.getMinMes();
					double maxMes = matActual.getMaxMes();
					double toleranceUl = matChar.getTolUl();
					double toleranceLl = matChar.getTolLl();

					if(!(maxMes<=toleranceUl && minMes>=toleranceLl)) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean deleteInspectionLot(Integer lotId) {
		lotRepo.deleteById(lotId);
		return lotRepo.findById(lotId).isEmpty();
	}

	


}
