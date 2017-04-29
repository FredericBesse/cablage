package fr.enac.iessa16.cablage.model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.SimpleWeightedGraph;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.Cablage;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;


/**
 * Classe permettant de calculer le chemin à cout minimum
 * reliant un sous-ensemble de sommet du graphe grace à
 * l'algorithme de Kruskal implémenté dans JGraphT
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class AlgoKruskalJGrapht {
	
	// Le logger
	private Logger LOGGER = LogManager.getLogger(AlgoKruskalJGrapht.class);
	
	// Le modèle
	private Modele modele;
	
	// Référence vers le graphe du modèle
	private GrapheTheorique graphe;
	
	// Objet permettant de calculer Dijkstra sur un graphe JGrapht
	private AlgoDijkstraJGrapht djikstra;

	// Cablage obtenu par l'algorithme de Kruskal
	private  ArrayList<Arete> listeAretes;

	// FIXME utiliser plutot l'objet cablage pour stocker le résultat
	private Cablage cablage;
	
	
	/**
	 * Constructeur à partir du modèle
	 * 
	 * @param model le modèle de l'application
	 */
	public AlgoKruskalJGrapht(Modele model){	
	
		// Enregistrement d'une référence vers le modèle
		this.modele = model;
		
		// Enregistrement d'une référence vers le graphe du modèle
		this.graphe = model.getGraphe();
		
	    // Création de l'objet permettant le calcul de Dijkstra
        djikstra = new AlgoDijkstraJGrapht(this.graphe);    
        
        this.listeAretes = null;
	}
	
	
	/**
	 * Méthode permettant de calculer le chemin à moindre cout reliant l'ensemble des noeuds sélectionnés
	 * 
	 * @param listeDeSommetsSelectionnés
	 */
	public void calculerKruskal(ArrayList<Sommet> listeDeSommetsSelectionnés) {
		
		// Déclaration des variables locales
		ArrayList<Arete> sousAretes = new ArrayList<Arete>();
		ArrayList<Sommet> sousSommets = new ArrayList<Sommet>();
		SimpleWeightedGraph<Sommet, Arete> sousGraphe = new SimpleWeightedGraph<Sommet, Arete>(Arete.class);
		ArrayList<Arete> chemin;
		int nbNoeudSupplémentaire = 0;
		Sommet sommetI, sommetJ;
		
		// Le sous graphe pour Kruskal contient tous les noeuds sélectionnés
		sousSommets.addAll(listeDeSommetsSelectionnés);
		
		//On calcule Djikstra entre un noeud de depart (indice i ) et toutes les combinaisons possibles (autre noeud commencant à l'indice i+1);
		for(int i = 0 ; i<listeDeSommetsSelectionnés.size(); i++) {
			
			for(int j = i+1 ; j<listeDeSommetsSelectionnés.size(); j++) {
				
				sommetI = listeDeSommetsSelectionnés.get(i);
				sommetJ = listeDeSommetsSelectionnés.get(j);

				// calcul du chemin le plus court entre les sommets i et j
				chemin = this.djikstra.getCheminLePlusCourtDijkstra(sommetI,sommetJ);
				
				// on ajoute toutes les aretes du chemin le plus court aux sous Aretes
				sousAretes.addAll(chemin);
				
				// on parcourt toutes les aretes pour ajouter les sommets supplémentaires (non sélectionné) à la liste des sous sommets
				for (Arete arete : chemin) {
					
					if (!sousSommets.contains(arete.getSommetOrigine())) {
						nbNoeudSupplémentaire++;
						sousSommets.add(arete.getSommetOrigine());
					}
					if (!sousSommets.contains(arete.getSommetExtremité())) {
						nbNoeudSupplémentaire++;
						sousSommets.add(arete.getSommetExtremité());
					}					
				}
			}
		}
		
		if (nbNoeudSupplémentaire > 0) {
			
			modele.message("Algorithme de Kruskal", nbNoeudSupplémentaire+" noeud(s) non sélectionné(s) ont du etre rajouté pour le calcul du chemin à cout minimum");
			
		}
		
		//On cree un sous graphe avec la liste des sommets selectionnés et les sous Aretes.
		//sousGraphe = new GrapheTheorique(sousSommets, sousAretes);
		
		
		 // Ajout de tous les sommets au graphe JGrapht
        for (Sommet sommet : sousSommets) {
            sousGraphe.addVertex(sommet);
        }
        /* 
          Sommet sommet;
          for (int i = 0; i< sousSommets.size();i++) {
    			sommet = sousSommets.get(i);
        		sousGraphe.addVertex(sommet);
    	  }
    	*/
       
        
        // Ajout de toutes les aretes au graphe JGrapht
        for (Arete arete : sousAretes) {
            sousGraphe.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
            //FIXME graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
        }
        
        // Création de l'objet permettant le calcul de Kruskal sur le sous graphe
        KruskalMinimumSpanningTree<Sommet, Arete> kruskalShortestPath = new KruskalMinimumSpanningTree<Sommet,Arete>(sousGraphe);

		//On appelle Kruskal sur ce sous graphe
        SpanningTree<Arete> tree = kruskalShortestPath.getSpanningTree();
        
        // On récupère la liste des aretes
        this.listeAretes = new ArrayList<Arete>(tree.getEdges());
        
        // On crée le cablage correspondant
        this.cablage = new Cablage(listeDeSommetsSelectionnés, sousSommets, sousAretes);
        
        // On élague le graphe si nécessaire
        if (nbNoeudSupplémentaire > 0) {
        	this.elague();
        }
	}
	
	
	/**
	 * Méthode permettant d'élaguer le graphe, ie de supprimer les aretes menant vers
	 * des noeuds non sélectionnés
	 */
	private void elague() {
		
		int nbPasses = 0;
		boolean hasElague = true;
		int nbAreteSupprime = 0;
		int nbNoeudSupprime = 0;
		
		HashMap<Sommet, ArrayList<Arete>> areteParSommet = this.cablage.getAretesParSommet();
		Arete areteASupprimer;
		Sommet origine, extremite;
		
		while (hasElague) {
			nbPasses ++;
			hasElague = false;
			
			LOGGER.trace("Phase "+nbPasses+" de l'élagage");
			
			for (Sommet sommet : areteParSommet.keySet()) {
				
				// Si le sommet n'est pas sélectionné
				if (!sommet.getSelected()) {
					
					// S'il est relié au graphe par une seule arete, on peut le supprimer
					if (areteParSommet.get(sommet).size() == 1) {
						
						LOGGER.trace(" -> Sommet inutile relié par une seule arete trouvé : "+sommet);
						nbAreteSupprime ++;
						
						hasElague = true;
						
						areteASupprimer = areteParSommet.get(sommet).get(0);
						
						// on supprime le sommet inutile
						areteParSommet.remove(sommet);
						cablage.getSommetsUtiles().remove(sommet);
						cablage.getChemin().remove(areteASupprimer);
						
						LOGGER.trace("test size "+cablage.getChemin().size()+" = "+this.listeAretes.size());
						
						nbNoeudSupprime++;
						
						// on supprime l'arete de la liste des aretes du sommet de l'autre extremite
						origine = areteASupprimer.getSommetOrigine();
						extremite = areteASupprimer.getSommetOrigine();
						
						if (sommet.equals(origine)) {
							
							areteParSommet.get(extremite).remove(areteASupprimer);
							
						} else {
							
							areteParSommet.get(origine).remove(areteASupprimer);
							
						} 						
					}					
				}				
			}			
		}
		
		// On informe l'utilisateur si besoin
		if (nbAreteSupprime > 0) {
			
			modele.message("Algorithme de Kruskal", nbNoeudSupprime+" noeud(s) et "+nbAreteSupprime+" aretes inutiles ont été supprimés.");
			
		}		
	}	

	/**
	 * Méthode permettant de récupérer le chemin le plus court entre le sommet origine et le sommet destination
	 * 
	 * @return la liste des aretes constituant un chemin le plus court
	 */
	public  ArrayList<Arete> getKruskalShortestPath() {
        return listeAretes;
    }
}
