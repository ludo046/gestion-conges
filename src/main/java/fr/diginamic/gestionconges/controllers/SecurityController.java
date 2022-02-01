package fr.diginamic.gestionconges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestionconges.repositories.CollaborateurRepository;
import fr.diginamic.gestionconges.security.UserDto;

@RestController
public class SecurityController {

	@Autowired
    private CollaborateurRepository collaborateurRepo;

    @GetMapping("/user")
    public ResponseEntity<?> quiSuisJe() {
        String identifiant = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.collaborateurRepo.findByIdentifiant(identifiant)
                .map(UserDto::new)
                .map(col -> ResponseEntity.ok(col))
                .orElse(ResponseEntity.badRequest().build());
    }
}