package fr.diginamic.gestionconges.controllers;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SecurityController {

	/**
	 * Fournit des informations sur l'utilisateur connecté
	 * 
	 * @param user utilisateur
	 * @return Principal
	 */
	@GetMapping
	public UsernamePasswordAuthenticationToken user(Principal principal) {
		
		UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;
		return user;
	}

}
