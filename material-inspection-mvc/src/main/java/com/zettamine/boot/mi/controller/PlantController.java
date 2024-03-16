package com.zettamine.boot.mi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.mi.entity.Plant;
import com.zettamine.boot.mi.service.PlantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/plant")
public class PlantController {
	@Autowired
	private PlantService plntService;

	public PlantController(PlantService plntService) {
		super();
		this.plntService = plntService;
	}
	
	@GetMapping(path = "/all")
	public ResponseEntity<List<Plant>> getAllPlantDetails() {
		List<Plant> plntList = plntService.getAllPlantDetails();
		return new ResponseEntity<List<Plant>>(plntList, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping(path = "/plant/add") public String showPlantEntryForm(Model
	 * model) { model.addAttribute("plant", new Plant()); return "add-plant"; }
	 */
	
	@PostMapping(path = "/saveplant")
	public ResponseEntity<String> savePlant(@Valid @RequestBody Plant plant ){
		HttpStatus status = null;
		String respData = null;
		/*
		 * if(validationError.hasErrors()) { List<ObjectError> errors =
		 * validationError.getAllErrors(); //return "add-plant"; }
		 */
		boolean isSaved = plntService.savePlant(plant);
		if (isSaved) {
			respData = "Plant Saved Successfully";
			status = HttpStatus.CREATED;
		} else {
			respData = "Failed to save plant";
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<String>(respData, status);
	}
	
	@PutMapping(path = "/edit")
	public ResponseEntity<Plant> updatePlantById(@RequestParam(value = "id", required = false, defaultValue = "0") String id, @RequestBody Plant plant) {
		Optional<Plant> plnt = plntService.getPlantById(id);
		if(plnt.isPresent()) {
			plant.setPlantId(id);
			plntService.savePlant(plant);
		}
		else {
			
		}
		
		return null;
	}
	
	@DeleteMapping(path = "/delete")
	public ResponseEntity<Object> deletePlantById(@RequestParam(value = "id", required = false, defaultValue = "0") String id) {
		String respData = null;
		HttpStatus status = null;
		boolean isdeleted = plntService.deletePlant(id);
		if (isdeleted) {
			respData = "Plant deleted Successfully";
			status = HttpStatus.OK;
		} else {
			respData = "Failed to Delete Plant";
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Object>(respData, status);

	}
	
	
	@GetMapping(path = "/search")
	public ResponseEntity<?> searchPlantById(@RequestParam(value = "id", required = false, defaultValue = "0") String id) {
		
		Optional<Plant> plnt = plntService.getPlantById(id);
		String respData = null;
		HttpStatus status = null;
		Plant plant = null;
		
		if (plnt.isPresent()) {
			plant = plnt.get();
			status = HttpStatus.OK;
		} else {
			if (id.equals(0)) {
			    respData = "no matchig plant found, please provide a valid id";
			}
			else {
				respData = "no matching plant found with id: " + id;
			}
			return new ResponseEntity<String>(respData, HttpStatus.BAD_REQUEST);	
		}
		return new ResponseEntity<Plant>(plant, status);

	}
	

}
