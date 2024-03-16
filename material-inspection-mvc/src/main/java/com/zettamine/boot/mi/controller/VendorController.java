package com.zettamine.boot.mi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.mi.entity.Vendor;
import com.zettamine.boot.mi.service.VendorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	
	private VendorService vndService;
	
	@Autowired
	public VendorController(VendorService vndService) {
		super();
		this.vndService = vndService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Vendor>> getAllVendorDetails() {
		List<Vendor> vndrList = vndService.getAllVendorDetails();
		
		return new ResponseEntity<List<Vendor>>(vndrList, HttpStatus.OK);
	}

	/*
	 * @GetMapping(path = "/add") public String showVendorEntryForm(Model model) {
	 * model.addAttribute("vendor", new Vendor()); return "add-vendor"; }
	 */

	@PostMapping(path = "/savevendor")
	public ResponseEntity<String> saveVendor(@Valid  @RequestBody Vendor vendor, BindingResult validationErrors) {
		/*
		 * if(validationErrors.hasErrors()) { List<ObjectError> errors =
		 * validationErrors.getAllErrors(); for(ObjectError error : errors) {
		 * error.getDefaultMessage(); } return "add-vendor"; }
		 */
		String respData = null;
		HttpStatus status = null;
		boolean isSaved = vndService.saveVendor(vendor);
		if (isSaved) {
			respData = "Vendor Saved Successfully";
			status = HttpStatus.CREATED;
		} else {
			respData = "Failed to save vendor";
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<String>(respData, status);
	}

	@PutMapping(path = "/edit")
	public ResponseEntity<String> updateVendorById(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id, @RequestBody Vendor vendor) {
		Optional<Vendor> vndr = vndService.getVendorById(id);
		String respData = null;
		HttpStatus status = null;
		if(vndr.isPresent()) {
			vendor.setVendorId(id);
			boolean isSaved = vndService.saveVendor(vendor);
			if(isSaved) {
				respData = "Vendor updated successfully";
				status = HttpStatus.OK;
			}else {
				respData = "Failed to update Vendor";
				status = HttpStatus.BAD_REQUEST;
			}
		}
		else {
			respData = "No vendor found with id " + id;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);
	}
	
	
	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteVendorById(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
		Optional<Vendor> vndr = vndService.getVendorById(id);
		String respData = null;
		HttpStatus status = null;
		if(vndr.isPresent()) {
			boolean isDeleted = vndService.deleteVendor(id);
			if(isDeleted) {
				respData = "Vendor deleted successfully";
				status = HttpStatus.OK;
			}else {
				respData = "Failed to delete Vendor";
				status = HttpStatus.BAD_REQUEST;
			}
		}
		else {
			respData = "No vendor found with id " + id;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);

	}
	

	@GetMapping(path = "/search")
	public ResponseEntity<?> searchVendorById(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
		Optional<Vendor> vndr = vndService.getVendorById(id);
		String respData = null;
		if (vndr.isPresent()) {
			Vendor vendor = vndr.get();
			return new ResponseEntity<Vendor>(vendor, HttpStatus.OK);
		} else {
			if (id.equals(0)) {
				respData = "please provide a valid id";
			}	
			else {
				respData = "no matching vendor found with id: " + id;
			}
			return new ResponseEntity<String>(respData, HttpStatus.BAD_REQUEST);	
		}
	}
	

}
