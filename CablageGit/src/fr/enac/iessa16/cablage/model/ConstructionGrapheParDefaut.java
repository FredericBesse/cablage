package fr.enac.iessa16.cablage.model;

import java.util.ArrayList;
/**
 * Classe ConstructionGrapheParDefaut  permettant de construire un graphe par défaut (pour des tests)
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */

public class ConstructionGrapheParDefaut {
	
	
	
	//attribut graphe de la classe
	private GrapheTheorique graphe;

	
	
	public ConstructionGrapheParDefaut() {
		//super();
		// TODO Auto-generated constructor stub
		
		//Construction de 5 sommets, dont les coordonnees sont fixes.	
		Sommet a = new Sommet(50, 50, "A"); 	
		Sommet b = new Sommet(50,500, "B");
		Sommet c = new Sommet(50,900, "C");
		Sommet d = new Sommet(400,275,"D");
		Sommet e = new Sommet(400,750,"E");
		
		//On cree le tableau de sommets
		ArrayList<Sommet> tableaudeSommet = new ArrayList<Sommet>();
		
		// On ajoute chaque sommet au tableau de sommets
		
		
		tableaudeSommet.add(a);
		tableaudeSommet.add(b);
		tableaudeSommet.add(c);
		tableaudeSommet.add(d);
		tableaudeSommet.add(e);
		
		//Creation de 5 aretes , qui relie donc les sommets prealablement crées.
		Arete arete1 = new Arete(a,b,25);
		Arete arete2 = new Arete(c,d,65);
		Arete arete3 = new Arete(d,e,25);
		Arete arete4 = new Arete(a,c,69);
		Arete arete5 = new Arete(a,d,69);
		
		//On cree le tableau d'aretes
		
		ArrayList<Arete> tableauAretes = new ArrayList<Arete>();
		
		//On ajoute chaque arete au tableau d'aretes.
		
		tableauAretes.add(arete1);
		tableauAretes.add(arete2);
		tableauAretes.add(arete3);
		tableauAretes.add(arete4);
		tableauAretes.add(arete5);
		
		
		
		//on cree l'obje graphe de type GrapheTheorique, il prendra donc en argument le tableau de sommets et le tableau d'aretes prealablement crées.
		
		this.graphe=new GrapheTheorique(tableaudeSommet,tableauAretes);
		
		
	}
	
	
	
	
	
	
	//Getter et Setter du graphe
	public GrapheTheorique getGraphe() {
		return graphe;
	}







	public void setGraphe(GrapheTheorique graphe) {
		this.graphe = graphe;
	}

	/*public void setGraphe(GrapheTheorique graphe) {
		this.graphe = graphe;
	}*/
	
	
	
	
	
	
	

}
