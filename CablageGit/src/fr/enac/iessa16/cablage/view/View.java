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
 * @author bessefc
 *
 */
public class View implements Observer {

	// Le modèle
	private Model model;
	
	private Controller controller;

	// La fenetre principale
	private JFrame mainJFrame;
	
	private DrawGraph2DPanel contenuGraphe ;

	/**
	 * Contructeur
	 * 
	 * @param model
	 * @param controller
	 */
	public View(Model model, Controller controller) {

		// On récupère le modèle et on se met observer
		this.model = model;
		this.model.addObserver(this);
		
		this.controller = controller;

		// Création de la fenetre principale
		this.mainJFrame = new JFrame("Calcul du cablage à cout minimum");

		// Initialisation de la fenetre
		this.init();
		
		
		this.createMenu();
		
		this.createGraphPanel();
		

	}
	
	
	

	private void init() {
		mainJFrame.setPreferredSize(new Dimension(1100, 600));
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
	private void createMenu() {
		
		JMenuBar jbar = new JMenuBar();
		mainJFrame.setJMenuBar(jbar);

		MenuFichier jmenu = new MenuFichier(controller);
		jbar.add(jmenu);

		/*JMenu menuFichier = new JMenu("Fichier");
		jbar.add(menuFichier);*/
		
	}
	
	
	private void createGraphPanel() {
		
		// on r�cup�re le container de la fen�tre :
		Container container = mainJFrame.getContentPane();
		
		contenuGraphe = new DrawGraph2DPanel(model,controller);
		
		
		contenuGraphe.setPreferredSize(new Dimension(1100,600));
		
		container.add(contenuGraphe);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		// reaffichage...
		
		System.out.println("View : Model updated");
		
		contenuGraphe.repaint();
		
	}

	public void start() {
			
		mainJFrame.pack();
		mainJFrame.setVisible(true);

	}

}
