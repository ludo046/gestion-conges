package fr.diginamic.gestionconges.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.diginamic.gestionconges.dto.DemandeAbsenceDto;
import fr.diginamic.gestionconges.dto.JourFerieDto;
import fr.diginamic.gestionconges.entities.DemandeAbsence;
import fr.diginamic.gestionconges.entities.JourFerie;
import fr.diginamic.gestionconges.entities.StatutDemande;
import fr.diginamic.gestionconges.entities.TypeAbsence;
import fr.diginamic.gestionconges.repositories.JourFerieRepository;

@Service
public class JourFerieService {

	@Autowired
	JourFerieRepository jourFerieRepository;
	
	public JourFerie createJourFerie(@Valid JourFerie jourFerie) {
        return jourFerieRepository.save(jourFerie);
    }
	
	public Iterable<JourFerie> readAllJourFerie() {
		return jourFerieRepository.findAll();
	}
	
	public Optional<JourFerie> readSingleJourFerie(int id) throws Exception {
		if (jourFerieRepository.findById(id).isEmpty()) {
			throw new Exception("Le jour férié avec l'id " + id + " n'existe pas");
		}
		return jourFerieRepository.findById(id);
	}
	
	public JourFerie updateJourFerie(int id, @Valid JourFerie jourFerie) throws Exception {
		if (jourFerieRepository.findById(id).isEmpty()) {
			throw new Exception("Le jour férié avec l'id " + id + " n'existe pas");
		}
		if (id != jourFerie.getId()) {
			throw new Exception("La variable d'URL id = " + id + " est différente de l'id du jour férié JSON (id = " + jourFerie.getId() + ")");
		}
		return jourFerieRepository.save(jourFerie);
	}
	
	public ResponseEntity<String> deleteJourFerie(int id) throws Exception {
		if (jourFerieRepository.findById(id).isEmpty()) {
			throw new Exception("Le jour férié avec l'id " + id + " n'existe pas");
		}
		jourFerieRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Jour ferié supprimé");
	}
	
	public JourFerie toEntity(JourFerieDto jourFerieDto) {
		JourFerie jourFerie = new JourFerie();
		jourFerie.setId(jourFerieDto.getId());
		jourFerie.setDateDebut(LocalDate.parse(jourFerieDto.getDateDebut(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		jourFerie.setDateFin(LocalDate.parse(jourFerieDto.getDateFin(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		jourFerie.setLibelle(jourFerieDto.getLibelle());
		return jourFerie;
	}
	
	public JourFerieDto toDto(JourFerie jourFerie) {
		JourFerieDto jourFerieDto = new JourFerieDto();
		jourFerieDto.setId(jourFerie.getId());
		jourFerieDto.setDateDebut(jourFerie.getDateDebut().toString());
		jourFerieDto.setDateFin(jourFerie.getDateFin().toString());
		jourFerieDto.setLibelle(jourFerie.getLibelle());
		return jourFerieDto;
	}

}
