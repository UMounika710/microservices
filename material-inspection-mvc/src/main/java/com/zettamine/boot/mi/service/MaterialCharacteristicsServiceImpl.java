package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zettamine.boot.mi.entity.Material;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.entity.Plant;
import com.zettamine.boot.mi.repository.MaterialRepository;
import com.zettamine.boot.mi.repository.MaterialCharacteristicsRepository;

@Service
public class MaterialCharacteristicsServiceImpl implements MaterialCharacteristicsService {
	private MaterialCharacteristicsRepository matchRepo;
	private MaterialRepository mtrlRepo;

	@Autowired
	public MaterialCharacteristicsServiceImpl(MaterialCharacteristicsRepository matchRepo,MaterialRepository mtrlRepo) {
		super();
		this.matchRepo = matchRepo;
		this.mtrlRepo = mtrlRepo;
	}

	@Override
	public List<MaterialCharacteristics> getMatChDetails() {
		List<MaterialCharacteristics> mtchlList = matchRepo.findAll();
		return mtchlList;
	}


	@Override
	public boolean saveMaterialCharacter(MaterialCharacteristics mtch) {
		MaterialCharacteristics savedObj = matchRepo.save(mtch);
		return savedObj.getChId() != null;	
	}
	
	@Override
	public Optional<MaterialCharacteristics> getMatChDetailsById(Integer id) {
		Optional<MaterialCharacteristics> mtch = matchRepo.findById(id);
		return mtch;
	}
	
	

	@Override
	public boolean deleteMatCh(Integer id) {
		matchRepo.deleteById(id);
		return matchRepo.findById(id).isEmpty();
	}

	/*
	@Override
	public boolean saveMatCh(MaterialCharacteristics mtch, String materialId) {
		Material material = mtrlRepo.findById(materialId).orElse(null);
        mtch.setMaterial(material);
		MaterialCharacteristics savedObj = matchRepo.save(mtch);
		return savedObj.getChId() != null;	
	}
	*/

}
