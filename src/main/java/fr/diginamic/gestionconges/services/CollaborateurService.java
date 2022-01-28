package fr.diginamic.gestionconges.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.diginamic.gestionconges.dto.CollaborateurDto;
import fr.diginamic.gestionconges.entities.Collaborateur;
import fr.diginamic.gestionconges.repositories.CollaborateurRepository;

@Service
public class CollaborateurService {

	@Autowired
	CollaborateurRepository collaborateurRepository;
	
	public Collaborateur createCollaborateur(@Valid Collaborateur collaborateur) {
        return collaborateurRepository.save(collaborateur);
    }
	
	public List<CollaborateurDto> readAllCollaborateur() {
		List<CollaborateurDto> collaborateursDto = new ArrayList<>();
		for (Collaborateur collaborateur : collaborateurRepository.findAll()) {
			 collaborateursDto.add(toDto(collaborateur));
		}
		return collaborateursDto;
	}
	
	public CollaborateurDto readSingleCollaborateur(int id) throws Exception {
		if (collaborateurRepository.findById(id).isEmpty()) {
			throw new Exception("Le collaborateur avec l'id " + id + " n'existe pas");
		}
		return toDto(collaborateurRepository.findById(id).get());
	}
	
	public Collaborateur updateCollaborateur(int id, @Valid Collaborateur collaborateur) throws Exception {
		if (collaborateurRepository.findById(id).isEmpty()) {
			throw new Exception("Le collaborateur avec l'id " + id + " n'existe pas");
		}
		if (id != collaborateur.getId()) {
			throw new Exception("La variable d'URL id = " + id + " est différente de l'id du collaborateur JSON (id = " + collaborateur.getId() + ")");
		}
		return collaborateurRepository.save(collaborateur);
	}
	
	public ResponseEntity<String> deleteCollaborateur(int id) throws Exception {
		if (collaborateurRepository.findById(id).isEmpty()) {
			throw new Exception("Le collaborateur avec l'id " + id + " n'existe pas");
		}
		collaborateurRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Collaborateur supprimé");
	}
	
	public Collaborateur toEntity(CollaborateurDto collaborateurDto) {
		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setId(collaborateurDto.getId());
		collaborateur.setPrenom(collaborateurDto.getPrenom());
		collaborateur.setNom(collaborateurDto.getNom());
		collaborateur.setIdentifiant(collaborateurDto.getIdentifiant());
		collaborateur.setMotDePasse(collaborateurDto.getMotDePasse());
		collaborateur.setCongesAquis(collaborateurDto.getCongesAquis());
		collaborateur.setCongesPoses(collaborateurDto.getCongesPoses());
		collaborateur.setRttAquis(collaborateurDto.getRttAquis());
		collaborateur.setRttPoses(collaborateurDto.getRttPoses());
		if (!collaborateurDto.getSubordonnes().isEmpty()) {
			for (CollaborateurDto collabDto : collaborateurDto.getSubordonnes()) {
				Collaborateur collab = toEntity(collabDto);
				collaborateur.getSubordonnes().add(collab);
			}
		}
		if (collaborateurDto.getManager() != null) {
			collaborateur.setManager(toEntity(collaborateurDto.getManager()));
		}
		return collaborateur;
	}
	
	public CollaborateurDto toDto(Collaborateur collaborateur) {
		CollaborateurDto collaborateurDto = new CollaborateurDto();
		collaborateurDto.setId(collaborateur.getId());
		collaborateurDto.setPrenom(collaborateur.getPrenom());
		collaborateurDto.setNom(collaborateur.getNom());
		collaborateurDto.setCongesAquis(collaborateur.getCongesAquis());
		collaborateurDto.setCongesPoses(collaborateur.getCongesPoses());
		collaborateurDto.setRttAquis(collaborateur.getRttAquis());
		collaborateurDto.setRttPoses(collaborateur.getRttPoses());
		collaborateurDto.setIdentifiant(collaborateur.getIdentifiant());
		if (!collaborateur.getSubordonnes().isEmpty()) {
			for (Collaborateur collab : collaborateur.getSubordonnes()) {
				CollaborateurDto collabDto = toDto(collab);
				collaborateurDto.getSubordonnes().add(collabDto);
			}
		}
		if (collaborateur.getManager() != null) {
			collaborateurDto.setManager(toDtoSimple(collaborateur.getManager()));
		}
		return collaborateurDto;
	}
	
	public CollaborateurDto toDtoSimple(Collaborateur collaborateur) {
		CollaborateurDto collaborateurDto = new CollaborateurDto();
		collaborateurDto.setId(collaborateur.getId());
		collaborateurDto.setPrenom(collaborateur.getPrenom());
		collaborateurDto.setNom(collaborateur.getNom());
		collaborateurDto.setCongesAquis(collaborateur.getCongesAquis());
		collaborateurDto.setCongesPoses(collaborateur.getCongesPoses());
		collaborateurDto.setRttAquis(collaborateur.getRttAquis());
		collaborateurDto.setRttPoses(collaborateur.getRttPoses());
		collaborateurDto.setIdentifiant(collaborateur.getIdentifiant());
		return collaborateurDto;
	}
	
}
