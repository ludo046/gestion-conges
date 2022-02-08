package fr.diginamic.gestionconges.controllers;

import java.time.Period;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestionconges.entities.DemandeAbsence;
import fr.diginamic.gestionconges.entities.StatutDemande;
import fr.diginamic.gestionconges.entities.TypeAbsence;
import fr.diginamic.gestionconges.repositories.DemandeAbsenceRepository;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/batch")
public class BatchController {
	@Autowired
	DemandeAbsenceRepository demandeAbsenceRepository;
	@GetMapping
	@Transactional
	@Scheduled(cron = "0 0 23 * * ?",  zone = "Europe/Paris")
	public ResponseEntity<?> traitement() {
		Iterable<DemandeAbsence> demandes = demandeAbsenceRepository.findAll();
		for(DemandeAbsence demande : demandes) {
			if(demande.getTypeAbsence().equals(TypeAbsence.RTT_SALARIE)) {
				if(StatutDemande.INITIALE.equals(demande.getStatut())) {
					double conges = demande.getCollaborateur().getRttAquis();
					Period duree = Period.between(demande.getDateDebut(), demande.getDateFin());
					if(conges > duree.getDays()) {
						demande.setStatut(StatutDemande.ATTENTE_VALIDATION);
						demande.getCollaborateur().setRttPoses(demande.getCollaborateur().getRttPoses() + duree.getDays());
					}
				}
			}else if(demande.getTypeAbsence().equals(TypeAbsence.CONGE_PAYE)){
				System.out.println("coucou");
				if(StatutDemande.INITIALE.equals(demande.getStatut())) {
					System.out.println("coucou 2");
					double conges = demande.getCollaborateur().getCongesAquis();
					Period duree = Period.between(demande.getDateDebut(), demande.getDateFin());
					if(conges > duree.getDays()) {
						System.out.println("coucou 3");
						demande.setStatut(StatutDemande.ATTENTE_VALIDATION);
						demande.getCollaborateur().setCongesPoses(demande.getCollaborateur().getCongesPoses() + duree.getDays());
						demande.getCollaborateur().setCongesAquis(demande.getCollaborateur().getCongesAquis() - duree.getDays());
					}
				}
			}else if(demande.getTypeAbsence().equals(TypeAbsence.CONGE_SANS_SOLDE)){
				if(StatutDemande.INITIALE.equals(demande.getStatut())) {
						demande.setStatut(StatutDemande.ATTENTE_VALIDATION);
					}
				
			}
		}
		return null;
	}
}
