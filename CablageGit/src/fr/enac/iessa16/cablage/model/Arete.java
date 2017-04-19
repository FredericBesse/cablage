package fr.enac.iessa16.cablage.model;

public class Arete {
	

	
	
	private double distance;
	private double cout;
	private double poids;
	private Sommet SommetOrigine;
	private Sommet SommetExtremité;
	
	
	/*Creation du constructeur arete , */
	public Arete(Sommet Origine, Sommet Extremité, double poids) {
		//super();
		this.SommetOrigine = Origine;
		this.SommetExtremité = Extremité;
		this.poids = poids;
		
		
		this.distance = this.SommetOrigine.Calculdistance(SommetExtremité);
		this.cout = distance*cout;
		
	}

	/* Getters et Setters des attributs de la classe */

	public double getDistance() {
		return distance;
	}



	public void setDistance(double distance) {
		this.distance = distance;
	}



	public double getCout() {
		return cout;
	}



	public void setCout(double cout) {
		this.cout = cout;
	}



	public double getPoids() {
		return poids;
	}



	public void setPoids(double poids) {
		this.poids = poids;
	}



	public Sommet getSommetOrigine() {
		return SommetOrigine;
	}



	public void setSommetOrigine(Sommet sommetOrigine) {
		SommetOrigine = sommetOrigine;
	}



	public Sommet getSommetExtremité() {
		return SommetExtremité;
	}



	public void setSommetExtremité(Sommet sommetExtremité) {
		SommetExtremité = sommetExtremité;
	}

	@Override
	public String toString() {
		return "Arete ["+SommetOrigine + ", " + SommetExtremité + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
