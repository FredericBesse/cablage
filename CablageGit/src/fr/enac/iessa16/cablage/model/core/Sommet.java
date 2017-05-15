package fr.enac.iessa16.cablage.model.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe Sommet théorique, possède en attributs une latitude, 
 * une longitude ainsi qu'un nom.
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Sommet {

	@XmlAttribute
	private String nom;
	@XmlAttribute
	private double abscisse ;
	@XmlAttribute
	private double ordonnee;
	@XmlTransient
	private boolean selected;
	
	private int id;
	
	private static int nbSommetCree = 0;

	
	/**
	 * Constructeur par défaut de la classe sommet 
	 */
	public Sommet() {
		
		nbSommetCree++;
		this.id = nbSommetCree;
		
	}
	
	
	/**
	 * Constructeur de la classe Sommet
	 * 
	 * @param abscisse l'abscisse du sommet
	 * @param ordonnee l'ord du sommet
	 * @param nom le nom
	 */
	public Sommet(double abscisse, double ordonnee, String nom) {

		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
		this.nom = nom;
		this.selected = false;
		
		nbSommetCree++;
		this.id = nbSommetCree;
	}
	
	
	
	/* 
	 * Getters et setters
	 */
	public double getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(double abscisse) {
		this.abscisse = abscisse;
	}

	/**
	 * @return latitude 
	 */
	public double getOrdonnee() {
		return ordonnee;
	}

	/**
	 * Setter de l'ordonnee
	 * 
	 * @param ordonnee la nouvelle ordonnee
	 */
	public void setOrdonnee(double ordonnee) {
		this.ordonnee = ordonnee;
	}

	/**
	 * @return nom le nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Setter du nom du sommet 
	 * @param nom le nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}	
	
	public boolean getSelected() {	
		return selected;	
	}
	
	public void setSelected(boolean value) {
		this.selected = value;
	}


	/**
	 * Méthode qui determine la distance entre deux sommets
	 *   
	 * @param sommet le sommet
	 * @return la distance
	 */
	public double calculerDistance(Sommet sommet) {
		double distance = 0;
		distance = Math.sqrt(Math.pow(sommet.ordonnee-this.ordonnee,2)+Math.pow(sommet.abscisse-this.abscisse,2));
		return distance;
	}


	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		return (this.ordonnee == ((Sommet)obj).getOrdonnee())&&(this.abscisse == ((Sommet)obj).getAbscisse());
	}
	
	


	@Override
	public String toString() {
		
		if (nom.length() == 0)
			return Integer.toString(id);
		else return nom;
			
				
	}	
}
