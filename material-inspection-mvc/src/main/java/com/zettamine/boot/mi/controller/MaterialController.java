package com.zettamine.boot.mi.controller;

import java.util.List;
import java.util.Optional;

import javax.swing.RepaintManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.mi.entity.Material;
import com.zettamine.boot.mi.entity.Plant;
import com.zettamine.boot.mi.entity.Vendor;
import com.zettamine.boot.mi.service.MaterialService;
import com.zettamine.boot.mi.service.PlantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/material")
public class MaterialController {
	private MaterialService mtrlService;

	public MaterialController(MaterialService mtrlService) {
		super();
		this.mtrlService = mtrlService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Material>> getAllMaterialDetails() {
		List<Material> mtrlList = mtrlService.getAllMaterialDetails();
		return new ResponseEntity<List<Material>>(mtrlList, HttpStatus.OK);
	}

	/*
	 * @GetMapping(path = "/add") public String showMaterialEntryForm(Model model) {
	 * model.addAttribute("material", new Material()); return "add-material"; }
	 */

	@PostMapping(path = "/savematerial")
	public ResponseEntity<String> saveMaterial(@RequestBody Material material) {
		String respData = null;
		HttpStatus status = null;
		boolean isSaved = mtrlService.saveMaterial(material);
		if (isSaved) {
			respData = "Material Saved Successfully";
			status = HttpStatus.CREATED;
		} else {
			respData = "Failed to save material";
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<String>(respData, status);
	}

	@PutMapping(path = "/edit")
	public ResponseEntity<String> updateMaterialById(
			@Valid @RequestParam(value = "id", required = false, defaultValue = "0") String id,
			@RequestBody Material material, BindingResult validationErrors) {
		Optional<Material> mtrl = mtrlService.getMaterialById(id);
		String respData = null;
		HttpStatus status = null;
		if (mtrl.isPresent()) {
			material.setId(id);
			boolean isSaved = mtrlService.saveMaterial(material);
			if (isSaved) {
				respData = "Material updated successfully";
				status = HttpStatus.CREATED;
			} else {
				respData = "Failed to update material";
				status = HttpStatus.BAD_REQUEST;
			}
		} else {
			respData = "No matching material found";
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);
	}

	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteMaterialById(
			@RequestParam(value = "id", required = false, defaultValue = "0") String id) {
		Optional<Material> mtrl = mtrlService.getMaterialById(id);
		String respData = null;
		HttpStatus status = null;
		if (mtrl.isPresent()) {
			boolean isdeleted = mtrlService.deleteMaterial(id);
			if (isdeleted) {
				respData = "Material Deleted successfully";
				status = HttpStatus.OK;
			} else {
				respData = "Failed to delete material";
				status = HttpStatus.BAD_REQUEST;
			}

		} else {
			respData = "No matching material found";
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);
	}

	@GetMapping(path = "/search")
	public ResponseEntity<?> searchMaterialtById(@RequestParam(value = "id", required = false, defaultValue = "0") String id) {
		Optional<Material> mtrl = mtrlService.getMaterialById(id);
		String respData = null;
		HttpStatus status = null;
		if (mtrl.isPresent()) {
			Material material = mtrl.get();
			return new ResponseEntity<Material>(material, HttpStatus.OK);
		} else {
			if (id.equals(0)) {
				respData = "please provide a valid id";
				status = HttpStatus.BAD_GATEWAY;
			}
			else {
				respData = "no matching vendor found with id: " + id;
				status = HttpStatus.BAD_REQUEST;
			}
			return new ResponseEntity<String>(respData, status);
		}
	}

}
