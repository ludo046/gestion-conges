package fr.diginamic.gestionconges.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "collaborateur")
public class Collaborateur {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Column(name = "prenom")
	private String prenom;
	
	@NotNull
	@NotBlank
	@Column(name = "nom")
	private String nom;
	
	@NotNull
	@NotBlank
	@Column(name = "identifiant")
	private String identifiant;
	
	@NotNull
	@NotBlank
	@Column(name = "mot_de_passe")
	private String motDePasse;
	
	@Column(name = "conges_aquis")
	private double congesAquis = 25;
	
	@Column(name = "conges_poses")
	private double congesPoses;
	
	@Column(name = "rtt_aquis")
	private double rttAquis = 6;
	
	@Column(name = "rtt_poses")
	private double rttPoses;
	
	/** role */
	@Column(name = "role")
	private String role;
	
	@OneToMany
	@JoinColumn(name = "collaborateur_id")
	private Set<DemandeAbsence> demandesAbsence;
	
	@OneToMany
	@JoinColumn(name = "manager_id")
	private Set<Collaborateur> subordonnes;
	
	@ManyToOne
	@JoinColumn(name = "manager_id", insertable = false, updatable = false)
	private Collaborateur manager;
	
	public Collaborateur() {
		demandesAbsence = new HashSet<>();
		subordonnes = new HashSet<>();
	}
	
	public Collaborateur(String prenom, String nom, String identifiant, String motDePasse) {
		this.prenom = prenom;
		this.nom = nom;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		demandesAbsence = new HashSet<>();
		subordonnes = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public double getCongesAquis() {
		return congesAquis;
	}

	public void setCongesAquis(double congesAquis) {
		this.congesAquis = congesAquis;
	}

	public double getCongesPoses() {
		return congesPoses;
	}

	public void setCongesPoses(double congesPoses) {
		this.congesPoses = congesPoses;
	}

	public double getRttAquis() {
		return rttAquis;
	}

	public void setRttAquis(double rttAquis) {
		this.rttAquis = rttAquis;
	}

	public double getRttPoses() {
		return rttPoses;
	}

	public void setRttPoses(double rttPoses) {
		this.rttPoses = rttPoses;
	}

	public Set<DemandeAbsence> getDemandesAbsence() {
		return demandesAbsence;
	}

	public void setDemandesAbsence(Set<DemandeAbsence> demandesAbsence) {
		this.demandesAbsence = demandesAbsence;
	}

	public Collaborateur getManager() {
		return manager;
	}

	public void setManager(Collaborateur manager) {
		this.manager = manager;
	}

	public Set<Collaborateur> getSubordonnes() {
		return subordonnes;
	}

	public void setSubordonnes(Set<Collaborateur> subordonnes) {
		this.subordonnes = subordonnes;
	}

	@Override
	public String toString() {
		return "Collaborateur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", identifiant=" + identifiant
				+ ", motDePasse=" + motDePasse + ", congesAquis=" + congesAquis + ", congesPoses=" + congesPoses
				+ ", rttAquis=" + rttAquis + ", rttPoses=" + rttPoses + ", demandesAbsence=" + demandesAbsence
				+ ", subordonnes=" + subordonnes + ", manager=" + manager + "]";
	}

	/** Getter
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/** Setter
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
