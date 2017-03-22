package fr.enac.iessa16.cablage.model;

import java.awt.Color;

public class Sommet {

	/**
	 * 
	 * Classe Sommet théorique , possède en attributs , une latitude, une longitude ainsi qu'un nom.
	 * 
	 * 
	 * @author Frederic Besse et Hedidi Racha
	 *
	 */

	private double longitude ;
	private double latitude;
	private String nom;
	
	/**
	 * Constructeur de la classe sommet
	 * 
	 * @param latitude
	 * @param longitude
	 * @param nom
	 */
	
	
	
	public Sommet(double longitude, double latitude,String nom) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.nom = nom;
	
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



	/**setter du nom du sommet 
	 * @param nom
	 */

	public void setNom(String nom) {
		this.nom = nom;
	}



	/**Methode qui determine la distance entre deux sommets.
	 * @param : un sommet som
	 * @return : distance 
	 */

	public double Calculdistance(Sommet som)
	{
		double distance = 0;
		distance = Math.sqrt(Math.pow(som.latitude-this.latitude,2)+Math.pow(som.longitude-this.longitude,2));
		return distance;
	}





	
	
	
	
	
}
