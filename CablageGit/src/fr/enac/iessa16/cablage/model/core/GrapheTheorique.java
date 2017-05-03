package fr.enac.iessa16.cablage.model.core;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * Classe GrapheTheorique définissant le modèle théorique d'un graphe.
 * 
 * Un graphe possède comme attribut une liste de sommets et une liste d'aretes.
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GrapheTheorique extends SimpleWeightedGraph<Sommet, Arete>{

	// La liste des sommets du graphe
	@XmlElementWrapper(name = "sommets")
	@XmlElement(name = "sommet")
	private ArrayList<Sommet> listeSommets;

	// La liste des aretes du graphe
	@XmlElementWrapper(name = "aretes")
	@XmlElement(name = "arete")
	private ArrayList<Arete> listeAretes;

	@XmlTransient
	private double latitudeMax;
	@XmlTransient
	private double latitudeMin;
	@XmlTransient
	private double longitudeMin;
	@XmlTransient
	private double longitudeMax;

	
	/**
	 * Constructeur par défaut de la classe GrapheTheorique, crée un graphe vide 
	 */
	public GrapheTheorique() {
		
		super(Arete.class);
		
		this.listeSommets = new ArrayList<Sommet>();
		this.listeAretes = new ArrayList<Arete>();
		
	}
	
	
	/**
	 * Constructeur de la classe Graphe, permet de construire un graphe à partir
	 * d'une liste de sommets et d'aretes
	 * 
	 * @param listeSommets
	 *            la liste des sommets
	 * @param listeAretes
	 *            la liste des aretes
	 */
	public GrapheTheorique(ArrayList<Sommet> listeSommets, ArrayList<Arete> listeAretes) {

		super(Arete.class);
		
		this.ajouterSommets(listeSommets);
		
		this.ajouterAretes(listeAretes);
		
		calculExtremumDonnees();
	}
	
	
	private void ajouterSommets(ArrayList<Sommet> listeSommets) {
		if (listeSommets != null) {
			this.listeSommets = listeSommets;
			
			
			for (Sommet sommet : listeSommets) {
				this.addVertex(sommet);
			}
		}
		
		
		else 
			this.listeSommets = new ArrayList<Sommet>();
		
	}
	
	
	

	private void ajouterAretes(ArrayList<Arete> listeAretes) {
		if (listeAretes != null) {
			this.listeAretes = listeAretes;
			
			for (Arete arete : listeAretes) {
				
				this.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
				
			}
			
		} else
			this.listeAretes = new ArrayList<Arete>();

		
	}


	


	public void calculExtremumDonnees() {
		
		double longitudeCourante, latitudeCourante;
		int size;
		
		longitudeMin = Double.MAX_VALUE;
		longitudeMax = Double.MIN_VALUE;

		latitudeMin = Double.MAX_VALUE;
		latitudeMax = Double.MIN_VALUE;

		
		size = listeSommets.size();
		
		for (int i = 0; i < size; i++) {
			
			longitudeCourante = listeSommets.get(i).getAbscisse();
			latitudeCourante = listeSommets.get(i).getOrdonnee();

			if (longitudeCourante < longitudeMin) {
				longitudeMin = longitudeCourante;
			}
			
			if (longitudeCourante > longitudeMax) {
				longitudeMax = longitudeCourante;
			}

			if (latitudeCourante < latitudeMin) {
				latitudeMin = latitudeCourante;
			}

			if (latitudeCourante > latitudeMax) {
				latitudeMax = latitudeCourante;
			}
		}
	}
	
	
	// Getters et Setters 

	public ArrayList<Sommet> getListeSommets() {
		return listeSommets;
	}	

	public ArrayList<Arete> getListeAretes() {
		return listeAretes;
	}

	public double getLatitudeMax() {
		return latitudeMax;
	}

	public double getLatitudeMin() {
		return latitudeMin;
	}

	public double getLongitudeMin() {
		return longitudeMin;
	}

	public double getLongitudeMax() {
		return longitudeMax;
	}
}
