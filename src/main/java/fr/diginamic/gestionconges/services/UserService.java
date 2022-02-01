package fr.diginamic.gestionconges.services;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.diginamic.gestionconges.entities.Collaborateur;
import fr.diginamic.gestionconges.repositories.CollaborateurRepository;

@Service
public class UserService implements UserDetailsService{
	
	private CollaborateurRepository collaborateurRepository = null;
	
	@Autowired
	public UserService(CollaborateurRepository collaboratueurRepository, CollaborateurRepository collaborateurRepository) {
		// TODO Auto-generated constructor stub
		this.collaborateurRepository = collaborateurRepository;
	}
	
 public UserDetails loadUserById(Integer id) throws Exception {
	// TODO Auto-generated method stub
	 Objects.requireNonNull(id);
		Collaborateur user = collaborateurRepository.findById(id)
				.orElseThrow(() -> new Exception("user not found"));
		return (UserDetails) user;
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// TODO Auto-generated method stub
	return null;
}

}
