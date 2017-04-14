package fr.enac.iessa16.cablage.model;



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


public class KruskalJGrapht {

	
	// Référence vers le graphe du modèle
	private GrapheTheorique graphe;
	
	// Graphe théorique au format JGrapht		
	private SimpleWeightedGraph<Sommet, Arete> graphPourJGrapht = null;
	
	// Objet permettant de calculer Dijkstra sur un graphe JGrapht
	private KruskalMinimumSpanningTree<Sommet, Arete> kruskalShortestPath = null;
		
	
	/**
	 * Constructeur à partir du graphe à afficher
	 * 
	 * @param g le graphe sur lequel l'algorithme sera exécuté
	 */
	public KruskalJGrapht(GrapheTheorique g){	
	
		// Enregistrement d'une référence vers le graphe à afficher
		this.graphe = g;
		
		// Construction du graphPourJGrapht à partir le l'objet graphe donné
        graphPourJGrapht = new SimpleWeightedGraph<Sommet, Arete>(Arete.class);

        // Ajout de tous les sommets au graphe JGrapht
        for (Sommet sommet: graphe.getEnsembleDeSommet()) {
            graphPourJGrapht.addVertex(sommet);
        }
      /*  for (int i = 0; i< graphe.getEnsembleDeSommet().size();i++) {
        	Sommet sommet = graphe.getEnsembleDeSommet().get(i);
            graphPourJGrapht.addVertex(sommet);
        }*/
        
        // Ajout de toutes les aretes au graphe JGrapht
        for (Arete arete : graphe.getEnsembleAretes()) {
            graphPourJGrapht.addEdge(arete.getSommetOrigine(), arete.getSommetExtremité(), arete);
            //graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
        }
	}
	

	/**
	 * Méthode permettant de calculer le chemin le plus court entre le sommet origine et le sommet destination
	 * 
	 * @param origine
	 * @param destination
	 * @return la liste des aretes constituant un chemin le plus court
	 */
	public ArrayList<Arete> getKruskalShortestPath() {

		// Si l'objet permettant le calcul dans JGrapht n'est pas déjà créé, on le crée
        if (kruskalShortestPath == null) {
        	kruskalShortestPath = new KruskalMinimumSpanningTree<Sommet,Arete>(this.graphPourJGrapht);
        }

        // On utilise la méthode fournie par la bibliothèque JGrapht
        SpanningTree<Arete> tree = kruskalShortestPath.getSpanningTree();

        // On renvoie la liste des aretes
        ArrayList<Arete> liste = new ArrayList<Arete>(tree.getEdges());
        return liste;
    }
}

	
	



