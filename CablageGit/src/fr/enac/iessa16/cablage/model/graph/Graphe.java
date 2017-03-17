package fr.enac.iessa16.cablage.model.graph;

import java.util.ArrayList;

/**
 * Classe Graphe théorique : possède commme attribut,une liste de sommets et une liste d'aretes.
 * 
 * @author hedidira
 *
 */

public class Graphe {
	
	
	private ArrayList<Sommet> listeSommets;
	private ArrayList<Arete>  listeAretes;
	
	/**
	 * Constructeur de la classe Graphe
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
	
	
}
