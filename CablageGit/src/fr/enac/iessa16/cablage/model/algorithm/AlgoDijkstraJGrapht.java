package fr.enac.iessa16.cablage.model.algorithm;

import java.util.ArrayList;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import fr.enac.iessa16.cablage.model.Modele;
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
		
	// Objet permettant de calculer Dijkstra sur un graphe JGrapht
	private DijkstraShortestPath<Sommet, Arete> dijkstraShortestPath;

	private Modele modele;
		
	
	/**
	 * Constructeur à partir du graphe à afficher
	 * 
	 * @param g le graphe sur lequel l'algorithme sera exécuté
	 * @param m le modele
	 */
	public AlgoDijkstraJGrapht(Modele m, GrapheTheorique g){	
	
		this.modele = m;
		this.graphe = g;
        
        // Création de l'objet Jgrapht permettant de calculer dijkstra
        this.dijkstraShortestPath = new DijkstraShortestPath<Sommet,Arete>(graphe);
	}        
		

	/**
	 * Méthode permettant de calculer le chemin le plus court entre le sommet origine et le sommet destination
	 * 
	 * @param origine de l'arete
	 * @param destination de l'arete
	 * @return la liste des aretes constituant un chemin le plus court
	 */
	public ArrayList<Arete> getCheminLePlusCourtDijkstra(Sommet origine, Sommet destination) {

        // On utilise la méthode fournie par la bibliothèque JGrapht
        GraphPath<Sommet, Arete> path = dijkstraShortestPath.getPath(origine, destination);

        // On renvoie la liste des aretes        
        ArrayList<Arete> listeAretesDijkstra = new ArrayList<Arete>();
        
        if (path != null) {
          	listeAretesDijkstra.addAll(path.getEdgeList());
        } else {
        	modele.erreur("Dijkstra", "Pas de chemin trouvé entre "+origine.getId()+" et "+destination.getId());
        }
        
        return listeAretesDijkstra;   
    }
}
