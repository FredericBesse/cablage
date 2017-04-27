package fr.enac.iessa16.cablage.model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.Cablage;
import fr.enac.iessa16.cablage.model.core.Sommet;

/**
 * Classe Elagage permettant de supprimer les chemins menant
 * vers des noeuds non sélectionnés dans le cablage
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Elagage {
	
	// Le logger
	private Logger LOGGER = LogManager.getLogger(Elagage.class);
		
	// Le cablage original
	private Cablage cablage;
	
	// Le cablage elagué
	private Cablage cablageElague;

	private int nbPassesMaxElagage = 100;

	

	/**
	 * Constructeur de la classe Elagage
	 *
	 * @param cablage le cablage à élaguer
	 */
	public Elagage(Cablage cablage) {
		
		// on sauvegarde le cablage original
		this.cablage = cablage;
		
		// on en fait une copie pour elagage
		this.cablageElague = new Cablage(cablage);
		
		// on elague
		this.elague();
	}
	
	
	/**
	 * Méthode permettant d'élaguer le cablage	 * 
	 */
	private void elague() {
		
		int nbPasses = 0;
		boolean hasElague = true;
		Arete areteASupprimer;
		Sommet origine, extremite;
		HashMap<Sommet, ArrayList<Arete>> areteParSommet = cablageElague.getAretesParSommet();
		
		
		while (hasElague && nbPasses < nbPassesMaxElagage) {
			nbPasses ++;
			hasElague = false;
			
			LOGGER.trace("Phase "+nbPasses+" de l'élagage");
			
			for (Sommet sommet : areteParSommet.keySet()) {
				
				// Si le sommet n'est pas sélectionné
				if (!sommet.getSelected()) {
					
					// S'il est relié au graphe par une seule arete, on peut le supprimer
					if (areteParSommet.get(sommet).size() == 1) {
						
						LOGGER.trace(" -> Sommet inutile relié par une seule arete trouvé : "+sommet);
						
						hasElague = true;
						
						areteASupprimer = areteParSommet.get(sommet).get(0);
						
						// on supprime le sommet inutile
						areteParSommet.remove(sommet);
						
						// on supprime l'arete de la liste des aretes du sommet de l'autre extremite
						origine = areteASupprimer.getSommetOrigine();
						extremite = areteASupprimer.getSommetOrigine();
						
						if (sommet.equals(origine)) {
							
							areteParSommet.get(extremite).remove(areteASupprimer);
							
						} else  {
							
							areteParSommet.get(origine).remove(areteASupprimer);
							
						} 						
					}				
				}				
			}
		}		
	}
	
	public Cablage getChemin() {
		return cablage;
	}
	
	public Cablage getCheminElague() {
		return cablageElague;
	}
}
