package fr.enac.iessa16.cablage.xml;

public class CopyAreteXML {
	
	
	private double distance;
	private double cout;
	private double poids;
	private CopySommetXML SommetOrigine;
	private CopySommetXML SommetExtremité;
	
	
	/*Creation du constructeur arete , */
	public CopyAreteXML(CopySommetXML Origine, CopySommetXML Extremité, double poids) {
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



	public CopySommetXML getSommetOrigine() {
		return SommetOrigine;
	}



	public void setSommetOrigine(CopySommetXML sommetOrigine) {
		SommetOrigine = sommetOrigine;
	}



	public CopySommetXML getSommetExtremité() {
		return SommetExtremité;
	}



	public void setSommetExtremité(CopySommetXML sommetExtremité) {
		SommetExtremité = sommetExtremité;
	}
	
	
	
	
	
	
	
	
	
	

}
