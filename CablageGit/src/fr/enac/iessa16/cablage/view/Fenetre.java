package fr.enac.iessa16.cablage.view;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.javafx.tk.Toolkit;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.DonneesAAfficher;



/**
 * Classe Fenetre qui correspond à la vue...
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Fenetre extends JFrame  implements Observer{






	private Controleur controleur;
	private DessinDuGrapheParDefaut dessin;	
	private JMenuBar jbar;


	/**
	 * Constructeur de la classe Fenetre.java
	 * @param controleur
	 * @param model
	 */
	public Fenetre(Controleur controleur,DonneesAAfficher model)
	{

		model.addObserver(this);

		proprieteFenetre();
		this.jbar = jbar;
		this.controleur = controleur;
		creerMenu(controleur);
		creerMenuCalcul(controleur);
		Container monContenair = this.getContentPane();

		this.dessin = new DessinDuGrapheParDefaut(model,controleur);
		monContenair.add(dessin);

		java.awt.Toolkit outil = getToolkit();
		ParametresFenetre.dimensionEcran = outil.getScreenSize();

		this.setSize(ParametresFenetre.dimensionEcran);
		//this.pack();
		this.setVisible(true);

	}




	/**
	 * Methode permettant de determiner les proprietés de la fenetre
	 */
	public void proprieteFenetre()
	{
		//this.setSize(1100,600);

		//	this.setSize(1024,750);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setTitle("Cablage à cout minimum");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.validate();


	}



	/**
	 * 
	 *Methode qui cree le menu
	 * @param controleur
	 */
	public void creerMenu(Controleur controleur)
	{
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
		//option3.addActionListener(this);
		menu.add(option3);

	}

	public void creerMenuCalcul(Controleur controleur)
	{

		//this.setJMenuBar(jbar);
		JMenu menuCalcul = new JMenu("Calcul");

		JMenuItem optionDjikstra = new JMenuItem("Calculer Djikstra");
		menuCalcul.add(optionDjikstra);
		optionDjikstra.setActionCommand("CalculerDjikstra");
		optionDjikstra.addActionListener(controleur.getControleCalcul());
		JMenuItem optionKruskal = new JMenuItem("Calculer Kruskal");
		menuCalcul.add(optionKruskal);
		optionKruskal.setActionCommand("CalculerKruskal");
		optionKruskal.addActionListener(controleur.getControleCalcul());

		this.jbar.add(menuCalcul);
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		//System.out.println("update");

		dessin.repaint();


	}







}



