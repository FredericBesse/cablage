package fr.enac.iessa16.cablage.main;
import java.util.ArrayList;

import fr.enac.iessa16.cablage.controller.Controller;
import fr.enac.iessa16.cablage.model.Model;
import fr.enac.iessa16.cablage.model.graph.Arete;
import fr.enac.iessa16.cablage.model.graph.Graphe;
import fr.enac.iessa16.cablage.model.graph.Sommet;
import fr.enac.iessa16.cablage.view.View;


public class RunCablageApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
					
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(model,controller);
		view.start();
		
		
		
		

	}

}
