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
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;


public class KruskalJGrapht {

	
	// Référence vers le graphe du modèle
	private GrapheTheorique graphe;
	
	// Graphe théorique au format JGrapht		
	private SimpleWeightedGraph<Sommet, Arete> graphPourJGrapht = null;
	
	// Objet permettant de calculer Dijkstra sur un graphe JGrapht
	private KruskalMinimumSpanningTree<Sommet, Arete> kruskalShortestPath = null;

	private DijkstraJGrapht djikstra;

	private DonneesAAfficher model;
		
	
	/**
	 * Constructeur à partir du graphe à afficher
	 * 
	 * @param g le graphe sur lequel l'algorithme sera exécuté
	 */
	public KruskalJGrapht(DonneesAAfficher model, GrapheTheorique g){	
	
		this.model = model;
		
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
        
     // Création de l'objet permettant le calcul de Dijkstra
        djikstra = new DijkstraJGrapht(this.graphe);
	}
	
	
	public void calculerKruskal(ArrayList<Sommet> listeDeSommetsSelectionnés) {
		// TODO Auto-generated method stub
		
	
	
		// TODO Auto-generated method stub
		ArrayList<Arete> sousAretes = new ArrayList<Arete>();
		ArrayList<Sommet> sousSommets = new ArrayList<Sommet>();
		GrapheTheorique sousGraphe;
		ArrayList<Arete> chemin;
		
		int nbNoeudInutile = 0;
		
		// Le sous graphe pour Kruskal contient tous les noeuds sélectionnés
		sousSommets.addAll(listeDeSommetsSelectionnés);
		
		
		
		//On calcule Djikstra entre un noeud de depart (indice i ) et toutes les combinaisons possibles (autre noeud commencant à l'indice i+1);
		for(int i = 0 ; i<listeDeSommetsSelectionnés.size(); i++)
		{
			for(int j = i+1 ; j<listeDeSommetsSelectionnés.size(); j++) 
			{

				chemin = this.djikstra.getDijkstraShortestPath(listeDeSommetsSelectionnés.get(i),listeDeSommetsSelectionnés.get(j));
				
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
		sousGraphe = new GrapheTheorique(sousSommets, sousAretes);

		//On appelle Kruskal sur ce sous graphe
		this.kruskal = new KruskalJGrapht(sousGraphe);
		this.listearetesCoresspondantauCheminLeplusCourtKruskal = this.kruskal.getKruskalShortestPath();
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

	
	



