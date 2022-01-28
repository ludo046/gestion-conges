package fr.diginamic.gestionconges.dto;

import java.util.HashSet;
import java.util.Set;

public class CollaborateurDto {

	private int id;
	private String prenom;
	private String nom;
	private String identifiant;
	private String motDePasse;
	private double congesAquis;
	private double congesPoses;
	private double rttAquis;
	private double rttPoses;
	private Set<DemandeAbsenceDto> demandesAbsence;
	private Set<CollaborateurDto> subordonnes;
	private CollaborateurDto manager;
	
	public CollaborateurDto() {
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
	
	public Set<DemandeAbsenceDto> getDemandesAbsence() {
		return demandesAbsence;
	}

	public void setDemandesAbsence(Set<DemandeAbsenceDto> demandesAbsence) {
		this.demandesAbsence = demandesAbsence;
	}

	public Set<CollaborateurDto> getSubordonnes() {
		return subordonnes;
	}

	public void setSubordonnes(Set<CollaborateurDto> subordonnes) {
		this.subordonnes = subordonnes;
	}

	public CollaborateurDto getManager() {
		return manager;
	}

	public void setManager(CollaborateurDto manager) {
		this.manager = manager;
	}
	
}
