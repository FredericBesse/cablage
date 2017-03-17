package fr.enac.iessa16.cablage.model.graph;


public class Arete {
	
	private Sommet sommet1;
	private Sommet sommet2;
	private double distance;
	private double poids;
	private double cout;
	
	
	
	
	
	public Arete(Sommet sommet1, Sommet sommet2, double poids) {
	//	super();
		this.sommet1 = sommet1;
		this.sommet2 = sommet2;
		this.poids = poids;
		
		this.distance = sommet1.distance(sommet2);
		this.cout = this.distance * this.poids;
		
	}
	
	
	
	
	public Sommet getSommet1() {
		return sommet1;
	}
	public void setSommet1(Sommet sommet1) {
		this.sommet1 = sommet1;
	}
	public Sommet getSommet2() {
		return sommet2;
	}
	public void setSommet2(Sommet sommet2) {
		this.sommet2 = sommet2;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getPoids() {
		return poids;
	}
	public void setPoids(double poids) {
		this.poids = poids;
	}
	public double getCout() {
		return cout;
	}
	public void setCout(double cout) {
		this.cout = cout;
	}
	
	
	
	

}
