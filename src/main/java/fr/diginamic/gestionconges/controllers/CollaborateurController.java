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

import fr.diginamic.gestionconges.dto.CollaborateurDto;
import fr.diginamic.gestionconges.entities.Collaborateur;
import fr.diginamic.gestionconges.services.CollaborateurService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/collaborateur")
public class CollaborateurController {
	
	@Autowired
	CollaborateurService collaborateurService;

	@PostMapping
	public Collaborateur createCollaborateur(@RequestBody CollaborateurDto collaborateurDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
            throw new Exception(result.toString());
        }
		Collaborateur collaborateur = collaborateurService.toEntity(collaborateurDto);
		return collaborateurService.createCollaborateur(collaborateur);
	}
	
	@GetMapping("/all")
	public List<CollaborateurDto> readAllCollaborateur() {
		return collaborateurService.readAllCollaborateur();
	}
	
	@GetMapping("/{id}")
	public CollaborateurDto readSingleCollaborateur(@PathVariable int id) throws Exception {
		return collaborateurService.readSingleCollaborateur(id);
	}
	
	@PutMapping("/{id}")
	public Collaborateur updateCollaborateur(@PathVariable int id, @RequestBody CollaborateurDto collaborateurDto, BindingResult result) throws Exception {
		if(result.hasErrors()) {
            throw new Exception(result.toString());
        }
		Collaborateur collaborateur = collaborateurService.toEntity(collaborateurDto);
		return collaborateurService.updateCollaborateur(id, collaborateur);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCollaborateur(@PathVariable int id) throws Exception {
		return collaborateurService.deleteCollaborateur(id);
	}

}
