package fr.diginamic.gestionconges.dto;

public class DemandeAbsenceDto {

	private int id;
	private String dateDebut;
	private String dateFin;
	private String typeAbsence;
	private String statut = "INITIALE";
	private String motifAbsence;
	private CollaborateurDto collaborateur;
	
	public DemandeAbsenceDto() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getTypeAbsence() {
		return typeAbsence;
	}

	public void setTypeAbsence(String typeAbsence) {
		this.typeAbsence = typeAbsence;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getMotifAbsence() {
		return motifAbsence;
	}

	public void setMotifAbsence(String motifAbsence) {
		this.motifAbsence = motifAbsence;
	}

	public CollaborateurDto getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(CollaborateurDto collaborateur) {
		this.collaborateur = collaborateur;
	}
	
}
