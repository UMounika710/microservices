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

import com.zettamine.boot.mi.entity.ActualsId;
import com.zettamine.boot.mi.entity.InspectionLot;
import com.zettamine.boot.mi.entity.InspectionActuals;
import com.zettamine.boot.mi.entity.MaterialCharacteristics;
import com.zettamine.boot.mi.entity.User;
import com.zettamine.boot.mi.service.InspectionLotService;
import com.zettamine.boot.mi.service.MaterialCharacteristicsService;
import com.zettamine.boot.mi.service.InspectionActualsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/ispact")
public class InspectionActualsController {
	private InspectionActualsService actualService;
	@Autowired
	private InspectionLotService lotService;
	@Autowired
	private MaterialCharacteristicsService matCharService;

	@Autowired
	public InspectionActualsController(InspectionActualsService actualService) {
		super();
		this.actualService = actualService;
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<InspectionActuals>> getIspActDetails() {
		List<InspectionActuals> actuals = actualService.getActualsDetails();
		return new ResponseEntity<List<InspectionActuals>>(actuals, HttpStatus.OK);
	}

	@GetMapping(path = "/add")
	public String showActualsEntryForm(@RequestParam(value = "lotId") Integer lotId, Model model) {
		System.out.println(lotId);
		Optional<InspectionLot> lotOptional = lotService.getLotById(lotId);
		InspectionLot lot = lotOptional.get();

		List<MaterialCharacteristics> materialCharactersList = lotOptional.get().getMaterial().getChList();
		List<MaterialCharacteristics> charactersList = new ArrayList<>(materialCharactersList);
		List<MaterialCharacteristics> actualsCharacterList = actualService.getActualsbyLotId(lotId).stream()
				.filter(act -> act.getDeleted() == 0).map(l -> l.getCharacter()).toList();

		materialCharactersList.removeAll(actualsCharacterList);

		InspectionActuals ispActuals = new InspectionActuals();
		model.addAttribute("ispact", ispActuals);
		model.addAttribute("lot", lot);
		model.addAttribute("materialCharactersList", materialCharactersList);
		model.addAttribute("actualsCharacterList", actualsCharacterList);
		model.addAttribute("charactersList", charactersList);
		System.out.println("---------->");
		System.out.println(materialCharactersList.size());
		System.out.println(actualsCharacterList.size());
		System.out.println(charactersList.size());
		actualsCharacterList.stream().forEach(ch -> System.out.println(ch));

		return "add-ispact";
	}

	@PostMapping(path = "/saveispact/{InspectionLotId}/{characterId}")
	public ResponseEntity<String> saveInspectionActual(@Valid @RequestBody InspectionActuals ispact,
			@PathVariable Integer InspectionLotId, @PathVariable Integer characterId, BindingResult validationErrors,
			HttpServletRequest request) {

		String respData = null;
		HttpStatus status = null;
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		Optional<InspectionLot> lotOptional = lotService.getLotById(InspectionLotId);

		if (lotOptional.isPresent()) {
			List<MaterialCharacteristics> materialCharactersList = lotOptional.get().getMaterial().getChList(); // 3
			System.out.println("-------------------->before remove " + materialCharactersList.size());

			List<MaterialCharacteristics> charactersList = new ArrayList<>(materialCharactersList);
			System.out.println("---------------->charlist" + charactersList.size());

			List<MaterialCharacteristics> actualsCharacterList = actualService.getActualsbyLotId(InspectionLotId)
					.stream().filter(act -> act.getDeleted() == 0).map(l -> l.getCharacter()).toList();

			System.out.println("------------------->actualList " + actualsCharacterList.size());

			materialCharactersList.removeAll(actualsCharacterList); //

			System.out.println("-------------------->after remove " + materialCharactersList.size());

			if (matCharService.getMatChDetailsById(characterId).isPresent()) {
				MaterialCharacteristics materialCharacter = matCharService.getMatChDetailsById(characterId).get();
				for (MaterialCharacteristics ch : materialCharactersList) {
					if (ch.getChId().equals(materialCharacter.getChId())) {
						ispact.setLot(lotOptional.get());
						ispact.setCharacter(materialCharacter);

						boolean isSaved = actualService.saveActual(ispact);
						Integer lotIds = ispact.getLot().getLotId();
						Optional<InspectionLot> lot = lotService.getLotById(lotIds);
						String lotId = null;
						if (isSaved) {
							System.out.println(
									"-------------> matchar " + ispact.getLot().getMaterial().getChList().size());
							System.out.println("--------------------->actualcharlist " + actualsCharacterList.size());
							System.out.println("----------->actuallist " + lot.get().getActuals().size());

							if (charactersList.size() == lot.get().getActuals().size()) {

								String result = lotService.updateInspectionStatus(lot.get()) ? "PASS" : "ON HOLD";

								lot.get().setIspEnDate(LocalDate.now());
								lot.get().setResult(result);
								if (result.equals("ON HOLD")) {
									lot.get().setRemark("review");
								} else {
									lot.get().setRemark("System generated result");
								}

								lot.get().setUser(user);
								System.out.println("--------->" + result);
							} else {
								// lot.get().setUser(user);
								lot.get().setResult("under process");
							}

							lotService.saveInspectionLot(lot.get());
							lotId = ispact.getLot().getLotId().toString();
							respData = "Actuals Saved Successfully";
							status = HttpStatus.CREATED;
						} else {
							respData = "Failed to save actuals";
							status = HttpStatus.BAD_REQUEST;
						}

					}
				}
			}
		}
		return new ResponseEntity<String>(respData, HttpStatus.OK);

	}

	@GetMapping(path = "/view")
	public ResponseEntity<?> viewActualsByLotId(@RequestParam Integer lotId) {
		Optional<InspectionLot> lot = lotService.getLotById(lotId);
		if (lot.isPresent()) {
			List<InspectionActuals> actuals = lotService.getLotById(lotId).get().getActuals();
			return new ResponseEntity<List<InspectionActuals>>(actuals, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("No matching lot found with id " + lotId, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/review")
	public String reviewActuals(Integer lotId, Model model) {
		List<InspectionActuals> actuals = lotService.getLotById(lotId).get().getActuals();
		InspectionLot lot = lotService.getLotById(lotId).get();
		model.addAttribute("actuals", actuals);
		model.addAttribute("lot", lot);
		model.addAttribute("lotId", lotId);
		return "show-review-actuals";

	}

	@PutMapping(path = "/edit/{lotId}/{chId}")
	public ResponseEntity<String> updateActualsById(@PathVariable(value = "lotId") Integer lotId,
			@PathVariable(value = "chId") Integer chId, @RequestBody InspectionActuals ispact) {

		String respData = null;
		HttpStatus status = null;
		ActualsId pk = new ActualsId();
		pk.setCharacter(matCharService.getMatChDetailsById(chId).get());
		pk.setLot(lotService.getLotById(lotId).get());
		Optional<InspectionActuals> actual = actualService.getActualDetailsById(pk);
		InspectionLot lot = lotService.getLotById(lotId).get();
		MaterialCharacteristics matCh = matCharService.getMatChDetailsById(chId).get();
		// ispact = actual.get();

		// System.out.println(ispact.getCharacter().getChId());
		ispact.setCharacter(matCharService.getMatChDetailsById(chId).get());
		ispact.setLot(lotService.getLotById(lotId).get());
		// List<InspectionActuals> act = actualService.getByLotAndCharacter(lotId,chId);
		boolean issaved = actualService.saveActual(ispact);
		if (issaved) {
			respData = "Actuals updated successfully";
			status = HttpStatus.OK;
		} else {
			respData = "Failed to update actuals";
			status = HttpStatus.BAD_REQUEST;
		}

		System.out.println("------------>" + ispact.getCharacter().getChDesc());
		return new ResponseEntity<String>(respData, status);
	}

	/*
	 * @PutMapping(path = "/update/actuals") public String
	 * updateActuals(@ModelAttribute(name = "ispact") InspectionActuals actuals) {
	 * System.out.println("---------------------------------->" +
	 * actuals.getMinMes() + " " + actuals.getMaxMes() + " " +
	 * actuals.getLot().getLotId() + " " + actuals.getCharacter().getChDesc());
	 * 
	 * actualService.saveActual(actuals); return "show-ispact";
	 * 
	 * }
	 */

	@DeleteMapping(path = "/delete/{lotId}/{chId}")
	public ResponseEntity<String> deleteActualsById(@PathVariable(value = "lotId") Integer lotId,
			@PathVariable(value = "chId") Integer chId) {


		String respData = null;
		HttpStatus status = null;

		ActualsId id = new ActualsId();

		if (lotService.getLotById(lotId).isPresent() && (matCharService.getMatChDetailsById(chId).isPresent())) {
			System.out.println("entered inside");
			if (lotService.getLotById(lotId).get().getMaterial().getChList()
					.contains(matCharService.getMatChDetailsById(chId).get())) {
				System.out.println("entered inside");
				id.setLot(lotService.getLotById(lotId).get());
				id.setCharacter(matCharService.getMatChDetailsById(chId).get());
				boolean isDeleted = actualService.deleteActualsById(id);
				if (isDeleted) {
					respData = "Actual deleted successfully";
					status = HttpStatus.OK;
				} else {
					respData = "Failed to delete actual";
					status = HttpStatus.BAD_REQUEST;
				}

			} else {
				respData = "Invalid lot Id or Character Id";
				status = HttpStatus.BAD_REQUEST;
			}

			//return new ResponseEntity<String>(respData, status);
		}
		else {
			respData = "Invalid lot Id or Character Id";
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(respData, status);
	}
	

}
