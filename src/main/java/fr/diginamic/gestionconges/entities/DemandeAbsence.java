package fr.diginamic.gestionconges.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "demande_absence")
public class DemandeAbsence {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Future
	@Column(name = "date_debut")
	private LocalDate dateDebut;
	
	@NotNull
	@Future
	@Column(name = "date_fin")
	private LocalDate dateFin;
	
	@NotNull
	@Column(name = "type_absence")
	private TypeAbsence typeAbsence;
	
	@NotNull
	@Column(name = "statut")
	private StatutDemande statut = StatutDemande.INITIALE;
	
	@Column(name = "motif_absence")
	private String motifAbsence;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "collaborateur_id")
	private Collaborateur collaborateur;
	
	public DemandeAbsence() {

	}
	
	public DemandeAbsence(LocalDate dateDebut, LocalDate dateFin, TypeAbsence typeAbsence, StatutDemande statut) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.typeAbsence = typeAbsence;
		this.statut = statut;
	}

	public DemandeAbsence(LocalDate dateDebut, LocalDate dateFin, TypeAbsence typeAbsence, StatutDemande statut, String motifAbsence) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.typeAbsence = typeAbsence;
		this.statut = statut;
		this.motifAbsence = motifAbsence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public TypeAbsence getTypeAbsence() {
		return typeAbsence;
	}

	public void setTypeAbsence(TypeAbsence typeAbsence) {
		this.typeAbsence = typeAbsence;
	}

	public StatutDemande getStatut() {
		return statut;
	}

	public void setStatut(StatutDemande statut) {
		this.statut = statut;
	}

	public String getMotifAbsence() {
		return motifAbsence;
	}

	public void setMotifAbsence(String motifAbsence) {
		this.motifAbsence = motifAbsence;
	}

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	@Override
	public String toString() {
		return "DemandeAbsence [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", typeAbsence="
				+ typeAbsence + ", statut=" + statut + ", motifAbsence=" + motifAbsence + ", collaborateur="
				+ collaborateur + "]";
	}
	
}
