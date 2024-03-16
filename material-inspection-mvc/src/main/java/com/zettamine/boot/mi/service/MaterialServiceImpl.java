package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zettamine.boot.mi.entity.Material;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.repository.MaterialCharacteristicsRepository;
import com.zettamine.boot.mi.repository.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService {
	private MaterialRepository mtrlRepo;
	@Autowired
	private MaterialCharacteristicsRepository matChRepo;
	
	public MaterialServiceImpl(MaterialRepository mtrlRepo) {
		super();
		this.mtrlRepo = mtrlRepo;
	}

	@Override
	public List<Material> getAllMaterialDetails() {
		List<Material> mtrlList = mtrlRepo.findAll();
		return mtrlList;
	}

	@Override
	public boolean saveMaterial(Material mtrl) {
		mtrl.setDeleted(0);
		Material savedObj = mtrlRepo.save(mtrl);
		return savedObj.getId() != null;
	}

	@Override
	public Optional<Material> getMaterialById(String id) {
		Optional<Material> mtrl = mtrlRepo.findById(id);
		return mtrl;
	}


	@Override
	public boolean deleteMaterial(String id) {
		List<MaterialCharacteristics> charactersList = mtrlRepo.findById(id).get().getChList();
		//List<MaterialCharacteristics> chList = charactersList.stream().map(ch -> ch.setDeleted(1)).collect(Collectors.toList());
		for(MaterialCharacteristics ch: charactersList) {
			//ch.setDeleted(1);
			System.out.println("----------->" + ch.getChDesc());
			//matChRepo.save(ch);
			matChRepo.deleteById(ch.getChId());
		}
		
		mtrlRepo.deleteById(id);
		return mtrlRepo.findById(id).isEmpty();
	}

}
