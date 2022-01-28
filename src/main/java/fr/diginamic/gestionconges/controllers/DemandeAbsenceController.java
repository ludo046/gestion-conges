package fr.diginamic.gestionconges.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestionconges.dto.DemandeAbsenceDto;
import fr.diginamic.gestionconges.entities.DemandeAbsence;
import fr.diginamic.gestionconges.services.DemandeAbsenceService;

@CrossOrigin
@RestController
@RequestMapping("/api/demande_absence")
public class DemandeAbsenceController {
	
	@Autowired
	DemandeAbsenceService demandeAbsenceService;

	@PostMapping
	public DemandeAbsence createDemandeAbsence(@RequestBody DemandeAbsenceDto demandeAbsenceDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
            throw new Exception(result.toString());
        }
		DemandeAbsence demandeAbsence = demandeAbsenceService.toEntity(demandeAbsenceDto);
		return demandeAbsenceService.createDemandeAbsence(demandeAbsence);
	}
	
	@GetMapping("/all")
	public List<DemandeAbsenceDto> readAllDemandeAbsence() {
		return demandeAbsenceService.readAllDemandeAbsence();
	}
	
	@GetMapping("/{id}")
	public DemandeAbsenceDto readSingleDemandeAbsence(@PathVariable int id) throws Exception {
		return demandeAbsenceService.readSingleDemandeAbsence(id);
	}
	
	@PutMapping("/{id}")
	public DemandeAbsence updateDemandeAbsence(@PathVariable int id, @RequestBody DemandeAbsenceDto demandeAbsenceDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
            throw new Exception(result.toString());
        }
		DemandeAbsence demandeAbsence = demandeAbsenceService.toEntity(demandeAbsenceDto);
		return demandeAbsenceService.updateDemandeAbsence(id, demandeAbsence);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDemandeAbsence(@PathVariable int id) throws Exception {
		return demandeAbsenceService.deleteDemandeAbsence(id);
	}

}
