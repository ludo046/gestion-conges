package fr.diginamic.gestionconges.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jour_ferie")
public class JourFerie {
	
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
	@NotBlank
	@Column(name = "libelle")
	private String libelle;
	
	public JourFerie() {
		
	}
	
	public JourFerie(LocalDate dateDebut, LocalDate dateFin, String libelle) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.libelle = libelle;
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
