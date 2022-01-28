package fr.diginamic.gestionconges.entities;

public enum StatutDemande {
	
	INITIALE("Demande initiale"),
	ATTENTE_VALIDATION("Demande en attente de validation"),
	VALIDEE("Demande validée"),
	REJETEE("Demande rejetée");
	
	public String libelle;
	
	private StatutDemande(String libelle) {
		this.libelle = libelle;
	}

}
