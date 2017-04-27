package fr.enac.iessa16.cablage.model.core;

/**
 * Classe Arete
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Arete {
	
	private double distance;
	private double cout;
	private double poids;
	private Sommet sommetOrigine;
	private Sommet sommetExtremite;
	
	
	
	/**
	 * Constructeur de la classe Arete
	 * 
	 * @param origine
	 * @param extremite
	 * @param poids
	 */
	public Arete(Sommet origine, Sommet extremite, double poids) {
		
		this.sommetOrigine = origine;
		this.sommetExtremite = extremite;
		this.poids = poids;
		
		this.distance = origine.calculerDistance(extremite);
		this.cout = distance * poids;
	}

	/* 
	 * Getters et Setters des attributs de la classe
	 */
	public double getDistance() {
		return distance;
	}

	public double getCout() {
		return cout;
	}

	public double getPoids() {
		return poids;
	}

	public Sommet getSommetOrigine() {
		return sommetOrigine;
	}

	public Sommet getSommetExtremité() {
		return sommetExtremite;
	}
	
	public void setPoids(double poids) {
		this.poids = poids;
		this.cout = poids * distance;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Arete ["+sommetOrigine + ", " + sommetExtremite + "]";
	}
}
