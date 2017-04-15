package fr.enac.iessa16.cablage.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	//private ConteneurFenetre pan;
	private JLabel nomDuProjet = new JLabel("Nom Projet :");
	private JLabel sujet = new JLabel("Calcul du cablage à cout minimum");
	private JLabel parametre = new JLabel ("PARAMETRES DU GRAPHE");
	private JLabel Sommet = new JLabel ("Caractéristiques du Sommet :");
	private JLabel NomSommet = new JLabel ("Nom:");
	private JLabel latSommet = new JLabel ("Latitude:");
	private JLabel longSommet = new JLabel ("Longitude:");
	private JTextField nomSommet = new JTextField ("");


	
	/**
	 * Constructeur de la classe Fenetre.java
	 * @param controleur
	 * @param model
	 */
	public Fenetre(Controleur controleur,DonneesAAfficher model)
	{
		this.setLayout(new BorderLayout());
		model.addObserver(this);

		proprieteFenetre();
		this.jbar = jbar;
		this.controleur = controleur;
		creerMenu(controleur);
		creerMenuCalcul(controleur);
		Container monContenair = this.getContentPane();
		this.dessin = new DessinDuGrapheParDefaut(model,controleur);
		monContenair.add(dessin);
		//monContenair.add(dessin);
	/*	this.setLayout(new GridLayout(1,2));
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(10,1));
		jp1.add(Sommet);
		
	//	JPanel p3 = new JPanel();
		//p3.setLayout(new Grid);
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		p2.add(NomSommet);
		p2.add(nomSommet);
		jp1.add(p2);
		
		
		
		JPanel p3 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		p3.add(latSommet);
		p3.add(nomSommet);
		jp1.add(p3);
		
		this.add(jp1);
		this.add(dessin);
		
	//	JPanel p3 = new JPanel(new BoxLayou)
		
		

	    //On définit le layout en lui indiquant qu'il travaillera en ligne

	   /* b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));

	    b1.add(new JButton("Bouton 1"));


	    JPanel b2 = new JPanel();

	    //Idem pour cette ligne

	    b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));

	    b2.add(new JButton("Bouton 2"));

	    b2.add(new JButton("Bouton 3"));


	    JPanel b3 = new JPanel();

	    //Idem pour cette ligne

	    b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));

	    b3.add(new JButton("Bouton 4"));

	    b3.add(new JButton("Bouton 5"));

	    b3.add(new JButton("Bouton 6"));


	    JPanel b4 = new JPanel();

	    //On positionne maintenant ces trois lignes en colonne

	    b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));

	    b4.add(b1);
		*/
		
		
		
		
		
		
		//monContenair.add(pan); 
		
		java.awt.Toolkit outil = getToolkit();
		ParametresFenetre.dimensionEcran = outil.getScreenSize();
	//	JPanel jpanel1 = new JPanel();
		//jpanel1.setLayout(new FlowLayout());
	//	jpanel1.add(nomDuProjet);
		//jpanel1.add(sujet);
		//this.add(jpanel1);
		
		
		
		
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
		JMenuItem optionAStar = new JMenuItem("Calculer AStar");
		menuCalcul.add(optionAStar);
		optionAStar.setActionCommand("CalculerAStar");
		optionAStar.addActionListener(controleur.getControleCalcul());
		this.jbar.add(menuCalcul);
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		//System.out.println("update");

		dessin.repaint();


	}







}



