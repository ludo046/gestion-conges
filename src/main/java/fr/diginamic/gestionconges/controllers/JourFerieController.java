package fr.diginamic.gestionconges.controllers;

import java.util.Optional;

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

import fr.diginamic.gestionconges.dto.JourFerieDto;
import fr.diginamic.gestionconges.entities.DemandeAbsence;
import fr.diginamic.gestionconges.entities.JourFerie;
import fr.diginamic.gestionconges.services.JourFerieService;

@CrossOrigin
@RestController
@RequestMapping("/api/jour_ferie")
public class JourFerieController {
	
	@Autowired
	JourFerieService jourFerieService;

	@PostMapping
	public JourFerie createJourFerie(@RequestBody JourFerieDto jourFerieDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
            throw new Exception(result.toString());
        }
		JourFerie jourFerie = jourFerieService.toEntity(jourFerieDto);
		return jourFerieService.createJourFerie(jourFerie);
	}
	
	@GetMapping("/all")
	public Iterable<JourFerie> readAllJourFerie() {
		return jourFerieService.readAllJourFerie();
	}
	
	@GetMapping("/{id}")
	public Optional<JourFerie> readSingleJourFerie(@PathVariable int id) throws Exception {
		return jourFerieService.readSingleJourFerie(id);
	}
	
	@PutMapping("/{id}")
	public JourFerie updateJourFerie(@PathVariable int id, @RequestBody JourFerieDto jourFerieDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
            throw new Exception(result.toString());
        }
		JourFerie jourFerie = jourFerieService.toEntity(jourFerieDto);
		return jourFerieService.updateJourFerie(id, jourFerie);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJourFerie(@PathVariable int id) throws Exception {
		return jourFerieService.deleteJourFerie(id);
	}

}
