package fr.enac.iessa16.cablage.model.algorithm;

import java.util.ArrayList;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import fr.enac.iessa16.cablage.model.core.Arete;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;


/**
 * Classe permettant de calculer le chemin le plus court 
 * entre deux sommets d'un graphe grace à l'algorithme de Dijkstra
 * implémenté dans la bibliothèque JGrapht 
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class AlgoDijkstraJGrapht {
	
	// Référence vers le graphe du modèle
	private GrapheTheorique graphe;
	
	// Graphe théorique au format JGrapht		
	private SimpleWeightedGraph<Sommet, Arete> graphPourJGrapht = null;
	
	// Objet permettant de calculer Dijkstra sur un graphe JGrapht
	private DijkstraShortestPath<Sommet, Arete> dijkstraShortestPath = null;
		
	
	/**
	 * Constructeur à partir du graphe à afficher
	 * 
	 * @param g le graphe sur lequel l'algorithme sera exécuté
	 */
	public AlgoDijkstraJGrapht(GrapheTheorique g){	
	
		// Enregistrement d'une référence vers le graphe à afficher
		this.graphe = g;
		
		// Construction du graphPourJGrapht à partir le l'objet graphe donné
        graphPourJGrapht = new SimpleWeightedGraph<Sommet, Arete>(Arete.class);

        // Ajout de tous les sommets au graphe JGrapht
        for (Sommet sommet: graphe.getListeSommets()) {
            graphPourJGrapht.addVertex(sommet);
        }
              
        // Ajout de toutes les aretes au graphe JGrapht
        for (Arete arete : graphe.getListeAretes()) {
            graphPourJGrapht.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
            // FIXME graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
        }
	}
	

	/**
	 * Méthode permettant de calculer le chemin le plus court entre le sommet origine et le sommet destination
	 * 
	 * @param origine
	 * @param destination
	 * @return la liste des aretes constituant un chemin le plus court
	 */
	public ArrayList<Arete> getDijkstraShortestPath(Sommet origine, Sommet destination) {

		// Si l'objet permettant le calcul dans JGrapht n'est pas déjà créé, on le crée
        if (dijkstraShortestPath == null) {
            dijkstraShortestPath = new DijkstraShortestPath<Sommet, Arete>(graphPourJGrapht);
        }

        // On utilise la méthode fournie par la bibliothèque JGrapht
        GraphPath<Sommet, Arete> path = dijkstraShortestPath.getPath(origine, destination);
       
        // On renvoie la liste des aretes
        // FIXME gerer le cas erreur dijk
        
        ArrayList<Arete> arete = null;
        
        if (path != null)
        	arete = new ArrayList<Arete>(path.getEdgeList());
        
        return arete;
       
    }
}
