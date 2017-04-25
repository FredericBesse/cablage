package fr.enac.iessa16.cablage.model.algo;

import java.util.ArrayList;
import java.util.HashMap;

import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.Chemin;
import fr.enac.iessa16.cablage.model.core.Sommet;

public class Elagage {
	
	private Chemin chemin;
	private Chemin cheminElague;

	

	public Elagage(Chemin chemin) {
		
		this.chemin = chemin;
		this.cheminElague = new Chemin(chemin);
		this.elague();
	}
	
	
	private void elague() {
		
		int nbPasses = 0;
		boolean hasElague = true;
		
		HashMap<Sommet, ArrayList<Arete>> areteParSommet = cheminElague.getAretesParSommet();
		Arete areteASupprimer;
		Sommet origine, extremite;
		
		while (hasElague && nbPasses < 10) {
			nbPasses ++;
			hasElague = false;
			
			System.out.println("Phase "+nbPasses+" de l'élagage");
			
			for (Sommet sommet : areteParSommet.keySet()) {
				
				// Si le sommet n'est pas sélectionné
				if (!sommet.getSelected()) {
					
					// S'il est relié au graphe par une seule arete, on peut le supprimer
					if (areteParSommet.get(sommet).size() == 1) {
						
						System.out.println(" -> Sommet inutile relié par une seule arete trouvé : "+sommet);
						
						hasElague = true;
						
						areteASupprimer = areteParSommet.get(sommet).get(0);
						
						// on supprime le sommet inutile
						areteParSommet.remove(sommet);
						
						// on supprime l'arete de la liste des aretes du sommet de l'autre extremite
						origine = areteASupprimer.getSommetOrigine();
						extremite = areteASupprimer.getSommetOrigine();
						
						if (sommet.equals(origine)) {
							
							areteParSommet.get(extremite).remove(areteASupprimer);
							
							
						} else if (sommet.equals(extremite)) {
							
							areteParSommet.get(origine).remove(areteASupprimer);
							
							
						} else {
							System.out.println("ERREUR : ne devrait jamais se produire");
						}
						
						
						
					}
					
				}
				
			}
			
			
			
		}
		
	}
	
	public Chemin getChemin() {
		return chemin;
	}
	
	public Chemin getCheminElague() {
		return cheminElague;
	}

}
