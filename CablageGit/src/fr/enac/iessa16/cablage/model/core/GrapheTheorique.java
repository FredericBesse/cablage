package fr.enac.iessa16.cablage.model.core;

import java.util.ArrayList;

/**
 * Classe GrapheTheorique définissant le modèle théorique d'un graphe.
 * 
 * Un graphe possède comme attribut une liste de sommets et une liste d'aretes.
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class GrapheTheorique {

	// La liste des sommets du graphe
	private ArrayList<Sommet> listeSommets;

	// La liste des aretes du graphe
	private ArrayList<Arete> listeAretes;

	private double latitudeMax;
	private double latitudeMin;
	private double longitudeMin;
	private double longitudeMax;

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

		this.listeSommets = listeSommets;
		this.listeAretes = listeAretes;

		calculExtremumDonnees();
	}

	private void calculExtremumDonnees() {
		
		longitudeMin = Double.MAX_VALUE;
		longitudeMax = Double.MIN_VALUE;

		latitudeMin = Double.MAX_VALUE;
		latitudeMax = Double.MIN_VALUE;

		double longitudeCourante, latitudeCourante;
		

		for (int i = 0; i < listeSommets.size(); i++) {
			
			longitudeCourante = listeSommets.get(i).getLongitude();
			latitudeCourante = listeSommets.get(i).getLatitude();

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
