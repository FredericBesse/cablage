package fr.enac.iessa16.cablage.xml;


import java.util.ArrayList;


/**
 * Classe Graphe définissant le modèle théorique d'un graphe.
 * 
 * Un graphe possède commme attribut une liste de sommets et une liste d'aretes.
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */



public class CopyGrapheTheoriqueXML {
	
	 //La liste des sommets du graphe
	private ArrayList <CopySommetXML> ensembleDeSommet;
	
	// La liste des aretes du graphe
	
	private ArrayList<CopyAreteXML>  ensembleAretes;
	
	/**
	 * Constructeur de la classe Graphe, permet de construire un graphe 
	 * à partir d'une liste de sommets et d'aretes
	 * 
	 * @param ensembleDeSommet la liste des sommets
	 * @param   ensembleAretes la liste des aretes
	 */
	
	public CopyGrapheTheoriqueXML(ArrayList<CopySommetXML> ensembleDeSommet, ArrayList<CopyAreteXML> ensembleAretes) {
		super();
		this.ensembleDeSommet = ensembleDeSommet;
		this.ensembleAretes = ensembleAretes;
	}

	
	 //Getters et Setters de la liste des Sommets et des aretes.
	 
	
	
	
	public ArrayList<CopySommetXML> getEnsembleDeSommet() {
		return ensembleDeSommet;
	}



	public void setEnsembleDeSommet(ArrayList<CopySommetXML> ensembleDeSommet) {
		this.ensembleDeSommet = ensembleDeSommet;
	}



	public ArrayList<CopyAreteXML> getEnsembleAretes() {
		return ensembleAretes;
	}



	public void setEnsembleAretes(ArrayList<CopyAreteXML> ensembleAretes) {
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
