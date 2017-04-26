package fr.enac.iessa16.cablage.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.sun.glass.events.KeyEvent;

import fr.enac.iessa16.cablage.controller.Controleur;
import javafx.scene.control.SeparatorMenuItem;

public class MenuFichier extends JMenu{

	
	private Controleur controleur;
	private JMenuBar jbar;
	
	
	public MenuFichier(Controleur controleur) 
	{
		super("Fichier");
		
		
		this.controleur = controleur;
		
		JMenu optionNouveau = new JMenu(ParametresNomFr.nouveau);
		optionNouveau.setMnemonic(KeyEvent.VK_N);
		this.add(optionNouveau);
		//optionNouveau.setAccelerator(KeyStroke.getKeyStroke("control N"));
	//	optionNouveau.setActionCommand("Nouveau");
	//	optionNouveau.addActionListener(controleur.getControleMenu());
		
		JMenuItem optionGrapheVide = new JMenuItem("Graphe Vide");
		optionNouveau.add(optionGrapheVide);
		optionGrapheVide.setActionCommand("GrapheVide");
		optionGrapheVide.addActionListener(controleur.getControleMenu());
			
		
		
		
		JMenuItem optionGrapheAleatoire = new JMenuItem("Graphe Aleatoire");
		optionNouveau.add(optionGrapheAleatoire);
		optionGrapheAleatoire.setActionCommand("GrapheAleatoire");
		optionGrapheAleatoire.addActionListener(controleur.getControleMenu());
		
		
		
		
		
		
		
		
		JMenuItem optionChargerDefaut = new JMenuItem("Charger graphe par defaut");
		optionChargerDefaut.setMnemonic(KeyEvent.VK_D);
		optionChargerDefaut.setAccelerator(KeyStroke.getKeyStroke("control D"));
		optionChargerDefaut.setActionCommand("ChargerGrapheParDefaut");
		optionChargerDefaut.addActionListener(controleur.getControleMenu());
		optionNouveau.add(optionChargerDefaut);
		
		JMenuItem optionImporter = new JMenuItem(ParametresNomFr.importer);
		optionImporter.setMnemonic(KeyEvent.VK_O);
		optionImporter.setAccelerator(KeyStroke.getKeyStroke("control I"));
		optionImporter.setActionCommand("ChargerGrapheDonné");
		optionImporter.addActionListener(controleur.getControleMenu());
		this.add(optionImporter);
		
		
		JMenuItem optionOuvrir = new JMenuItem(ParametresNomFr.ouvrir);
		optionOuvrir.setMnemonic(KeyEvent.VK_O);
		optionOuvrir.setAccelerator(KeyStroke.getKeyStroke("control O"));
		optionOuvrir.setActionCommand("Ouvrir");
		optionOuvrir.addActionListener(controleur.getControleMenu());
		this.add(optionOuvrir);
		this.addSeparator();
		
		
		
		
	
		
		
		JMenuItem optionEnregistrerSous = new JMenuItem(ParametresNomFr.enregistrerSous);
        optionEnregistrerSous.setMnemonic(KeyEvent.VK_S);
		optionEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke("control S"));
		optionEnregistrerSous.setActionCommand("EnregistrerSous");
		optionEnregistrerSous.addActionListener(controleur.getControleMenu());
		this.add(optionEnregistrerSous);
		this.addSeparator();
		
	
		
		JMenuItem optionImprimer = new JMenuItem(ParametresNomFr.imprimer);
		optionImprimer.setMnemonic(KeyEvent.VK_P);
		optionImprimer.setAccelerator(KeyStroke.getKeyStroke("control P"));
		optionImprimer.setActionCommand("Imprimer");
		optionImprimer.addActionListener(controleur.getControleMenu());
		this.add(optionImprimer);
		this.addSeparator();
		
		
		JMenuItem optionfermer = new JMenuItem(ParametresNomFr.fermer);
        optionfermer.setMnemonic(KeyEvent.VK_W);
		optionfermer.setAccelerator(KeyStroke.getKeyStroke("control W"));
		optionfermer.setActionCommand("Fermer");
		optionfermer.addActionListener(controleur.getControleMenu());
		this.add(optionfermer);
		
		
		
		JMenuItem optionQuitter = new JMenuItem(ParametresNomFr.quitter);
		optionQuitter.setMnemonic(KeyEvent.VK_Q);
		optionQuitter.setAccelerator(KeyStroke.getKeyStroke("control Q"));
		optionQuitter.setActionCommand("Quitter");
		optionQuitter.addActionListener(controleur.getControleMenu());
		this.add(optionQuitter);
		
		
		
	}	
}
