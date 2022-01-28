package fr.diginamic.gestionconges.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.diginamic.gestionconges.dto.DemandeAbsenceDto;
import fr.diginamic.gestionconges.entities.DemandeAbsence;
import fr.diginamic.gestionconges.entities.StatutDemande;
import fr.diginamic.gestionconges.entities.TypeAbsence;
import fr.diginamic.gestionconges.repositories.DemandeAbsenceRepository;

@Service
public class DemandeAbsenceService {

	@Autowired
	DemandeAbsenceRepository demandeAbsenceRepository;
	
	@Autowired
	CollaborateurService collaborateurService;
	
	public DemandeAbsence createDemandeAbsence(@Valid DemandeAbsence demandeAbsence) {
        return demandeAbsenceRepository.save(demandeAbsence);
    }
	
	public List<DemandeAbsenceDto> readAllDemandeAbsence() {
		List<DemandeAbsenceDto> demandesAbsenceDto = new ArrayList<>();
		for (DemandeAbsence demandeAbsence : demandeAbsenceRepository.findAll()) {
			demandesAbsenceDto.add(toDto(demandeAbsence));
		}
		return demandesAbsenceDto;
	}
	
	public DemandeAbsenceDto readSingleDemandeAbsence(int id) throws Exception {
		if (demandeAbsenceRepository.findById(id).isEmpty()) {
			throw new Exception("La demande d'absence avec l'id " + id + " n'existe pas");
		}
		return toDto(demandeAbsenceRepository.findById(id).get());
	}
	
	public DemandeAbsence updateDemandeAbsence(int id, @Valid DemandeAbsence demandeAbsence) throws Exception {
		if (demandeAbsenceRepository.findById(id).isEmpty()) {
			throw new Exception("La demande d'absence avec l'id " + id + " n'existe pas");
		}
		if (id != demandeAbsence.getId()) {
			throw new Exception("La variable d'URL id = " + id + " est différente de l'id de la demande d'absence JSON (id = " + demandeAbsence.getId() + ")");
		}
		return demandeAbsenceRepository.save(demandeAbsence);
	}
	
	public ResponseEntity<String> deleteDemandeAbsence(int id) throws Exception {
		if (demandeAbsenceRepository.findById(id).isEmpty()) {
			throw new Exception("La demande d'absence avec l'id " + id + " n'existe pas");
		}
		demandeAbsenceRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Demande d'absence supprimée");
	}
	
	public DemandeAbsence toEntity(DemandeAbsenceDto demandeAbsenceDto) {
		DemandeAbsence demandeAbsence = new DemandeAbsence();
		demandeAbsence.setId(demandeAbsenceDto.getId());
		demandeAbsence.setDateDebut(LocalDate.parse(demandeAbsenceDto.getDateDebut(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		demandeAbsence.setDateFin(LocalDate.parse(demandeAbsenceDto.getDateFin(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		demandeAbsence.setTypeAbsence(TypeAbsence.valueOf(demandeAbsenceDto.getTypeAbsence()));
		demandeAbsence.setStatut(StatutDemande.valueOf(demandeAbsenceDto.getStatut()));
		demandeAbsence.setMotifAbsence(demandeAbsenceDto.getMotifAbsence());
		demandeAbsence.setCollaborateur(collaborateurService.toEntity(demandeAbsenceDto.getCollaborateur()));
		return demandeAbsence;
	}
	
	public DemandeAbsenceDto toDto(DemandeAbsence demandeAbsence) {
		DemandeAbsenceDto demandeAbsenceDto = new DemandeAbsenceDto();
		demandeAbsenceDto.setId(demandeAbsence.getId());
		demandeAbsenceDto.setDateDebut(demandeAbsence.getDateDebut().toString());
		demandeAbsenceDto.setDateFin(demandeAbsence.getDateFin().toString());
		demandeAbsenceDto.setTypeAbsence(demandeAbsence.getTypeAbsence().toString());
		demandeAbsenceDto.setStatut(demandeAbsence.getStatut().toString());
		demandeAbsenceDto.setMotifAbsence(demandeAbsence.getMotifAbsence());
		demandeAbsenceDto.setCollaborateur(collaborateurService.toDtoSimple(demandeAbsence.getCollaborateur()));
		return demandeAbsenceDto;
	}
	
}
