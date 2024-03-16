package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.boot.mi.entity.Plant;
import com.zettamine.boot.mi.repository.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {
	
	private PlantRepository plantRepo;
	
	public PlantServiceImpl(PlantRepository plantRepo) {
		super();
		this.plantRepo = plantRepo;
	}

	@Override
	public List<Plant> getAllPlantDetails() {
		List<Plant> plntList = plantRepo.findAll();
		return plntList;
	}

	@Override
	public boolean savePlant(Plant plnt) {
		Plant savedObj = plantRepo.save(plnt);
		return savedObj.getPlantId() != null;	
	}

	@Override
	public Optional<Plant> getPlantById(String id) {
		Optional<Plant> plnt = plantRepo.findById(id);
		return plnt;
	}
	
	/*
	@Override
	public void savePlant(List<Plant> plntList) {
		plantRepo.saveAll(plntList);

	}
	*/

	@Override
	public boolean deletePlant(String id) {
		if(plantRepo.findById(id).isPresent()){
			plantRepo.deleteById(id);
			return true;
		}
		return false;
	}
	

}
