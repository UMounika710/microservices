package com.zettamine.boot.mi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.boot.mi.Model.LotDTO;
import com.zettamine.boot.mi.Model.SearchCriteria;
import com.zettamine.boot.mi.entity.InspectionLot;
import com.zettamine.boot.mi.entity.Material;
import com.zettamine.boot.mi.entity.InspectionActuals;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.entity.Plant;
import com.zettamine.boot.mi.entity.Vendor;
import com.zettamine.boot.mi.service.InspectionLotService;
import com.zettamine.boot.mi.service.MaterialService;
import com.zettamine.boot.mi.service.PlantService;
import com.zettamine.boot.mi.service.PlantServiceImpl;
import com.zettamine.boot.mi.service.VendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/lot")
public class InspectionLotController {
	private InspectionLotService lotService;
	@Autowired
	private PlantService plantService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private MaterialService materialService;

	@Autowired
	public InspectionLotController(InspectionLotService lotService) {
		super();
		this.lotService = lotService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<InspectionLot>> getLotDetails() {
		List<InspectionLot> lot = lotService.getAllLotDetails();
		return new ResponseEntity<List<InspectionLot>>(lot, HttpStatus.OK);
	}

	@PostMapping(path = "/savelot")
	public ResponseEntity<?> saveInspectionLot(@Valid @RequestBody LotDTO lotDto,  InspectionLot lot, BindingResult validationErrors) {
	
		String respData = null;
		HttpStatus status = null; 
		lot.setLotId(lotDto.getLotId());
		lot.setCreatedOn(lotDto.getCreatedOn());
		lot.setIspStDate(lotDto.getIspStDate());
		if(plantService.getPlantById(lotDto.getPlantId()).isPresent()) {
			lot.setPlant(plantService.getPlantById(lotDto.getPlantId()).get());
		}
		if(vendorService.getVendorById(lotDto.getVendorId()).isPresent()) {
			lot.setVendor(vendorService.getVendorById(lotDto.getVendorId()).get());
		}
		if(materialService.getMaterialById(lotDto.getMaterialId()).isPresent()) {
			lot.setMaterial(materialService.getMaterialById(lotDto.getMaterialId()).get());
		}
		
		boolean isSaved = lotService.saveInspectionLot(lot);
		if (isSaved) {
			respData = "Lot Saved Successfully";
			status = HttpStatus.CREATED;
		} else {
			respData = "Failed to save lot";
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);
	}
	
	@GetMapping(path = "/search")
	public ResponseEntity<?> getLotDetailsById(@RequestParam(name = "lotId") Integer lotId) {
		String respData = null;
		HttpStatus status = null; 
		Optional<InspectionLot> lot = lotService.getLotById(lotId);
		if(lot.isPresent()) {
			return new ResponseEntity<InspectionLot>(lot.get(), HttpStatus.OK);
		}else {
			respData =  "No lot found with lot number " + lotId;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);
	}
	
	/*
	 * @GetMapping(path = "/search") public String showSearchLotForm(Model model) {
	 * model.addAttribute("searchLot", new SearchCriteria()); return "search-lot"; }
	 */
	
	@GetMapping(path = "/searchlot/result") 
	public ResponseEntity<List<InspectionLot>> getLotSearchResult(@Valid @RequestBody SearchCriteria searchLot,BindingResult validationErrors) { 
		/*
		 * if(validationErrors.hasErrors()) { List<ObjectError> errors =
		 * validationErrors.getAllErrors(); for(ObjectError error : errors) {
		 * error.getDefaultMessage(); } return "search-lot"; }
		 */
		List<InspectionLot> searchResult = lotService.getLotsBySearchCriteria(searchLot);
	    return new ResponseEntity<List<InspectionLot>>(searchResult, HttpStatus.OK);
	  }
	
	
	@PostMapping("/update-actual-result")
	public String updateInspectionReviewResult(@ModelAttribute InspectionLot lot, Integer lotId,  Model model) {
		String result = lot.getResult();
		InspectionLot updateLot = lotService.getLotById(lotId).get();
		updateLot.setResult(result);
		System.out.println(result);
		System.out.println(lotId);
		lotService.saveInspectionLot(updateLot);
		return "show-lot";
	}
	

	@PostMapping(path = "/updatelot")
	public String updateLot(@Valid InspectionLot lot, BindingResult validationErrors, Model model) {
		if (validationErrors.hasErrors()) {
			List<ObjectError> errors = validationErrors.getAllErrors();
			for (ObjectError error : errors) {
				error.getDefaultMessage();
			}
			return "add-lot";
		}
		boolean isSaved = lotService.saveInspectionLot(lot);
		if (isSaved) {
			model.addAttribute("succMsg", "Lot Saved Successfully");
		} else {
			model.addAttribute("errMsg", "Failed to save lot");
		}
		return "redirect:/lot/all";
	}

	@GetMapping(path = "/delete")
	public String deleteInspectionLot(@RequestParam(value = "id",defaultValue = "0") Integer lotId, Model model) {
		boolean isDelete = lotService.deleteInspectionLot(lotId);
		return "redirect:/lot/search";
	}
	
	@GetMapping(path = "/edit")
	public String updateLotById(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id,
			InspectionLot lot, Model model) {
		Optional<InspectionLot> lt = lotService.getLotById(id);
		lot = lt.get();
		model.addAttribute("lot", lot);
		return "add-lot";
	}

	/*
	 * @GetMapping(path = "/ispact/add") public String
	 * showActualsEntryForm(@RequestParam(value = "lotId", required = false) Integer
	 * lotId, Model model) { System.out.println(lotId); Optional<InspectionLot> lot
	 * = lotService.getLotById(lotId); List<MaterialCharacteristics>
	 * materialCharactersList = lot.get().getMaterial().getChList();
	 * 
	 * 
	 * model.addAttribute("lot", lot); model.addAttribute("ispact", new
	 * IspActuals());
	 * model.addAttribute("materialCharactersList",materialCharactersList);
	 * 
	 * return "add-ispact"; }
	 */

	
 	/*
	@GetMapping(path = "/ispact/add")
	@ModelAttribute("materialCharactersList")
	public String showActualsEntryForm(@RequestParam(value = "lotId", required = false) Integer lotId, Model model) {
		System.out.println(lotId);
		Optional<InspectionLot> lot = lotService.getLotById(lotId);
		List<MaterialCharacteristics> materialCharactersList = lot.get().getMaterial().getChList();
		System.out.println(materialCharactersList.size());
		
		String view = "show-lot";
		return "add-ispact";
	}
	

	@GetMapping(path = "/updateresult/{lotId}/{chId}")
	public String updateInspectionResult(@PathVariable("lotId") Integer lotId, @PathVariable("chId") Integer chId,
			Model model) {
		LocalDate ispStDate = LocalDate.now();
		LocalDate ispEnDate = LocalDate.now();
		//Boolean result = lotService.updateResult(lotId);
		String remark;
		List<InspectionLot> lot = lotService.getAllLotDetails();
		model.addAttribute("lot", lot);
		return "show-lot";
	}
	
	*/

}
