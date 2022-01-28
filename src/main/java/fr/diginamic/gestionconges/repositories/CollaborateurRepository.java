package fr.diginamic.gestionconges.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.gestionconges.entities.Collaborateur;

@Repository
public interface CollaborateurRepository extends CrudRepository<Collaborateur, Integer> {

}
