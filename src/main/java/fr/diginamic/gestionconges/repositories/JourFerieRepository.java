package fr.diginamic.gestionconges.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.gestionconges.entities.JourFerie;

@Repository
public interface JourFerieRepository extends CrudRepository<JourFerie, Integer> {

}
