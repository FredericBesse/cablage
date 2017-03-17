package fr.enac.iessa16.cablage.model;


import java.util.Observable;

import fr.enac.iessa16.cablage.builder.DefaultGraphBuilder;
import fr.enac.iessa16.cablage.model.graph.Graphe;

public class Model extends Observable {

	
	private Graphe graphe;
	
	
	public Model() {
		
		this.graphe = null;
		
	}

	public void loadDefaultGraph() {
		
		DefaultGraphBuilder defaultGraphBuilder = new DefaultGraphBuilder();
		defaultGraphBuilder.buildGraph(); // construction du graphe
		this.graphe = defaultGraphBuilder.getGraphe();
		
		this.setChanged();
		this.notifyObservers();
	
	}

	public Graphe getGraph() {
		// TODO Auto-generated method stub
		return graphe;
	}

	public void cliqueFenetreGraphe(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("Model : clique "+x+" "+y);
		
		
		
	}

	

}
