package fr.enac.iessa16.cablage.model;


import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;


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
	
	private double latitudemax;
	private double latitudemin;
	private double longitudemin;
	private double longitudemax;
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
		this.latitudemax = latitudemax;
		this.longitudemax = longitudemax;
		this.longitudemin = longitudemin;
		this.latitudemin = latitudemin;
		calculExtremumDonnées();
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
	/*
	
	private void calculExtremumDonnées2()
	{
		double longitudemin;
		double longitudemax;
		double longitudecourante = ensembleDeSommet.get(0).getLongitude();
		for(int i =0 ; i<ensembleDeSommet.size(); i++)
		{
		
			if(longitudecourante <= ensembleDeSommet.get(i).getLongitude())
			{
				
				longitudemin = longitudecourante;
				
			}
			if(z > ensembleDeSommet.get(i).getLongitude())
			{
				
				z = ensembleDeSommet.get(i).getLongitude();
				longitudemin = z;
				
				
			}
			
		
		
		
		}
		System.out.println("la valeur du minimum est" + z);
	}
	*/
	
	private void calculExtremumDonnées()
	{
		longitudemin = ensembleDeSommet.get(0).getLongitude();
		longitudemax = ensembleDeSommet.get(0).getLongitude();
		
		latitudemin = ensembleDeSommet.get(0).getLatitude();
		latitudemax = ensembleDeSommet.get(0).getLatitude();
		
		double longitudeCourante, latitudeCourante;
		//double z = ensembleDeSommet.get(0).getLongitude();
		
		for(int i =0 ; i<ensembleDeSommet.size(); i++)
		{
			longitudeCourante = ensembleDeSommet.get(i).getLongitude();
			latitudeCourante = ensembleDeSommet.get(i).getLatitude();
			
			if(longitudeCourante < longitudemin)
			{				
				longitudemin = longitudeCourante;
			}
			if(longitudeCourante > longitudemax)
			{
				
				longitudemax = longitudeCourante;
				
			}
			
			if(latitudeCourante < latitudemin)
			{
				
				latitudemin =latitudeCourante;
				
			}
			
			if(latitudeCourante > latitudemax)
			{
				
				latitudemax =latitudeCourante;
				
			}
			
			
			
		}
		System.out.println("la valeur longitude minimum est " + longitudemin);
		System.out.println("la valeur longitude max est " + longitudemax);
		System.out.println("la valeur latitude minimum est " + latitudemin);
		System.out.println("la valeur latitude max est " + latitudemax);
	}


	public double getLatitudemax() {
		return latitudemax;
	}


	public void setLatitudemax(double latitudemax) {
		this.latitudemax = latitudemax;
	}


	public double getLatitudemin() {
		return latitudemin;
	}


	public void setLatitudemin(double latitudemin) {
		this.latitudemin = latitudemin;
	}


	public double getLongitudemin() {
		return longitudemin;
	}


	public void setLongitudemin(double longitudemin) {
		this.longitudemin = longitudemin;
	}


	public double getLongitudemax() {
		return longitudemax;
	}


	public void setLongitudemax(double longitudemax) {
		this.longitudemax = longitudemax;
	}


	public boolean existeArc(int x0, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	

}





