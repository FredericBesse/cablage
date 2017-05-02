package fr.enac.iessa16.cablage.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.menu.BarreOutils;
import fr.enac.iessa16.cablage.view.menu.MenuAffichage;
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
	private Modele modele;
	private PanneauDessinGraphe panneauDessin;
	private PanneauParametres panneauParametres;
	

	/**
	 * Constructeur de la classe Fenetre.java
	 * 
	 * @param controleur le controleur
	 * @param modele le modele
	 */
	public Fenetre(Controleur controleur, Modele modele) {

		super();
		
		this.controleur = controleur;
		this.modele = modele;
		
		// Ajout de la fenetre comme observateur du modele
		this.modele.addObserver(this);
				
		creerContenuFenetre();
		
		setProprietesFenetre();
		
		this.setVisible(true);
		
		// Récupération de la taille du panneau dessin (pour les fonctions de conversion)
		Parametres.panneauDessinWidth = this.panneauDessin.getWidth();
		Parametres.panneauDessinHeight = this.panneauDessin.getHeight();
	}
	
	

	/**
	 * Méthode permettant de créer le contenu de la fenetre  
	 */
	private void creerContenuFenetre() {
		
		// Création du menu
		System.setProperty("apple.laf.useScreenMenuBar", "true"); // compatibilité MAC
		creerMenu();
		
		// Création de la toolbar
		this.add(new BarreOutils(controleur), BorderLayout.NORTH);
		
		// Création du panneau contenaire
		JPanel monContenair = new JPanel();
		monContenair.setLayout(new BorderLayout());
		
		// Création du panneau parametre	
		this.panneauParametres = new PanneauParametres(this.modele);
		//monContenair.add(panneauParametres, BorderLayout.WEST);
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setViewportView(panneauParametres);
		monContenair.add(scroll, BorderLayout.WEST);
		
		
		// Création du panneau dessin
		this.panneauDessin = new PanneauDessinGraphe(this.modele, this.controleur);
		monContenair.add(panneauDessin, BorderLayout.CENTER);
				
		// Ajout du contenu a la fenetre
		this.getContentPane().add(monContenair);
	}

	

	/** 
	 * Methode qui cree le menu
	 */
	public void creerMenu() {

		JMenuBar jMenuBar = new JMenuBar();
				
		MenuFichier menuFichier = new MenuFichier(controleur);
		jMenuBar.add(menuFichier);
		
		MenuEdition menuEdition = new MenuEdition(controleur);
		jMenuBar.add(menuEdition);
		
		MenuAffichage menuAffichage = new MenuAffichage(controleur); 
		jMenuBar.add(menuAffichage);
		
		MenuCalcul menuCalcul = new MenuCalcul(controleur); 
		jMenuBar.add(menuCalcul);
		
		MenuAide menuAide = new MenuAide(controleur);
		jMenuBar.add(menuAide);
		
		this.setJMenuBar(jMenuBar);	
	}
	
	/**
	 * Methode permettant de determiner les proprietés de la fenetre
	 */
	public void setProprietesFenetre() {
		
		// Calcul de la dimension utile de l'écran
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
		this.setSize(new Dimension(maximumWindowBounds.width, maximumWindowBounds.height));
	
		// Titre
		this.setTitle(Parametres.titreFenetre);
		
		// Fermeture
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Apparence de la fenetre
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//"com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.validate();
	}
	
	

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		panneauDessin.update();
		
		panneauParametres.update();
	}
	
	
	/*
	 * Getters
	 */
	public PanneauDessinGraphe getDessin() {
		return panneauDessin;
	}
}
