package fr.enac.iessa16.cablage.model.graph;

import java.util.ArrayList;

public class Graphe {
	
	
	private ArrayList<Sommet> listeSommets;
	private ArrayList<Arete>  listeAretes;
	
	public Graphe(ArrayList<Sommet> listeSommets, ArrayList<Arete> listeAretes) {
		super();
		this.listeSommets = listeSommets;
		this.listeAretes = listeAretes;
	}

	public ArrayList<Sommet> getListeSommets() {
		return listeSommets;
	}

	public void setListeSommets(ArrayList<Sommet> listeSommets) {
		this.listeSommets = listeSommets;
	}

	public ArrayList<Arete> getListeAretes() {
		return listeAretes;
	}

	public void setListeAretes(ArrayList<Arete> listeAretes) {
		this.listeAretes = listeAretes;
	}
	
	
}
