package fr.enac.iessa16.cablage.view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controleur;

public class MenuCalcul extends JMenu {
	
	private Controleur controleur;
	
	
	

	public MenuCalcul(Controleur controleur) {
		super("Calcul");
		
	
		JMenuItem optionDjikstra = new JMenuItem("Calculer Djikstra");
		this.add(optionDjikstra);
		optionDjikstra.setActionCommand("CalculerDjikstra");
		optionDjikstra.addActionListener(controleur.getControleCalcul());
		JMenuItem optionKruskal = new JMenuItem("Calculer Kruskal");
		this.add(optionKruskal);
		optionKruskal.setActionCommand("CalculerKruskal");
		optionKruskal.addActionListener(controleur.getControleCalcul());
		
		//this.jbar.add(menuCalcul);
	
		
		
		
		
		
	}

	
	
	
	
	
	
}
