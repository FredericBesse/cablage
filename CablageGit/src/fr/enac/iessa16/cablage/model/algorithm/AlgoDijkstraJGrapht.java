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
	
	// Attribut graphe du modèle
	private GrapheTheorique graphe;
	
	// Graphe théorique au format JGrapht		
	private SimpleWeightedGraph<Sommet, Arete> graphPourJGrapht;
	
	// Objet permettant de calculer Dijkstra sur un graphe JGrapht
	private DijkstraShortestPath<Sommet, Arete> dijkstraShortestPath;
		
	
	/**
	 * Constructeur à partir du graphe à afficher
	 * 
	 * @param g le graphe sur lequel l'algorithme sera exécuté
	 */
	public AlgoDijkstraJGrapht(GrapheTheorique g){	
	

		this.graphe = g;
	
		// Construction du graphPourJGrapht à partir le l'objet graphe donné
        graphPourJGrapht = new SimpleWeightedGraph<Sommet, Arete>(Arete.class);
	  
        // Ajout de tous les sommets au graphe JGrapht
        for (Sommet sommet: graphe.getListeSommets()) {
            graphPourJGrapht.addVertex(sommet);
        }
        /* equivalent à
     	Sommet sommet1 ;
     	int size = graphe.getListeSommets().size();
      
       	for(int i = 0; i< size ; i++) {
        	sommet1 = graphe.getListeSommets().get(i);
        	graphPourJGrapht.addVertex(sommet1);	
        }	
        */        
              
        // Ajout de toutes les aretes au graphe JGrapht
        for (Arete arete : graphe.getListeAretes()) {
            graphPourJGrapht.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
            // FIXME graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
        }
        /* equivalent à :
        Arete arete;
        size = graphe.getListeAretes().size();
        
        for(int i=0 ; i<size ; i++) {
        	arete = graphe.getListeAretes().get(i);
        	graphPourJGrapht.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
        }
        */
        
        // Création de l'objet Jgrapht permettant de calculer dijkstra
        this.dijkstraShortestPath = new DijkstraShortestPath<Sommet,Arete>(graphPourJGrapht);
	}        
		

	/**
	 * Méthode permettant de calculer le chemin le plus court entre le sommet origine et le sommet destination
	 * 
	 * @param origine
	 * @param destination
	 * @return la liste des aretes constituant un chemin le plus court
	 */
	public ArrayList<Arete> getCheminLePlusCourtDijkstra(Sommet origine, Sommet destination) {

        // On utilise la méthode fournie par la bibliothèque JGrapht
        GraphPath<Sommet, Arete> path = dijkstraShortestPath.getPath(origine, destination);
       
        // On renvoie la liste des aretes
        // FIXME gerer le cas erreur dijk
        
        ArrayList<Arete> listeAretesDijkstra = null;
        
        if (path != null) {
        	
        	listeAretesDijkstra = new ArrayList<Arete>();
        	listeAretesDijkstra.addAll(path.getEdgeList());

        }      	
        
        return listeAretesDijkstra;
    }
}
