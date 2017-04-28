package fr.enac.iessa16.cablage.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.menu.BarreOutils;
import fr.enac.iessa16.cablage.view.menu.MenuAide;
import fr.enac.iessa16.cablage.view.menu.MenuCalcul;
import fr.enac.iessa16.cablage.view.menu.MenuEdition;
import fr.enac.iessa16.cablage.view.menu.MenuFichier;

/**
 * Classe Fenetre qui correspond à la vue.
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Observer {

	private Controleur controleur;
	private PanneauDessinGraphe panneauDessin;
	private PanneauParametres panneauParametres;
	private Modele modele;

	/**
	 * Constructeur de la classe Fenetre.java
	 * 
	 * @param controleur
	 * @param model
	 */
	public Fenetre(Controleur controleur, Modele modele) {

		super();
		
		this.controleur = controleur;
		this.modele = modele;
		
		// Ajout de la fenetre comme observateur du modele
		this.modele.addObserver(this);
		
		calculerTailleComposants();
				
		creerContenuFenetre();
		
		proprieteFenetre();
		
		this.setVisible(true);
	}
	
	

	/**
	 * Méthode permettant de créer le contenu de la fenetre  
	 */
	private void creerContenuFenetre() {
		
		// Création du menu
		creerMenu();
		
		// Création de la toolbar
		this.add(new BarreOutils(controleur), BorderLayout.NORTH);
		
		// Création du panneau contenaire
		JPanel monContenair = new JPanel();
		monContenair.setLayout(new FlowLayout());
		
		// Création du panneau parametre		
		this.panneauParametres = new PanneauParametres(this.modele);
		monContenair.add(panneauParametres);	
		
		// Création du panneau dessin
		this.panneauDessin = new PanneauDessinGraphe(this.modele, this.controleur);
		monContenair.add(panneauDessin);
		
		// Ajout du contenu a la fenetre
		this.getContentPane().add(monContenair);
		
	}

	/**
	 * Methode permettant de determiner les proprietés de la fenetre
	 */
	public void proprieteFenetre() {
		
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setTitle(ParametresFenetre.titreFenetre);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Apparence de la fenetre
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//"com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// FIXME ? this.pack();
		this.validate();
	}
	
	
	/**
	 * Méthode permettant de calculer la taille des composants de la fenetre 
	 */
	private void calculerTailleComposants() {
		
		// Calcul de la dimension utile de l'écran
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
		ParametresFenetre.dimensionEcran = new Dimension(maximumWindowBounds.width, maximumWindowBounds.height);
		this.setSize(ParametresFenetre.dimensionEcran);
		
		// Calcul de la dimension du JPanel paramètres (à gauche)
		ParametresFenetre.dimensionJPanelParametres = new Dimension(300, ParametresFenetre.dimensionEcran.height - 85);
		ParametresFenetre.dimensionJPanelParametresGraphe = new Dimension(280, 120);
		ParametresFenetre.dimensionJPanelParametresSommet = new Dimension(280, 120);
		ParametresFenetre.dimensionJPanelParametresDijkstra = new Dimension(280, 150);
		ParametresFenetre.dimensionJPanelParametresKruskal = new Dimension(280, 120);
		
		// Calcul de la dimension du JPanel d'affichage du graphe
		ParametresFenetre.dimensionJPanelDessin = new Dimension(ParametresFenetre.dimensionEcran.width - 390,
				ParametresFenetre.dimensionEcran.height - 85);		
	}

	/**
	 * 
	 * Methode qui cree le menu
	 * 
	 * @param controleur
	 */
	public void creerMenu() {

		JMenuBar jMenuBar = new JMenuBar();
				
		MenuFichier menuFichier = new MenuFichier(controleur);
		jMenuBar.add(menuFichier);
		
		MenuEdition menuEdition = new MenuEdition(controleur);
		jMenuBar.add(menuEdition);
		
		MenuCalcul menuCalcul = new MenuCalcul(controleur); 
		jMenuBar.add(menuCalcul);
			
		MenuAide menuAide = new MenuAide(controleur);
		jMenuBar.add(menuAide);
		
		this.setJMenuBar(jMenuBar);	
	}
	

	@Override
	public void update(Observable o, Object arg) {
		
		panneauDessin.update();
		
		panneauParametres.update();
	}

	public PanneauDessinGraphe getDessin() {
		return panneauDessin;
	}
}
