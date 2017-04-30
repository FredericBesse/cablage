package fr.enac.iessa16.cablage.model.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Classe Arete
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class Arete extends DefaultWeightedEdge {
	
	@XmlAttribute
	private double distance;
	@XmlAttribute
	private double cout;
	@XmlAttribute
	private double poids;
	private Sommet sommetOrigine;
	private Sommet sommetExtremite;
	
	
	/**
	 * Constructeur par défaut de la classe 
	 */
	public Arete() {
		
		super();
		
		
	}
	
	/**
	 * Constructeur de la classe Arete
	 * 
	 * @param origine
	 * @param extremite
	 * @param poids
	 */
	public Arete(Sommet origine, Sommet extremite, double poids) {
		
		super();
		
		this.sommetOrigine = origine;
		this.sommetExtremite = extremite;
		this.poids = poids;
		
		this.distance = origine.calculerDistance(extremite);
		this.cout = distance * poids;
		
		
		
	}
	
	
	@Override
	protected double getWeight() {
		return cout;
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
	
	
	@Override
	public boolean equals(Object obj) {
		
		Arete a = (Arete) obj;
		
		return (a.sommetExtremite.equals(this.sommetExtremite) && a.sommetOrigine.equals(this.sommetOrigine))
				|| (a.sommetExtremite.equals(this.sommetOrigine) && a.sommetOrigine.equals(this.sommetExtremite));

	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Arete ["+sommetOrigine + ", " + sommetExtremite + "]";
	}
}
