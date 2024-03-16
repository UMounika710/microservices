package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.entity.Plant;

public interface MaterialCharacteristicsService {
	public List<MaterialCharacteristics> getMatChDetails();
	public Optional<MaterialCharacteristics> getMatChDetailsById(Integer id);
	boolean saveMaterialCharacter(MaterialCharacteristics mtch);

	public boolean deleteMatCh(Integer id);
	//public boolean saveMatCh(MaterialCharacteristics mtch, String materialId);

}
