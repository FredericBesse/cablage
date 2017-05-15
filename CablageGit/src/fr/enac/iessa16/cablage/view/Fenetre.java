package fr.enac.iessa16.cablage.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.EtatVue;
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

	// Le logger
	private Logger LOGGER = LogManager.getLogger(Fenetre.class);
	
	private Controleur controleur;
	private Modele modele;
	private PanneauDessinGraphe panneauDessin;
	private PanneauParametres panneauParametres;

	private MenuFichier menuFichier;
	private MenuEdition menuEdition;
	private MenuAffichage menuAffichage;
	private MenuCalcul menuCalcul;
	private MenuAide menuAide;

	/**
	 * Constructeur de la classe Fenetre.java
	 * 
	 * @param controleur
	 *            le controleur
	 * @param modele
	 *            le modele
	 */
	public Fenetre(Controleur controleur, Modele modele) {

		super();
		
		LOGGER.debug("Création de la fenetre");

		this.controleur = controleur;
		this.modele = modele;
		
		// Ajout de la fenetre comme observateur du modele
		this.modele.addObserver(this);

		creerContenuFenetre();

		setProprietesFenetre();
		
		// on rend visible la fenetre
		try {
			// pour voir le splash screen
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setVisible(true);

		// Récupération de la taille du panneau dessin (pour les fonctions de
		// conversion)
		Parametres.panneauDessinWidth = this.panneauDessin.getWidth();
		Parametres.panneauDessinHeight = this.panneauDessin.getHeight();
		Parametres.couleurFondPanneauDessinLancement = this.panneauDessin.getBackground();
	}

	
	/**
	 * Méthode permettant de créer le contenu de la fenetre
	 */
	private void creerContenuFenetre() {

	
		// Création du menu													
		creerMenu();

		// Recuperation du panneau contenaire
		Container monContenair = this.getContentPane();
		monContenair.setLayout(new BorderLayout());

		// Création de la toolbar
		this.add(new BarreOutils(controleur), BorderLayout.NORTH);

		// Création du panneau parametre
		this.panneauParametres = new PanneauParametres(this.modele);

		// on le met dans un scroll panel pour les petits ecrans
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setViewportView(panneauParametres);
		monContenair.add(scroll, BorderLayout.WEST);

		// Création du panneau dessin
		JPanel panneauVue2D = new JPanel();
		panneauVue2D.setLayout(new BorderLayout());
		panneauVue2D.setBorder(BorderFactory.createTitledBorder(Parametres.titrePanneauVue2D));
		this.panneauDessin = new PanneauDessinGraphe(this.modele, this.controleur);
		panneauVue2D.add(panneauDessin, BorderLayout.CENTER);
		monContenair.add(panneauVue2D, BorderLayout.CENTER);

		// Ajout du contenu a la fenetre
		// this.getContentPane().add(monContenair);

	}

	/**
	 * Methode qui cree le menu
	 */
	public void creerMenu() {

		// compatibilité MAC OS X
		System.setProperty("apple.laf.useScreenMenuBar", "true"); 
		
		JMenuBar jMenuBar = new JMenuBar();

		menuFichier = new MenuFichier(controleur);
		jMenuBar.add(menuFichier);

		menuEdition = new MenuEdition(controleur);
		jMenuBar.add(menuEdition);

		menuAffichage = new MenuAffichage(controleur, this);
		jMenuBar.add(menuAffichage);

		menuCalcul = new MenuCalcul(controleur);
		jMenuBar.add(menuCalcul);

		menuAide = new MenuAide(controleur);
		jMenuBar.add(menuAide);

		this.setJMenuBar(jMenuBar);
	}

	/**
	 * Methode permettant de determiner les proprietés de la fenetre
	 */
	public void setProprietesFenetre() {

		// Calcul de la dimension utile de l'écran
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
		this.setSize(new Dimension(maximumWindowBounds.width, maximumWindowBounds.height));

		// Titre
		this.setTitle(Parametres.titreFenetre);

		// Fermeture
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Apparence de la fenetre
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.validate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {

		
		
		
		if (EtatVue.nouveauGraphe) {
			
			panneauDessin.updateNouveauGraphe();
			panneauParametres.updateNouveauGraphe();
			
			menuFichier.updateNouveauGraphe();
			menuEdition.updateNouveauGraphe();
			menuAffichage.updateNouveauGraphe();
			menuCalcul.updateNouveauGraphe();
			
			EtatVue.nouveauGraphe = false;
		}
		
		if (EtatVue.fermerGraphe) {
			panneauDessin.updateFermerGraphe();
			panneauParametres.updateNouveauGraphe();
			menuFichier.updateFermerGraphe();
			menuEdition.updateFermerGraphe();
			menuAffichage.updateFermerGraphe();
			menuCalcul.updateFermerGraphe();
			EtatVue.fermerGraphe = false;
		}
		
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
