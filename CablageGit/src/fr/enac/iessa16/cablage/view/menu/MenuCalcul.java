package fr.enac.iessa16.cablage.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe définissant le menu Calcul 
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class MenuCalcul extends JMenu {
	

	/**
	 * Constructeur de la classe MenuCalcul
	 *
	 * @param controleur le controleur de l'application
	 */
	public MenuCalcul(Controleur controleur) {
		
		// Création du menu
		super(ParametresFenetre.menuCalcul);
		
		// Item Dijkstra
		JMenuItem optionDjikstra = new JMenuItem(ParametresFenetre.dijkstra);
		optionDjikstra.setActionCommand(ParametresFenetre.dijkstra);
		optionDjikstra.addActionListener(controleur.getControleurMenuCalcul());
		this.add(optionDjikstra);
		
		// Item Kruskal
		JMenuItem optionKruskal = new JMenuItem(ParametresFenetre.kruskal);
		this.add(optionKruskal);
		optionKruskal.setActionCommand(ParametresFenetre.kruskal);
		optionKruskal.addActionListener(controleur.getControleurMenuCalcul());
	
	}	
}
