package fr.diginamic.gestionconges.security;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.diginamic.gestionconges.entities.Collaborateur;
import fr.diginamic.gestionconges.repositories.CollaborateurRepository;

@Component
public class StartupListener {

    private PasswordEncoder passwordEncoder;
    private CollaborateurRepository collaborateurRepo;

    public StartupListener(PasswordEncoder passwordEncoder, CollaborateurRepository collaborateurRepo) {
        this.passwordEncoder = passwordEncoder;
        this.collaborateurRepo = collaborateurRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {

        // Création de deux utilisateurs

    	if (collaborateurRepo.findByIdentifiant("admin").isEmpty()) {
	        Collaborateur col1 = new Collaborateur();
	        col1.setNom("TRUMP");
	        col1.setPrenom("Donald");
	        col1.setIdentifiant("admin");
	        col1.setMotDePasse(passwordEncoder.encode("admin"));
	        col1.setRole("ADMINISTRATEUR");;
	        this.collaborateurRepo.save(col1);
    	}
    	if (collaborateurRepo.findByIdentifiant("salarie").isEmpty()) {
	        Collaborateur col2 = new Collaborateur();
	        col2.setNom("DURAND");
	        col2.setPrenom("Paul");
	        col2.setIdentifiant("salarie");
	        col2.setMotDePasse(passwordEncoder.encode("salarie"));
	        col2.setRole("SALARIE");
	        this.collaborateurRepo.save(col2);
    	}
    }

}