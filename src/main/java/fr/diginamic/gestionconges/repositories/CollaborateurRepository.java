package fr.diginamic.gestionconges.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.gestionconges.entities.Collaborateur;

@Repository
public interface CollaborateurRepository extends CrudRepository<Collaborateur, Integer> {

	/** Extrait un collaborateur par identifiant
	 * @param identifiant identifiant
	 * @return Collaborateur
	 */
	public Collaborateur findByIdentifiant(String identifiant);
}
