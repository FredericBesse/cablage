package fr.enac.iessa16.cablage.model;


import java.util.Observable;

import fr.enac.iessa16.cablage.builder.DefaultGraphBuilder;
import fr.enac.iessa16.cablage.model.graph.Graphe;

/**
 * Classe Model contenant les données utiles à afficher
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Model extends Observable {

	
	/**
	 * Le graphe à afficher
	 */
	private Graphe graphe;
	
	
	/**
	 * Constructeur de la classe Model
	 */
	public Model() {
		
		// Attention, par défaut, le graphe n'est pas créé 
		this.graphe = null;
		
		// FIXME : il faudrait peut etre plutot créé un graphe vide...
		
	}
	
	
	/**
	 * @return le graphe utile
	 */
	public Graphe getGraph() {
		// TODO Auto-generated method stub
		return graphe;
	}

	
	/**
	 * Méthode appelé lors d'un clic sur le bouton "Charger graphe par défaut", permettant de
	 * construire un graphe par défaut et de mettre à jour le modèle
	 */
	public void loadDefaultGraph() {
		
		// Création d'un objet de type DefaultGraphBuilder pour construire le graphe par défaut
		DefaultGraphBuilder defaultGraphBuilder = new DefaultGraphBuilder();
				
		// Mise à jour du graphe du modèle avec le graphe par défaut précédemment construit
		this.graphe = defaultGraphBuilder.getGraphe();
		
		// Notification de la vue que le modèle a changé
		this.setChanged();
		this.notifyObservers();	
	}
	

	/**
	 * Méthode appelé lors d'un clique souris sur la fenetre
	 * 
	 * @param x la position x du clic souris (pixel)
	 * @param y la position y du clic souris (pixel)
	 */
	public void cliqueFenetreGraphe(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("Model : clique "+x+" "+y);
		
	}
}
