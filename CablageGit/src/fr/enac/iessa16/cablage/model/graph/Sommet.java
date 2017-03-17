package fr.enac.iessa16.cablage.model.graph;

/**
 * 
 * Classe Sommet théorique , possède en attributs , une latitude, une longitude ainsi qu'un nom.
 * 
 * 
 * @author hedidira
 *
 */


public class Sommet {
	
	private double latitude;
	private double longitude;
	private String nomSommet;
	
	/**
	 * Constructeur de la classe sommet
	 * 
	 * @param latitude
	 * @param longitude
	 * @param nom
	 */
	public Sommet(double latitude, double longitude, String nom) {
	//	super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.nomSommet = nom;
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
	 * @return la longitude 
	 */
	public double getLongitude() {
		return longitude;
	}

	/**Setter de la longitude 
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return le nom
	 */
	public String getNom() {
		return nomSommet;
	}

	/**setter du nom du sommet 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nomSommet = nom;
	}

	/*public double distance(double lat, double lon) {
		double d = 0;
		
		// TODO trouver formule
		
		return d;
	}*/
	
	
	
	/**Methode qui determine la distance entre deux sommets.
	 * @param : un sommet som
	 * @return : distance 
	 */
	public double distance(Sommet som) {
		double d = 0;
		
		// TODO trouver formule
		d = Math.sqrt(Math.pow(som.latitude-this.latitude,2)+Math.pow(som.longitude-this.longitude,2));
		
		return d;
	}
	
	
	
}
