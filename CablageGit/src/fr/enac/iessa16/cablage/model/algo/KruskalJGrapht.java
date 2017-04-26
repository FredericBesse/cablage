package fr.enac.iessa16.cablage.model.algo;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.Cablage;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;


public class KruskalJGrapht {
	
	// Référence vers le modèle
	private DonneesAAfficher model;
	
	// Référence vers le graphe du modèle
	private GrapheTheorique graphe;
	
	// Objet permettant de calculer Dijkstra sur un graphe JGrapht
	private DijkstraJGrapht djikstra;

	// Cablage obtenu par l'algorithme de Kruskal
	private  ArrayList<Arete> cablage;
	
	
	/**
	 * Constructeur à partir du modèle
	 * 
	 * @param model le modèle de l'application
	 */
	public KruskalJGrapht(DonneesAAfficher model){	
	
		// Enregistrement d'une référence vers le modèle
		this.model = model;
		
		// Enregistrement d'une référence vers le graphe du modèle
		this.graphe = model.getGrapheàafficher();
		
	    // Création de l'objet permettant le calcul de Dijkstra
        djikstra = new DijkstraJGrapht(this.graphe);    
        
        this.cablage = null;
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
		int nbNoeudInutile = 0;
		Sommet sommetI, sommetJ;
		
		// Le sous graphe pour Kruskal contient tous les noeuds sélectionnés
		sousSommets.addAll(listeDeSommetsSelectionnés);
		
		//On calcule Djikstra entre un noeud de depart (indice i ) et toutes les combinaisons possibles (autre noeud commencant à l'indice i+1);
		for(int i = 0 ; i<listeDeSommetsSelectionnés.size(); i++) {
			
			for(int j = i+1 ; j<listeDeSommetsSelectionnés.size(); j++) {
				
				sommetI = listeDeSommetsSelectionnés.get(i);
				sommetJ = listeDeSommetsSelectionnés.get(j);

				// calcul du chemin le plus court entre les sommets i et j
				chemin = this.djikstra.getDijkstraShortestPath(sommetI,sommetJ);
				
				// on ajoute toutes les aretes du chemin le plus court aux sous Aretes
				sousAretes.addAll(chemin);
				
				// on parcourt toutes les aretes pour ajouter les sommets supplémentaires (non sélectionné) à la liste des sous sommets
				for (Arete arete : chemin) {
					
					if (!sousSommets.contains(arete.getSommetOrigine())) {
						nbNoeudInutile++;
						sousSommets.add(arete.getSommetOrigine());
					}
					if (!sousSommets.contains(arete.getSommetExtremité())) {
						nbNoeudInutile++;
						sousSommets.add(arete.getSommetExtremité());
					}					
				}
			}
		}
		
		if (nbNoeudInutile != 0) {
			
			model.message("Algorithme de Kruskal", nbNoeudInutile+" noeud(s) non sélectionné(s) ont du etre rajouté pour le calcul du chemin à cout minimum");
			
		}
		
		//On cree un sous graphe avec la liste des sommets selectionnés et les sous Aretes.
		//sousGraphe = new GrapheTheorique(sousSommets, sousAretes);
		
		
		 // Ajout de tous les sommets au graphe JGrapht
        for (Sommet sommet: sousSommets) {
            sousGraphe.addVertex(sommet);
        }
      /*  for (int i = 0; i< graphe.getEnsembleDeSommet().size();i++) {
        	Sommet sommet = graphe.getEnsembleDeSommet().get(i);
            graphPourJGrapht.addVertex(sommet);
        }*/
        
        // Ajout de toutes les aretes au graphe JGrapht
        for (Arete arete : sousAretes) {
            sousGraphe.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
            //graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
        }
        
        // Création de l'objet permettant le calcul de Kruskal sur le sous graphe
        KruskalMinimumSpanningTree kruskalShortestPath = new KruskalMinimumSpanningTree<Sommet,Arete>(sousGraphe);

		//On appelle Kruskal sur ce sous graphe
        SpanningTree<Arete> tree = kruskalShortestPath.getSpanningTree();

        // On récupère la liste des aretes
        this.cablage = new ArrayList<Arete>(tree.getEdges());
     
		
	}
	

	/**
	 * Méthode permettant de calculer le chemin le plus court entre le sommet origine et le sommet destination
	 * 
	 * @param origine
	 * @param destination
	 * @return la liste des aretes constituant un chemin le plus court
	 */
	public  ArrayList<Arete> getKruskalShortestPath() {
        return cablage;
    }



	
}

	
	



