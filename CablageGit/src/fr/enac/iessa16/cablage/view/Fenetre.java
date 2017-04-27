package fr.enac.iessa16.cablage.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.menu.BarreOutils;
import fr.enac.iessa16.cablage.view.menu.MenuAide;
import fr.enac.iessa16.cablage.view.menu.MenuCalcul;
import fr.enac.iessa16.cablage.view.menu.MenuEdition;
import fr.enac.iessa16.cablage.view.menu.MenuFichier;

/**
 * Classe Fenetre qui correspond à la vue...
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Observer {

	private Controleur controleur;
	private PanneauDessinGraphe dessin;
	private JMenuBar jbar;
	private PanneauParametres panneauParametres;

	/**
	 * Constructeur de la classe Fenetre.java
	 * 
	 * @param controleur
	 * @param model
	 */
	public Fenetre(Controleur controleur, Modele model) {

		// Ajout de la fenetre comme observateur du modele
		model.addObserver(this);

		proprieteFenetre();
		
		this.controleur = controleur;
		creerMenu();
	
	
		
		
		JPanel monContenair = new JPanel();

		monContenair.setLayout(new FlowLayout());
		
		

	


		// Calcul de la dimension du JPanel paramètres (à gauche)
		ParametresFenetre.dimensionJPanelParametres = new Dimension(300, ParametresFenetre.dimensionEcran.height - 85);
		ParametresFenetre.dimensionJPanelParametresSommet = new Dimension(280, 250);
		ParametresFenetre.dimensionJPanelParametresArete = new Dimension(280, 250);
		// Calcul de la dimension du JPanel d'affichage du graphe
		ParametresFenetre.dimensionJPanelGraphe = new Dimension(ParametresFenetre.dimensionEcran.width - 390,
				ParametresFenetre.dimensionEcran.height - 85);
		System.out.println("toto "+ParametresFenetre.dimensionJPanelGraphe);
		this.setSize(ParametresFenetre.dimensionEcran);

		System.out.println("taille écran " + ParametresFenetre.dimensionEcran);
		System.out.println("size container " + monContenair.preferredSize());

		
		
		panneauParametres = new PanneauParametres(model);
		monContenair.add(panneauParametres);	
		
		//JTabbedPane tabbedPane = new JTabbedPane();
		
		this.dessin = new PanneauDessinGraphe(model, controleur);
		
		
		
		//dessin1 = new DessinDuGrapheParDefaut(model, controleur);
		//dessin2 = new DessinDuGrapheParDefaut(model, controleur);
	//	ImageIcon icon = createImageIcon("images/middle.gif");

		//JComponent Vue = makeTextPanel("Panel #1");
		//tabbedPane.addTab("Tab 1", icon, panel1,
		//                  "Does nothing");
		//tabbedPane.addTab("Defaut", dessin);
		//tabbedPane.addTab("LFPO", dessin1);
		//tabbedPane.addTab("LFPG", dessin2);
	//	tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		//droite.add(dessin);
		
		//monContenair.add(tabbedPane);
		monContenair.add(dessin);
		
		

		
		

		this.getContentPane().add(monContenair);
		this.add(new BarreOutils(controleur), BorderLayout.NORTH);

		// this.pack();
		this.setVisible(true);

	}

	/**
	 * Methode permettant de determiner les proprietés de la fenetre
	 */
	public void proprieteFenetre() {
		// Calcul de la dimension de l'écran
		GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maximumWindowBounds=graphicsEnvironment.getMaximumWindowBounds();
		
		ParametresFenetre.dimensionEcran = new Dimension(maximumWindowBounds.width, maximumWindowBounds.height);
		
		System.out.println("toolkit "+ParametresFenetre.dimensionEcran);
		
		
		
		System.out.println("GraphicsEnvironment "+maximumWindowBounds);
		
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setTitle("Cablage à cout minimum");
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

	/**
	 * 
	 * Methode qui cree le menu
	 * 
	 * @param controleur
	 */
	public void creerMenu() {

		
		
		this.jbar = new JMenuBar();
		this.setJMenuBar(jbar);
		
		MenuFichier menufichier = new MenuFichier(controleur);
		jbar.add(menufichier);
		
		//JMenu menuCalcul = new JMenu("Calcul");
		MenuCalcul menucalcul = new MenuCalcul(controleur); 
		this.jbar.add(menucalcul);
		
		MenuEdition menuedition = new MenuEdition(controleur);
		this.jbar.add(menuedition);
		
		MenuAide menuaide = new MenuAide(controleur);
		jbar.add(menuaide);
	
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub


		dessin.update();
		
		panneauParametres.update();
		
		
		
		
		

	}

	public PanneauDessinGraphe getDessin() {
		return dessin;
	}

}
