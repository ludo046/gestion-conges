package fr.diginamic.gestionconges.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.diginamic.gestionconges.entities.Collaborateur;
import fr.diginamic.gestionconges.repositories.CollaborateurRepository;
import fr.diginamic.gestionconges.security.Role;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private CollaborateurRepository collaborateurRepository;

	public UserDetails loadUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Objects.requireNonNull(id);
		Collaborateur user = collaborateurRepository.findById(id).orElseThrow(() -> new Exception("user not found"));
		return (UserDetails) user;
	}

	@Override
	public UserDetails loadUserByUsername(String identifiant) throws UsernameNotFoundException {
		
		Collaborateur collaborateur = collaborateurRepository.findByIdentifiant(identifiant);
		return new User(collaborateur.getIdentifiant(), collaborateur.getMotDePasse(), Arrays.asList(new Role(collaborateur.getRole())));
	}

}
