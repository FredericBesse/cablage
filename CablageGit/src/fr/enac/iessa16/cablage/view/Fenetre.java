package fr.enac.iessa16.cablage.view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Donnees;
import fr.enac.iessa16.cablage.view.graph.Dessin2DGraphePanel;
import fr.enac.iessa16.cablage.view.menu.MenuFichier;


/**
 * Classe View définissant la vue de l'application 
 * 
 * La vue implémente l'interface Observer pour recevoir les
 * notifications de changement du modèle
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Fenetre extends JFrame implements Observer {

	/**
	 * Le modèle de l'application contenant les données à afficher
	 */
	private Donnees model;
	
	/**
	 * Le controller de l'application contenant les listeners
	 */
	private Controleur controller;

	/**
	 * L'objet DrawGraph2DPanel (dérivant de JPanel) permettant d'afficher
	 * le graphe avec la bibliothèque Graplics2D
	 */
	private Dessin2DGraphePanel contenuGraphe ;

	
	/**
	 * Contructeur de la vue à partir d'un modèle et d'un controleur existant
	 * 
	 * @param model le modele 
	 * @param controller le controleur
	 */
	public Fenetre(Donnees model, Controleur controller) {

		// Création de la fenetre principale
		super("Calcul du cablage à cout minimum");
				
		// On récupère le modèle 
		this.model = model;
		
		// On ajoute la vue comme observeur du modèle (pour etre notifier
		// en cas de changement)
		this.model.addObserver(this);
		
		// On récupère le controleur
		this.controller = controller;

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
		this.setPreferredSize(new Dimension(1100, 600));
		
		// Fermeture de l'application lorsqu'on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	
	/**
	 * Methode permettant de créer le menu
	 */
	private void createMenu() {
		
		// Création de la JMenuBar
		JMenuBar jbar = new JMenuBar();
		this.setJMenuBar(jbar);

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
		Container container = this.getContentPane();
		
		// on crée le Panel
		contenuGraphe = new Dessin2DGraphePanel(model,controller);
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
			
		this.pack();
		this.setVisible(true);
	}

}
