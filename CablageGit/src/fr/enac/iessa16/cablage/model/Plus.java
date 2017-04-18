package fr.enac.iessa16.cablage.model;

import java.util.ArrayList;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;
public class Plus {

	
	
	
	private GrapheTheorique graphe;
	
	// Graphe théorique au format JGrapht		
	private SimpleWeightedGraph<Sommet, Arete> graphPourJGrapht = null;
	private AStarShortestPath<Sommet, Arete> AStarShortestPath = null;
	
	public Plus(GrapheTheorique graphe) {

	GrapheTheorique g = null;
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
        // FIXME graphPourJGrapht.setEdgeWeight(arete, arete.getCout());
    }}
	

	 
		/*public ArrayList<Arete> getDijkstraShortestPath(Sommet origine, Sommet destination) {

			// Si l'objet permettant le calcul dans JGrapht n'est pas déjà créé, on le crée
	        if (dijkstraShortestPath == null) {
	            dijkstraShortestPath = new DijkstraShortestPath<Sommet, Arete>(graphPourJGrapht);
	        }

	        // On utilise la méthode fournie par la bibliothèque JGrapht
	        GraphPath<Sommet, Arete> path = dijkstraShortestPath.getPath(origine, destination);
	       
	        // On renvoie la liste des aretes
	        ArrayList<Arete> arete = new ArrayList<Arete>(path.getEdgeList());
	        return arete;
	       
	    }*/
		
		
		public ArrayList<Arete> getShortestPath(Sommet origine,Sommet destination, AStarAdmissibleHeuristic<Sommet> admissibleHeuristic)
		{
			
			 if (AStarShortestPath == null) {
				 AStarShortestPath = new AStarShortestPath<Sommet, Arete>(graphPourJGrapht, admissibleHeuristic);
			
			 }

		        // On utilise la méthode fournie par la bibliothèque JGrapht
		        GraphPath<Sommet, Arete> path = AStarShortestPath.getPath(origine, destination);
		       
		        // On renvoie la liste des aretes
		        ArrayList<Arete> arete = new ArrayList<Arete>(path.getEdgeList());
		       System.out.println("le chemin le plus court "+ arete);
		        return arete;
		
			
			
			
		}



		public AStarShortestPath<Sommet, Arete> getAStarShortestPath() {
			return AStarShortestPath;
		}



		public void setAStarShortestPath(AStarShortestPath<Sommet, Arete> aStarShortestPath) {
			AStarShortestPath = aStarShortestPath;
		}



}


	
	
	
		

