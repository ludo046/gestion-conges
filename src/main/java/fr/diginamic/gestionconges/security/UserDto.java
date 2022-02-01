package fr.diginamic.gestionconges.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.diginamic.gestionconges.entities.Collaborateur;

public class UserDto {

	private Integer id;
    private String identifiant;
    private String nom;
    private String prenom;
    private List<Role> roles = new ArrayList<>();

    public UserDto(Collaborateur col) {
    	this.id = col.getId();
        this.identifiant = col.getIdentifiant();
        this.nom = col.getNom();
        this.prenom = col.getPrenom();
        this.roles = Arrays.asList(new Role(col.getRole()));
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }



	/** Getter
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/** Setter
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/** Getter
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}



	/** Setter
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
}