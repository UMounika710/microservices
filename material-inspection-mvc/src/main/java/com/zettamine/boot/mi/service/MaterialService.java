package com.zettamine.boot.mi.service;

import java.util.List;
import java.util.Optional;

import com.zettamine.boot.mi.entity.Material;
import com.zettamine.boot.mi.entity.Plant;

public interface MaterialService {
	public List<Material> getAllMaterialDetails();
	public boolean saveMaterial(Material mtrl);
	public Optional<Material> getMaterialById(String id);
	public boolean deleteMaterial(String id);

}
