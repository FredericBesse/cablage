package fr.enac.iessa16.cablage.model.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.internal.txw2.annotation.XmlElement;

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
	private double longitude ;
	@XmlAttribute
	private double latitude;
	@XmlTransient
	private boolean selected;

	
	public Sommet() {
		
	}
	
	/**
	 * Constructeur de la classe sommet
	 * 
	 * @param latitude
	 * @param longitude
	 * @param nom
	 */	
	public Sommet(double longitude, double latitude, String nom) {

		this.longitude = longitude;
		this.latitude = latitude;
		this.nom = nom;
		this.selected = false;
	}
	
	
	/**
	 * @return longitude 
	 */	
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Setter de la Longitude
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude 
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Setter de la Latitude
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * Setter du nom du sommet 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
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
	 * @param sommet
	 * @return la distance
	 */
	public double calculerDistance(Sommet sommet) {
		double distance = 0;
		distance = Math.sqrt(Math.pow(sommet.latitude-this.latitude,2)+Math.pow(sommet.longitude-this.longitude,2));
		return distance;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sommet other = (Sommet) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nom;
	}	
}
