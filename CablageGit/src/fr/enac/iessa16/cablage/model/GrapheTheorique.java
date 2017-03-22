package fr.enac.iessa16.cablage.model;


import java.util.ArrayList;


/**
 * Classe Graphe définissant le modèle théorique d'un graphe.
 * 
 * Un graphe possède commme attribut une liste de sommets et une liste d'aretes.
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */



public class GrapheTheorique {
	
	 //La liste des sommets du graphe
	private ArrayList <Sommet> ensembleDeSommet;
	
	// La liste des aretes du graphe
	
	private ArrayList<Arete>  ensembleAretes;
	
	/**
	 * Constructeur de la classe Graphe, permet de construire un graphe 
	 * à partir d'une liste de sommets et d'aretes
	 * 
	 * @param ensembleDeSommet la liste des sommets
	 * @param   ensembleAretes la liste des aretes
	 */
	
	public GrapheTheorique(ArrayList<Sommet> ensembleDeSommet, ArrayList<Arete> ensembleAretes) {
		super();
		this.ensembleDeSommet = ensembleDeSommet;
		this.ensembleAretes = ensembleAretes;
	}

	
	 //Getters et Setters de la liste des Sommets et des aretes.
	 
	
	
	
	public ArrayList<Sommet> getEnsembleDeSommet() {
		return ensembleDeSommet;
	}



	public void setEnsembleDeSommet(ArrayList<Sommet> ensembleDeSommet) {
		this.ensembleDeSommet = ensembleDeSommet;
	}



	public ArrayList<Arete> getEnsembleAretes() {
		return ensembleAretes;
	}



	public void setEnsembleAretes(ArrayList<Arete> ensembleAretes) {
		this.ensembleAretes = ensembleAretes;
	}
	/*
	public boolean existeArc(Sommet sommetorigine, Sommet sommetdestination)
	{
		for(int i =0 ; i<ensembleAretes.size();i++)
		{
			if(ensembleAretes.get(i).getSommetOrigine() ==  )
			
			
		}
		
	}*/
	
	
	
	
	
	

}
