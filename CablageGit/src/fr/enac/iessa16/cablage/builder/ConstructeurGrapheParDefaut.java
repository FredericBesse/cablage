package fr.enac.iessa16.cablage.builder;

import java.util.ArrayList;

import fr.enac.iessa16.cablage.model.graph.Arete;
import fr.enac.iessa16.cablage.model.graph.Graphe;
import fr.enac.iessa16.cablage.model.graph.Sommet;

/**
 * Classe DefaultGraphBuilder permettant de construire un graphe par défaut (pour des tests)
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ConstructeurGrapheParDefaut {

	// L'objet graphe construit par la classe
	private Graphe _graphe;

	
	/**
	 * Constructeur de la classe DefaultGraphBuilder 
	 */
	public ConstructeurGrapheParDefaut() {
		
		// Construction de la liste des sommets
		ArrayList<Sommet> sommets = new ArrayList<Sommet>();
		
		// Construction des sommets		
		Sommet a = new Sommet(50, 50, "A");
		Sommet b = new Sommet(50, 500, "B");
		Sommet c = new Sommet(50, 900, "C");
		Sommet d = new Sommet(400, 275, "D");
		Sommet e = new Sommet(400, 750, "E");

		// Ajout des sommets à la liste des sommets
		sommets.add(a);
		sommets.add(b);
		sommets.add(c);
		sommets.add(d);
		sommets.add(e);

		// Construction de la liste des aretes
		ArrayList<Arete> listeAretes = new ArrayList<>();
		
		// Construction des aretes
		Arete arete1 = new Arete(a, b, 1);
		Arete arete2 = new Arete(c, d, 1);
		Arete arete3 = new Arete(d, e, 1);
		Arete arete4 = new Arete(a, c, 1);
		Arete arete5 = new Arete(a, d, 1);

		// Ajout des aretes à la liste des aretes
		listeAretes.add(arete1);
		listeAretes.add(arete2);
		listeAretes.add(arete3);
		listeAretes.add(arete4);
		listeAretes.add(arete5);

		// Création du graphe à partir de la liste des sommets et de la liste des aretes		
		this._graphe = new Graphe(sommets, listeAretes);
	}

	
	/**
	 * @return le graphe construit par le constructeur de graphe par defaut
	 */
	public Graphe getGraphe() {
		return _graphe;
	}

	

}
