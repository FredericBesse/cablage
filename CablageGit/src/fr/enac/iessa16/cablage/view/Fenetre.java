package fr.enac.iessa16.cablage.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.DonneesAAfficher;

/**
 * Classe Fenetre qui correspond à la vue...
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Fenetre extends JFrame implements Observer {

	private Controleur controleur;
	private DessinDuGrapheParDefaut dessin;
	private JMenuBar jbar;
	private PanneauParametres panneauParametres;

	/**
	 * Constructeur de la classe Fenetre.java
	 * 
	 * @param controleur
	 * @param model
	 */
	public Fenetre(Controleur controleur, DonneesAAfficher model) {

		// Ajout de la fenetre comme observateur du modele
		model.addObserver(this);

		proprieteFenetre();
		
		this.controleur = controleur;
		creerMenu(controleur);
		creerMenuCalcul(controleur);

		JPanel monContenair = new JPanel();

		monContenair.setLayout(new FlowLayout());

		

		// ParametresFenetre.dimensionFenetre = new Dimension()

		// monContenair.setPreferredSize(new
		// Dimension(ParametresFenetre.dimensionEcran.width-200,
		// ParametresFenetre.dimensionEcran.height-200));

		// Calcul de la dimension du JPanel paramètres (à gauche)
		ParametresFenetre.dimensionJPanelParametres = new Dimension(300, ParametresFenetre.dimensionEcran.height - 85);
		ParametresFenetre.dimensionJPanelParametresSommet = new Dimension(280, 250);
		ParametresFenetre.dimensionJPanelParametresArete = new Dimension(280, 250);
		// Calcul de la dimension du JPanel d'affichage du graphe
		ParametresFenetre.dimensionJPanelGraphe = new Dimension(ParametresFenetre.dimensionEcran.width - 390,
				ParametresFenetre.dimensionEcran.height - 85);
		System.out.println("toto "+ParametresFenetre.dimensionJPanelGraphe);
		// JPanel jpanel1 = new JPanel();
		// jpanel1.setLayout(new FlowLayout());
		// jpanel1.add(nomDuProjet);
		// jpanel1.add(sujet);
		// this.add(jpanel1);

		this.setSize(ParametresFenetre.dimensionEcran);

		System.out.println("taille écran " + ParametresFenetre.dimensionEcran);
		System.out.println("size container " + monContenair.preferredSize());

		
		
		panneauParametres = new PanneauParametres(model);
		monContenair.add(panneauParametres);

	
		// Création du panneau
		JPanel droite = new JPanel();
				
		// Création de la bordure
		droite.setBorder(BorderFactory.createTitledBorder("Vue 2D"));
				
		// Taille du panneau
		//droite.setPreferredSize(ParametresFenetre.dimensionJPanelParametres);
		
		
		this.dessin = new DessinDuGrapheParDefaut(model, controleur);
		droite.add(dessin);
		
		monContenair.add(droite);

		JPanel vide = new JPanel();
		// vide.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		vide.setPreferredSize(new Dimension(60, 50));
		monContenair.add(vide);

		

		this.getContentPane().add(monContenair);

		// this.pack();
		this.setVisible(true);

	}

	/**
	 * Methode permettant de determiner les proprietés de la fenetre
	 */
	public void proprieteFenetre() {
		// Calcul de la dimension de l'écran
		java.awt.Toolkit outil = getToolkit();
		ParametresFenetre.dimensionEcran = outil.getScreenSize();
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setTitle("Cablage à cout minimum");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.validate();

	}

	/**
	 * 
	 * Methode qui cree le menu
	 * 
	 * @param controleur
	 */
	public void creerMenu(Controleur controleur) {

		// Look and feel de la fenetre
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.controleur = controleur;
		this.jbar = new JMenuBar();
		this.setJMenuBar(jbar);
		JMenu menu = new JMenu("Gerer graphe");
		jbar.add(menu);
		JMenuItem option1 = new JMenuItem("Charger Graphe par defaut");
		menu.add(option1);
		option1.setActionCommand("ChargerGrapheParDefaut");
		option1.addActionListener(controleur.getControleMenu());
		JMenuItem option2 = new JMenuItem("ChargerGrapheDonné");
		option2.setActionCommand("ChargerGrapheDonné");
		option2.addActionListener(controleur.getControleMenu());
		menu.add(option2);
		JMenuItem option3 = new JMenuItem("Quitter");
		option3.setActionCommand("Quitter");
		option3.addActionListener(controleur.getControleMenu());
		// option3.addActionListener(this);
		menu.add(option3);

		// Get all the available look and feel that we are going to use for
		// creating the JMenuItem and assign the action listener to handle
		// the selection of menu item to change the look and feel.
		/*
		 * UIManager.LookAndFeelInfo[] lookAndFeels =
		 * UIManager.getInstalledLookAndFeels(); for (UIManager.LookAndFeelInfo
		 * lookAndFeelInfo : lookAndFeels) { JMenuItem item = new
		 * JMenuItem(lookAndFeelInfo.getName()); item.addActionListener(event ->
		 * { try { // Set the look and feel for the frame and update the UI //
		 * to use a new selected look and feel.
		 * System.out.println("toto"+lookAndFeelInfo.getClassName());
		 * UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
		 * SwingUtilities.updateComponentTreeUI(this); } catch (Exception e) {
		 * e.printStackTrace(); } }); menu.add(item); }
		 * 
		 */
	}

	public void creerMenuCalcul(Controleur controleur) {

		// this.setJMenuBar(jbar);
		JMenu menuCalcul = new JMenu("Calcul");

		JMenuItem optionDjikstra = new JMenuItem("Calculer Djikstra");
		menuCalcul.add(optionDjikstra);
		optionDjikstra.setActionCommand("CalculerDjikstra");
		optionDjikstra.addActionListener(controleur.getControleCalcul());
		JMenuItem optionKruskal = new JMenuItem("Calculer Kruskal");
		menuCalcul.add(optionKruskal);
		optionKruskal.setActionCommand("CalculerKruskal");
		optionKruskal.addActionListener(controleur.getControleCalcul());
		JMenuItem optionAStar = new JMenuItem("Calculer AStar");
		menuCalcul.add(optionAStar);
		optionAStar.setActionCommand("CalculerAStar");
		optionAStar.addActionListener(controleur.getControleCalcul());
		this.jbar.add(menuCalcul);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		// System.out.println("update");

		dessin.update();
		
		panneauParametres.update();
		
		
		
		
		

	}

}
