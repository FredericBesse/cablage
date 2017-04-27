package fr.enac.iessa16.cablage.model.core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Cablage
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Cablage {
	
	private ArrayList<Sommet> sommetsSelectionnes;
	private ArrayList<Sommet> sommetsUtiles;
	private ArrayList<Arete> aretes;
	private double cout;
	
	private HashMap<Sommet, ArrayList<Arete>> aretesParSommet;
	

	/**
	 * Constructeur de la classe Cablage
	 * 
	 * @param sommetsSelectionnes
	 * @param sommetsUtiles
	 * @param listeAretes
	 */
	public Cablage(ArrayList<Sommet> sommetsSelectionnes, ArrayList<Sommet> sommetsUtiles, ArrayList<Arete> listeAretes) {
		
		this.sommetsSelectionnes = sommetsSelectionnes;
		this.sommetsUtiles = sommetsUtiles;
		this.aretes = listeAretes;
		
		this.aretesParSommet = new HashMap<Sommet, ArrayList<Arete>>();
		
		ArrayList<Arete> aretes;
		Sommet origine, extremite;
		
		for (Arete arete : listeAretes) {
			
			origine = arete.getSommetOrigine();
			extremite = arete.getSommetExtremité();
			
			if(!this.aretesParSommet.containsKey(origine)) {
				aretes = new ArrayList<Arete>();
				aretes.add(arete);
				aretesParSommet.put(origine, aretes);
			} else {
				aretesParSommet.get(origine).add(arete);				
			}
			
			if(!this.aretesParSommet.containsKey(extremite)) {
				aretes = new ArrayList<Arete>();
				aretes.add(arete);
				aretesParSommet.put(extremite, aretes);
			} else {
				aretesParSommet.get(extremite).add(arete);				
			}	
		}		
	}
	
	public Cablage(Cablage chemin) {
		this(chemin.getSommetsSelectionnes(), chemin.getSommetsUtiles(), chemin.getChemin());
	}
	
	/*
	 * Getters
	 */
	
	public HashMap<Sommet, ArrayList<Arete>> getAretesParSommet() {
		return aretesParSommet;
	}	

	public ArrayList<Arete> getChemin() {
		return aretes;
	}

	public double getCout() {
		
		// Calcul du cout
		this.cout = 0;
		for (Arete arete : this.aretes) {
			this.cout += arete.getCout();
		}
		
		return cout;
	}
	
	public ArrayList<Sommet> getSommetsUtiles() {
		return sommetsUtiles;
	}

	public ArrayList<Sommet> getSommetsSelectionnes() {
		return sommetsSelectionnes;
	}
}
