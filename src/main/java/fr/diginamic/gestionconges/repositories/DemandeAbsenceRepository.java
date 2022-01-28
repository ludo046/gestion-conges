package fr.diginamic.gestionconges.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.gestionconges.entities.DemandeAbsence;

@Repository
public interface DemandeAbsenceRepository extends CrudRepository<DemandeAbsence, Integer> {

}
