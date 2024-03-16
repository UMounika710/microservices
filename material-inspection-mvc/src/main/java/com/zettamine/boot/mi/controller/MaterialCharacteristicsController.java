package com.zettamine.boot.mi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.mi.entity.Material;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.entity.Vendor;
import com.zettamine.boot.mi.service.MaterialService;
import com.zettamine.boot.mi.service.MaterialCharacteristicsService;
import com.zettamine.boot.mi.service.VendorService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mtch")
public class MaterialCharacteristicsController {
	private MaterialCharacteristicsService mtchService;

	@Autowired
	private MaterialService materialService;

	@Autowired
	public MaterialCharacteristicsController(MaterialCharacteristicsService mtchService) {
		super();
		this.mtchService = mtchService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<MaterialCharacteristics>> getMatChDetails(Model model) {
		List<MaterialCharacteristics> mtchList = mtchService.getMatChDetails();
		return new ResponseEntity<List<MaterialCharacteristics>>(mtchList, HttpStatus.OK);
	}
	/*
	 * @GetMapping(path = {"/add/{materialId}" }) public String
	 * showMtrlChEntryForm(@PathVariable(value = "materialId") String materialId,
	 * Model model) { MaterialCharacteristics mtch = new MaterialCharacteristics();
	 * model.addAttribute("materialId", materialId); model.addAttribute("mtch",
	 * mtch); return "add-mtch"; }
	 */

	@PostMapping(path = { "/character/savemtch", "/savemtch/{matId}" })
	public ResponseEntity<String> saveMaterialCharacter(@PathVariable(value = "matId") String matId,
			@RequestBody MaterialCharacteristics mtch) {
		String respData = null;
		HttpStatus status = null;
		Material material = materialService.getMaterialById(matId).orElse(null);
		if (material != null) {
			mtch.setMaterial(material);
			boolean isSaved = mtchService.saveMaterialCharacter(mtch);
			if (isSaved) {
				respData = "Character Saved Successfully";
				status = HttpStatus.CREATED;
			} else {
				respData = "Failed to save character";
				status = HttpStatus.BAD_REQUEST;
			}
		} else {
			respData = "No material found with matching material id " + matId;
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<String>(respData, status);
	}

	@GetMapping(path = "/material/{materialId}/characters")
	public ResponseEntity<?> getMaterialCharactersByMaterialId(@PathVariable(value = "materialId") String materialId) {
		Material material = materialService.getMaterialById(materialId).orElse(null);
		if (material != null) {
			List<MaterialCharacteristics> mtchList = materialService.getMaterialById(materialId).get().getChList();
			return new ResponseEntity<List<MaterialCharacteristics>>(mtchList, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("No matching material found", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/character/{characterId}")
	public ResponseEntity<?> searchMaterialCharacterById(@PathVariable(value = "characterId") Integer characterId) {
		Optional<MaterialCharacteristics> character = mtchService.getMatChDetailsById(characterId);
		if (character.isPresent()) {
			MaterialCharacteristics ch = character.get();
			return new ResponseEntity<MaterialCharacteristics>(ch, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("No matching character found with id " + characterId, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/character/edit")
	public ResponseEntity<?> updateCharacterById(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			@RequestBody MaterialCharacteristics mtch) {
		String respData = null;
		HttpStatus status = null;
		Optional<MaterialCharacteristics> ch = mtchService.getMatChDetailsById(id);
		if(ch.isPresent()) {
			MaterialCharacteristics character = ch.get();
			mtch.setChId(id);
			mtch.setMaterial(character.getMaterial());
			boolean isUpdated = mtchService.saveMaterialCharacter(mtch);
			if(isUpdated) {
				return new ResponseEntity<MaterialCharacteristics>(character, HttpStatus.OK);
			}else {
				respData = "Failed to update material characteristic";
				status = HttpStatus.BAD_REQUEST;
			}
		}else {
			respData = "No matching characteristic found with id " + id;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);
	}

	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteCharacterById(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
		String respData = null;
		HttpStatus status = null;
		Optional<MaterialCharacteristics> ch = mtchService.getMatChDetailsById(id);
		if(ch.isPresent()) {
			boolean isdeleted = mtchService.deleteMatCh(id);
			if (isdeleted) {
				respData = "Character deleted Successfully";
				status = HttpStatus.OK;
			} else {
				respData = "Failed to delete character";
				status=  HttpStatus.BAD_REQUEST;
			}
		}else {
			respData = "No matching characteristics found with id " + id;
			status = HttpStatus.BAD_REQUEST;
		}
	
		return new ResponseEntity<String>(respData, status);

	}

	/*
	 * @GetMapping(path = "/search") public String
	 * searchVendorById(@RequestParam(value = "id", required = false, defaultValue =
	 * "0") Integer id, Model model) { Optional<Vendor> vndr =
	 * vndService.getVendorById(id); if (vndr.isPresent()) { Vendor vendor =
	 * vndr.get(); model.addAttribute("vndr", vendor); } else { if (id.equals(0))
	 * model.addAttribute("no_match", "please provide a valid id"); else
	 * model.addAttribute("no_match", "no matching vendor found with id: " + id); }
	 * return "vendordetails";
	 * 
	 * }
	 */

}
