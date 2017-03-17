package fr.enac.iessa16.cablage.model.graph;


/**
 * 	Creation théorique d'une arete : une arete est une droite reliant 
 * un sommet origine et un sommet destination  (d'où la presence de deux sommets en attributs), 
 * chaque arete dispose d'une distance et d'un poids , dont le produit des deux nous permettra 
 * de definir un cout de l'arete.
 * 
 * @author hedidira
 *
 */
public class Arete {
	 
	

	
	private Sommet sommetOrigine; // origine   
	private Sommet sommetDestination; // extrémité
	private double distance;
	private double poids;
	private double cout;
	
	
	
	/*Creation du constructeur arete , */
	
	public Arete(Sommet sommetOrigine, Sommet sommetDestination, double poids) {
	//	super();
		this.sommetOrigine = sommetOrigine;
		this.sommetDestination = sommetDestination;
		this.poids = poids;
		
		this.distance = sommetOrigine.distance(sommetDestination);//l'attribut distance est la distance entre le sommet 1 et le sommet 2
		this.cout = this.distance * this.poids; /*le cout de chaque arete vaut le produit entre la norme de la droite reliant les 
		 2 sommets et le poids*/
		
	}
	
	
	
	/* Getters et Setters des attributs de la classe */
	public Sommet getSommetOrigine() {   
		return sommetOrigine;
	}
	public void setSommetOrigine(Sommet sommetOrigine) {
		this.sommetOrigine = sommetOrigine;
	}
	public Sommet getSommetDestination() {
		return sommetDestination;
	}
	public void setSommetDestination(Sommet sommetDestination) {
		this.sommetDestination = sommetDestination;
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
