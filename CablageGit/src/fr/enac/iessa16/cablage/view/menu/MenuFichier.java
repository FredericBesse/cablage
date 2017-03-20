package fr.enac.iessa16.cablage.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controller;

/**
 * Classe MenuFichier définissant le menu fichier
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class MenuFichier extends JMenu {
	
	/**
	 * 
	 */
	private Controller controller;

	
	/**
	 * Constructeur de la classe MenuFichier à partir du controleur (qui contient les listeners)
	 * 
	 * @param controller le controleur de l'application
	 */
	public MenuFichier(Controller controller) {
		
		// MenuFichier hérite de JMenu donc on appelle le constructeur parent
		super("Fichier");
		
		// On sauvegarde le controleur dans les attributs
		this.controller = controller;
		
		// Création des items du menu
		this.addItems();
	} 
	
	
	/**
	 * Methode définissant les items du menu
	 */
	private void addItems() {
		
		// Item Charger défaut
		JMenuItem loadDefaultGraphJMenuItem = new JMenuItem("Charger défaut"); // Création de l'item
		loadDefaultGraphJMenuItem.setActionCommand("loadDefaultGraph");	// Définition de son actionCommand (pour identifier la source dans le listener)
		this.add(loadDefaultGraphJMenuItem); // ajout de l'item au menu
		loadDefaultGraphJMenuItem.addActionListener(controller.getMenuController()); // ajout du listener à l'item (le listener est dans le controleur)
		
		// Item Charger
		JMenuItem loadJMenuItem = new JMenuItem("Charger...");
		loadJMenuItem.setActionCommand("load");
		this.add(loadJMenuItem);
		loadJMenuItem.addActionListener(controller.getMenuController());
				
		// Item Quitter
		JMenuItem quitJMenuItem = new JMenuItem("Quitter");
		quitJMenuItem.setActionCommand("quit");
		this.add(quitJMenuItem);
		quitJMenuItem.addActionListener(controller.getMenuController());

	}

}
