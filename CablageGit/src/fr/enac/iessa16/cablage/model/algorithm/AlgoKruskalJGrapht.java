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
	private Cablage cablageKruskal;
	
	
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
		this.djikstra = new AlgoDijkstraJGrapht(model,this.graphe);    
        
		// Initialisation du cablage
        this.cablageKruskal = null;
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
			
			// FIXME modele.message("Algorithme de Kruskal", nbNoeudSupplémentaire+" noeud(s) non sélectionné(s) ont du etre rajouté pour le calcul du chemin à cout minimum");
			
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
            sousGraphe.setEdgeWeight(arete, arete.getCout());
        }
        
        // Création de l'objet permettant le calcul de Kruskal sur le sous graphe
        KruskalMinimumSpanningTree<Sommet, Arete> kruskalShortestPath = new KruskalMinimumSpanningTree<Sommet,Arete>(sousGraphe);

		//On appelle Kruskal sur ce sous graphe
        SpanningTree<Arete> tree = kruskalShortestPath.getSpanningTree();
        
        // On récupère la liste des aretes
        ArrayList<Arete> listeAretesKruskal = new ArrayList<Arete>(tree.getEdges());
        
        // On crée le cablage correspondant
        this.cablageKruskal = new Cablage(listeDeSommetsSelectionnés, sousSommets, listeAretesKruskal);
        
        
        
        
        ArrayList<Sommet> test = modele.getListeSommetsSelectionnes();
        ArrayList<Arete> testsarete;
		for (Sommet sommet : test) {
			LOGGER.info("noeud selectionne : "+sommet.getId());
			
			testsarete = cablageKruskal.getAretesParSommet().get(sommet);
			
			for (Arete arete : testsarete) {
				LOGGER.info("   -> arete : "+arete.getSommetOrigine().getId()+" -> "+arete.getSommetExtremité().getId());
			}
			
		}
        
        
        
		for (Sommet sommet : listeDeSommetsSelectionnés) {
			LOGGER.info("noeud selectionne : "+sommet.getId());
			
			testsarete = cablageKruskal.getAretesParSommet().get(sommet);
			
			for (Arete arete : testsarete) {
				LOGGER.info("   -> arete : "+arete.getSommetOrigine().getId()+" -> "+arete.getSommetExtremité().getId());
			}
			
		}
        
        
        
        // On élague le graphe si nécessaire
        if (nbNoeudSupplémentaire > 0) {
        	this.elague();
        }
        
        
        for (Sommet sommet : listeDeSommetsSelectionnés) {
			LOGGER.info("apres elagage noeud selectionne : "+sommet.getId());
			
			testsarete = cablageKruskal.getAretesParSommet().get(sommet);
			
			for (Arete arete : testsarete) {
				LOGGER.info("   -> arete : "+arete.getSommetOrigine().getId()+" -> "+arete.getSommetExtremité().getId());
			}
			
		}
        
        // FIXME modele.message("kruskal", "nb aretes="+listeAretesKruskal.size()+"  cout="+cablageKruskal.getCout());
	}
	
	
	/**
	 * Méthode permettant d'élaguer le graphe, ie de supprimer les aretes menant vers
	 * des noeuds non sélectionnés
	 */
	private void elague() {
		
		int nbPasses = 0;
		boolean hasElague = true;
		int nbAreteSupprime = 0;
		int nbNoeudSupprimePasse = 0;
		int nbNoeudSupprimeTotal = 0;
		
		ArrayList<Sommet> sommetsASupprimer = new ArrayList<Sommet>();
		ArrayList<Arete> aretesASupprimer = new ArrayList<Arete>();
		
		HashMap<Sommet, ArrayList<Arete>> areteParSommet = this.cablageKruskal.getAretesParSommet();
		Arete areteASupprimer;
		
		Sommet origine, extremite;
		
		
		
		
		while (hasElague && nbPasses < 100) {
			nbPasses ++;
			nbNoeudSupprimePasse = 0;
			hasElague = false;
			
			LOGGER.trace("Phase "+nbPasses+" de l'élagage nbSommet="+areteParSommet.keySet().size());
			
			for (Sommet sommet : areteParSommet.keySet()) {
				
				// Si le sommet n'est pas sélectionné
				if (!sommet.getSelected()) {
					
					//LOGGER.trace("som non sel "+sommet.getId()+" "+areteParSommet.get(sommet).size());
					
					// S'il est relié au graphe par une seule arete, on peut le supprimer
					if (areteParSommet.get(sommet).size() == 1) {
						
						
												
						hasElague = true;
												
						// on ajoute l'arete dans la liste des aretes a supprimer
						areteASupprimer = areteParSommet.get(sommet).get(0);
						aretesASupprimer.add(areteASupprimer);
						nbAreteSupprime++;
						
						// on ajoute l'arete dans la liste des aretes a supprimer
						sommetsASupprimer.add(sommet);
						nbNoeudSupprimePasse++;
						nbNoeudSupprimeTotal++;
						
						//LOGGER.trace("    -> Sommet inutile :"+sommet.getId()+" arete "+areteASupprimer.getSommetOrigine().getId()+"->"+areteASupprimer.getSommetExtremité().getId());
									
					}					
				}				
			}
			
			
			// on supprime l'arete de la liste des aretes du sommet de l'autre extremite
			for (Arete arete : aretesASupprimer) {
				
				origine = arete.getSommetOrigine();
				extremite = arete.getSommetExtremité();
	
				//LOGGER.trace("        -> ext:"+extremite.getId()+" avant:"+areteParSommet.get(extremite).size());
				areteParSommet.get(extremite).remove(arete);
				//LOGGER.trace("        -> ext:"+extremite.getId()+" apres:"+areteParSommet.get(extremite).size());
				
				//LOGGER.trace("        -> ori:"+origine.getId()+" avant:"+areteParSommet.get(origine).size());
				areteParSommet.get(origine).remove(arete);
				//LOGGER.trace("        -> ori:"+origine.getId()+" apres:"+areteParSommet.get(origine).size());
				
				
				if(origine.getSelected()) {
					LOGGER.trace("        -> ORIGINE="+origine.getId()+" -> "+extremite.getId());
				}
				if(extremite.getSelected()) {
					LOGGER.trace("        -> "+origine.getId()+" -> EXTREMITE"+extremite.getId());
				}
				
				
				cablageKruskal.getAretes().remove(arete);
			}
			aretesASupprimer.clear();
			
			
			LOGGER.trace("nbNoeudSupprimePasse="+nbNoeudSupprimePasse);
			for (Sommet sommet : sommetsASupprimer) {
				
				if (sommet.getSelected() || areteParSommet.get(sommet).size()>0)
					LOGGER.info("ERRRROOOOOOOORRRRR   Suppression du sommet "+sommet.getId()+" nbAretes="+areteParSommet.get(sommet).size());
				areteParSommet.remove(sommet);
				cablageKruskal.getSommetsUtiles().remove(sommet);
				//nbNoeudSupprime++;
			}
			sommetsASupprimer.clear();
						
		}
		
		
		
		
		
		// On informe l'utilisateur si besoin
		if (nbAreteSupprime > 0) {
			modele.message("Algorithme de Kruskal", nbNoeudSupprimeTotal+" noeud(s) et "+nbAreteSupprime+" arete(s) inutile(s) ont été supprimés en "+nbPasses+" passes.");
		}		
	}	

	
	/**
	 * Méthode permettant de récupérer le cablage le plus court 
	 * @return
	 */
	public Cablage getCablageLePlusCourtKruskal() {
        return cablageKruskal;
    }
}
