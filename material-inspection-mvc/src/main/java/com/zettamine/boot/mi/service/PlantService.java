package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import com.zettamine.boot.mi.entity.Plant;
import com.zettamine.boot.mi.entity.Vendor;

public interface PlantService {
	public List<Plant> getAllPlantDetails();
	public boolean savePlant(Plant plnt);
	public Optional<Plant> getPlantById(String id);
	
	public boolean deletePlant(String id);
	//public void savePlant(List<Plant> plntList);

}
