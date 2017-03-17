package fr.enac.iessa16.cablage.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controller;

public class MenuFichier extends JMenu {
	
	private Controller controller;

	public MenuFichier(Controller controller) {
		
		super("Fichier");
		
		this.controller = controller;
		
		this.addItems();
	} 
	
	
	private void addItems() {
		
		JMenuItem loadDefaultGraphJMenuItem = new JMenuItem("Charger défaut");
		loadDefaultGraphJMenuItem.setActionCommand("loadDefaultGraph");
		this.add(loadDefaultGraphJMenuItem);
		loadDefaultGraphJMenuItem.addActionListener(controller.getMenuController());
		
		JMenuItem loadJMenuItem = new JMenuItem("Charger...");
		loadJMenuItem.setActionCommand("load");
		this.add(loadJMenuItem);
		loadJMenuItem.addActionListener(controller.getMenuController());
		
		
		
		JMenuItem quitJMenuItem = new JMenuItem("Quitter");
		quitJMenuItem.setActionCommand("quit");
		this.add(quitJMenuItem);
		quitJMenuItem.addActionListener(controller.getMenuController());

	}

}
