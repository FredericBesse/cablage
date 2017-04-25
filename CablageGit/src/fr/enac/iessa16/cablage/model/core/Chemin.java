package fr.enac.iessa16.cablage.model.core;

import java.util.ArrayList;
import java.util.HashMap;

public class Chemin {
	
	private ArrayList<Arete> chemin;
	private double cout;
	
	private HashMap<Sommet, ArrayList<Arete>> aretesParSommet;
	

	public Chemin(ArrayList<Arete> listeAretes) {
		
		this.chemin = listeAretes;
		
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
	
	public HashMap<Sommet, ArrayList<Arete>> getAretesParSommet() {
		return aretesParSommet;
	}

	public Chemin(Chemin chemin) {
		
		this(chemin.getChemin());
	}

	public ArrayList<Arete> getChemin() {
		return chemin;
	}

	public double getCout() {
		
		// Calcul du cout
		this.cout = 0;
		for (Arete arete : this.chemin) {
			this.cout += arete.getCout();
		}
		
		return cout;
	}
}
