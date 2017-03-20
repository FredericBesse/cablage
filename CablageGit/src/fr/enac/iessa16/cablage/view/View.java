package fr.enac.iessa16.cablage.view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controller;
import fr.enac.iessa16.cablage.model.Model;
import fr.enac.iessa16.cablage.view.graph.DrawGraph2DPanel;
import fr.enac.iessa16.cablage.view.menu.MenuFichier;


/**
 * Classe View définissant la vue de l'application 
 * 
 * La vue implémente l'interface Observer pour recevoir les
 * notifications de changement du modèle
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class View implements Observer {

	/**
	 * Le modèle de l'application contenant les données à afficher
	 */
	private Model model;
	
	/**
	 * Le controller de l'application contenant les listeners
	 */
	private Controller controller;

	/**
	 * La fenetre principale
	 */
	private JFrame mainJFrame;
	
	/**
	 * L'objet DrawGraph2DPanel (dérivant de JPanel) permettant d'afficher
	 * le graphe avec la bibliothèque Graplics2D
	 */
	private DrawGraph2DPanel contenuGraphe ;

	
	/**
	 * Contructeur de la vue à partir d'un modèle et d'un controleur existant
	 * 
	 * @param model le modele 
	 * @param controller le controleur
	 */
	public View(Model model, Controller controller) {

		// On récupère le modèle 
		this.model = model;
		
		// On ajoute la vue comme observeur du modèle (pour etre notifier
		// en cas de changement)
		this.model.addObserver(this);
		
		// On récupère le controleur
		this.controller = controller;

		// Création de la fenetre principale
		this.mainJFrame = new JFrame("Calcul du cablage à cout minimum");

		// Initialisation de la fenetre
		this.init();
		
		// Création du menu
		this.createMenu();
		
		// Création du panneau permettant l'affichage du graphe
		this.createGraphPanel();
	}
	

	/**
	 * Methode regroupant les initialisations de la fenetre
	 */
	private void init() {
		
		// Définition de la taille de la fenetre
		mainJFrame.setPreferredSize(new Dimension(1100, 600));
		
		// Fermeture de l'application lorsqu'on clique sur la croix
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	
	/**
	 * Methode permettant de créer le menu
	 */
	private void createMenu() {
		
		// Création de la JMenuBar
		JMenuBar jbar = new JMenuBar();
		mainJFrame.setJMenuBar(jbar);

		// Création du JMenu Fichier
		MenuFichier jmenu = new MenuFichier(controller);
		jbar.add(jmenu);

		/*JMenu menuFichier = new JMenu("Fichier");
		jbar.add(menuFichier);*/
		
	}
	
	
	/**
	 * Méthode permettant la création du panneau affichant le graphe
	 */
	private void createGraphPanel() {
		
		// on r�cup�re le container de la fen�tre :
		Container container = mainJFrame.getContentPane();
		
		// on crée le Panel
		contenuGraphe = new DrawGraph2DPanel(model,controller);
		contenuGraphe.setPreferredSize(new Dimension(1100,600));
		
		// Ajout du Panel au container de la fenetre
		container.add(contenuGraphe);
	}

	/* (non-Javadoc)
	 * 
	 * Méthode appelée en cas de modification du modèle (grace à la fonction notify)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		System.out.println("View : Model updated");
		
		// On force le réaffichage de la vue
		contenuGraphe.repaint();
		
	}

	/**
	 * Méthode permettant l'affichage de la fenetre
	 */
	public void start() {
			
		mainJFrame.pack();
		mainJFrame.setVisible(true);
	}

}
