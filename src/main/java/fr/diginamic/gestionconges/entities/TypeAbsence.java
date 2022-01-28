package fr.diginamic.gestionconges.entities;

public enum TypeAbsence {
	
	CONGE_PAYE("CP"),
	CONGE_SANS_SOLDE("CSS"),
	RTT_EMPLOYEUR("RTTE"),
	RTT_SALARIE("RTTS");
	
	public String libelle;
	
	private TypeAbsence(String libelle) {
		this.libelle = libelle;
	}

}
