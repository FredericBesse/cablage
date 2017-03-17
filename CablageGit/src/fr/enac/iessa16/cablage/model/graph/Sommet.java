package fr.enac.iessa16.cablage.model.graph;

public class Sommet {
	
	private double latitude;
	private double longitude;
	private String nom;
	
	public Sommet(double latitude, double longitude, String nom) {
	//	super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.nom = nom;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double distance(double lat, double lon) {
		double d = 0;
		
		// TODO trouver formule
		
		return d;
	}
	
	public double distance(Sommet som) {
		double d = 0;
		
		// TODO trouver formule
		d = Math.sqrt(Math.pow(som.latitude-this.latitude,2)+Math.pow(som.longitude-this.longitude,2));
		
		return d;
	}
	
	
	
}
