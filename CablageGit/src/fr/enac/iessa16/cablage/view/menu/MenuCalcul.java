package fr.enac.iessa16.cablage.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.Parametres;

/**
 * Classe définissant le menu Calcul 
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class MenuCalcul extends JMenu {
	

	private Controleur controleur;
	private JMenuItem optionDjikstra;
	private JMenuItem optionKruskal;

	/**
	 * Constructeur de la classe MenuCalcul
	 *
	 * @param controleur le controleur de l'application
	 */
	public MenuCalcul(Controleur controleur) {
		
		// Création du menu
		super(Parametres.menuCalcul);
		
		// on récupère le controleur
		this.controleur = controleur;
				
		// on crée les items
		this.creerItems();
	}
			
	private void creerItems() {
		
		// Item Dijkstra
		optionDjikstra = new JMenuItem(Parametres.dijkstra);
		optionDjikstra.setActionCommand(Parametres.dijkstra);
		optionDjikstra.addActionListener(controleur.getControleurMenuCalcul());
		optionDjikstra.setEnabled(false);
		this.add(optionDjikstra);
		
		// Item Kruskal
		optionKruskal = new JMenuItem(Parametres.kruskal);
		optionKruskal.setActionCommand(Parametres.kruskal);
		optionKruskal.setEnabled(false);
		optionKruskal.addActionListener(controleur.getControleurMenuCalcul());
		this.add(optionKruskal);
	}

	public void updateNouveauGraphe() {
		optionDjikstra.setEnabled(true);
		optionKruskal.setEnabled(true);
	}

	public void updateFermerGraphe() {
		optionDjikstra.setEnabled(false);
		optionKruskal.setEnabled(false);
	}	
}
