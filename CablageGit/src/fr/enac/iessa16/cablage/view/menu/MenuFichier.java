package fr.enac.iessa16.cablage.view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.Parametres;

/**
 * Classe définissant le menu Fichier
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class MenuFichier extends JMenu {
	
	/**
	 * Constructeur de la classe MenuFichier
	 *
	 * @param controleur le controleur de l'application
	 */
	public MenuFichier(Controleur controleur) {
		
		// Appel au constructeur parent
		super(Parametres.menuFichier);
		
		// Création du sous-menu Nouveau...
		JMenu optionNouveau = new JMenu(Parametres.nouveau);
		//optionNouveau.setMnemonic(KeyEvent.VK_N);
				
		// Création de l'item Nouveau/Graphe vide
		JMenuItem optionGrapheVide = new JMenuItem(Parametres.grapheVide);
		optionNouveau.add(optionGrapheVide);
		optionGrapheVide.setActionCommand(Parametres.grapheVide);
		optionGrapheVide.addActionListener(controleur.getControleurMenuFichier());
		
		// Création de l'item Nouveau/Graphe par defaut		
		JMenuItem optionGrapheParDefaut = new JMenuItem(Parametres.grapheDefaut);
		optionGrapheParDefaut.setMnemonic(KeyEvent.VK_D);
		optionGrapheParDefaut.setAccelerator(KeyStroke.getKeyStroke("control D"));
		optionGrapheParDefaut.setActionCommand(Parametres.grapheDefaut);
		optionGrapheParDefaut.addActionListener(controleur.getControleurMenuFichier());
		optionNouveau.add(optionGrapheParDefaut);
		
		// Création de l'item Nouveau/Graphe aléatoire		
		JMenuItem optionGrapheAleatoire = new JMenuItem(Parametres.grapheAleatoire);
		optionNouveau.add(optionGrapheAleatoire);
		optionGrapheAleatoire.setActionCommand(Parametres.grapheAleatoire);
		optionGrapheAleatoire.addActionListener(controleur.getControleurMenuFichier());
		
		// Ajout du sous-menu Nouveau au menu Fichier		
		this.add(optionNouveau);
		
		// Création de l'item Importer
		JMenuItem optionImporter = new JMenuItem(Parametres.importer);
		optionImporter.setMnemonic(KeyEvent.VK_O);
		optionImporter.setAccelerator(KeyStroke.getKeyStroke("control I"));
		optionImporter.setActionCommand(Parametres.importer);
		optionImporter.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionImporter);
		
		// Création de l'item Ouvrir
		JMenuItem optionOuvrir = new JMenuItem(Parametres.ouvrir);
		optionOuvrir.setMnemonic(KeyEvent.VK_O);
		optionOuvrir.setAccelerator(KeyStroke.getKeyStroke("control O"));
		optionOuvrir.setActionCommand(Parametres.ouvrir);
		optionOuvrir.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionOuvrir);
		
		this.addSeparator();
				
		// Création de l'item Enregistrer		
		JMenuItem optionEnregistrer = new JMenuItem(Parametres.enregistrer);
        optionEnregistrer.setMnemonic(KeyEvent.VK_S);
		optionEnregistrer.setAccelerator(KeyStroke.getKeyStroke("control S"));
		optionEnregistrer.setActionCommand(Parametres.enregistrer);
		optionEnregistrer.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionEnregistrer);
		
		// Création de l'item Enregistrer Sous		
		JMenuItem optionEnregistrerSous = new JMenuItem(Parametres.enregistrerSous);
        //optionEnregistrerSous.setMnemonic(KeyEvent.VK_S);
		//optionEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		//optionEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));
		optionEnregistrerSous.setActionCommand(Parametres.enregistrerSous);
		optionEnregistrerSous.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionEnregistrerSous);
		
		this.addSeparator();
		
		// Création de l'item Imprimer		
		JMenuItem optionImprimer = new JMenuItem(Parametres.imprimer);
		optionImprimer.setMnemonic(KeyEvent.VK_P);
		optionImprimer.setAccelerator(KeyStroke.getKeyStroke("control P"));
		optionImprimer.setActionCommand(Parametres.imprimer);
		optionImprimer.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionImprimer);
		
		this.addSeparator();
	
		// Création de l'item Fermer
		JMenuItem optionfermer = new JMenuItem(Parametres.fermer);
        optionfermer.setMnemonic(KeyEvent.VK_W);
		optionfermer.setAccelerator(KeyStroke.getKeyStroke("control W"));
		optionfermer.setActionCommand(Parametres.fermer);
		optionfermer.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionfermer);
		
		// Création de l'item Quitter
		JMenuItem optionQuitter = new JMenuItem(Parametres.quitter);
		optionQuitter.setMnemonic(KeyEvent.VK_Q);
		optionQuitter.setAccelerator(KeyStroke.getKeyStroke("control Q"));
		optionQuitter.setActionCommand(Parametres.quitter);
		optionQuitter.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionQuitter);		
	}	
}
