package fr.enac.iessa16.cablage.model.graph;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

/**
 * Classe Graphe définissant le modèle théorique d'un graphe.
 * 
 * Un graphe possède commme attribut une liste de sommets et une liste d'aretes.
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Graphe {
	
	/**
	 * La liste des sommets du graphe
	 */
	private ArrayList<Sommet> listeSommets;
	
	/**
	 * La liste des aretes du graphe
	 */
	private ArrayList<Arete>  listeAretes;
	
	
	/**
	 * Constructeur de la classe Graphe, permet de construire un graphe 
	 * à partir d'une liste de sommets et d'aretes
	 * 
	 * @param listeSommets la liste des sommets
	 * @param listeAretes la liste des aretes
	 */
	public Graphe(ArrayList<Sommet> listeSommets, ArrayList<Arete> listeAretes) {
		super();
		this.listeSommets = listeSommets;
		this.listeAretes = listeAretes;
	}

	
	/** Getters et Setters de la liste des Sommets et des aretes.
	 * 
	 * @return la liste des sommets
	 */
	public ArrayList<Sommet> getListeSommets() {
		return listeSommets;
	}



	public void setListeSommets(ArrayList<Sommet> listeSommets) {
		this.listeSommets = listeSommets;
	}

	/**
	 * @return la liste des aretes.
	 */
	public ArrayList<Arete> getListeAretes() {
		return listeAretes;
	}

	public void setListeAretes(ArrayList<Arete> listeAretes) {
		this.listeAretes = listeAretes;
	}
	
	
	
	public void getDijkstraShortestPath(Sommet origine, Sommet destination) {
		
		
		
		
	}
	
	
}
