package fr.enac.iessa16.cablage.builder;

import java.util.ArrayList;

import fr.enac.iessa16.cablage.model.graph.Arete;
import fr.enac.iessa16.cablage.model.graph.Graphe;
import fr.enac.iessa16.cablage.model.graph.Sommet;

public class DefaultGraphBuilder {

	private Graphe graphe;

	public DefaultGraphBuilder() {
		
		this.graphe = null;
	
	}
	
	public void buildGraph() {
	

		Sommet a = new Sommet(50, 50, "A");
		Sommet b = new Sommet(50, 500, "B");
		Sommet c = new Sommet(50, 900, "C");
		Sommet d = new Sommet(400, 275, "D");
		Sommet e = new Sommet(400, 750, "E");

		ArrayList<Sommet> sommets = new ArrayList<Sommet>();
		sommets.add(a);
		sommets.add(b);
		sommets.add(c);
		sommets.add(d);
		sommets.add(e);

		Arete arete1 = new Arete(a, b, 1);
		Arete arete2 = new Arete(c, d, 1);
		Arete arete3 = new Arete(d, e, 1);
		Arete arete4 = new Arete(a, c, 1);
		Arete arete5 = new Arete(a, d, 1);

		ArrayList<Arete> listeAretes = new ArrayList<>();
		listeAretes.add(arete1);
		listeAretes.add(arete2);
		listeAretes.add(arete3);
		listeAretes.add(arete4);
		listeAretes.add(arete5);

		this.graphe = new Graphe(sommets, listeAretes);
	}

	public Graphe getGraphe() {
		return graphe;
	}

	

}
