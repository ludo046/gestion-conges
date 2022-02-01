package fr.diginamic.gestionconges.security;

import org.springframework.security.core.GrantedAuthority;

/** Représente un rôle utilisateur
 * @author RichardBONNAMY
 *
 */
public class Role implements GrantedAuthority {

	/** nom */
	private String nom;

	/** Constructeur
	 * 
	 */
	public Role() {
	}
	
	/** Constructeur
	 * @param nom nom du rôle
	 */
	public Role(String nom) {
		super();
		this.nom = nom;
	}


	@Override
	public String getAuthority() {
		return nom;
	}

}
