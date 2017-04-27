package fr.enac.iessa16.cablage.view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

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
		super(ParametresFenetre.menuFichier);
		
		// Création du sous-menu Nouveau...
		JMenu optionNouveau = new JMenu(ParametresFenetre.nouveau);
		//optionNouveau.setMnemonic(KeyEvent.VK_N);
				
		// Création de l'item Nouveau/Graphe vide
		JMenuItem optionGrapheVide = new JMenuItem(ParametresFenetre.grapheVide);
		optionNouveau.add(optionGrapheVide);
		optionGrapheVide.setActionCommand(ParametresFenetre.grapheVide);
		optionGrapheVide.addActionListener(controleur.getControleurMenuFichier());
		
		// Création de l'item Nouveau/Graphe par defaut		
		JMenuItem optionGrapheParDefaut = new JMenuItem(ParametresFenetre.grapheDefaut);
		optionGrapheParDefaut.setMnemonic(KeyEvent.VK_D);
		optionGrapheParDefaut.setAccelerator(KeyStroke.getKeyStroke("control D"));
		optionGrapheParDefaut.setActionCommand(ParametresFenetre.grapheDefaut);
		optionGrapheParDefaut.addActionListener(controleur.getControleurMenuFichier());
		optionNouveau.add(optionGrapheParDefaut);
		
		// Création de l'item Nouveau/Graphe aléatoire		
		JMenuItem optionGrapheAleatoire = new JMenuItem(ParametresFenetre.grapheAleatoire);
		optionNouveau.add(optionGrapheAleatoire);
		optionGrapheAleatoire.setActionCommand(ParametresFenetre.grapheAleatoire);
		optionGrapheAleatoire.addActionListener(controleur.getControleurMenuFichier());
		
		// Ajout du sous-menu Nouveau au menu Fichier		
		this.add(optionNouveau);
		
		// Création de l'item Importer
		JMenuItem optionImporter = new JMenuItem(ParametresFenetre.importer);
		optionImporter.setMnemonic(KeyEvent.VK_O);
		optionImporter.setAccelerator(KeyStroke.getKeyStroke("control I"));
		optionImporter.setActionCommand(ParametresFenetre.importer);
		optionImporter.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionImporter);
		
		// Création de l'item Ouvrir
		JMenuItem optionOuvrir = new JMenuItem(ParametresFenetre.ouvrir);
		optionOuvrir.setMnemonic(KeyEvent.VK_O);
		optionOuvrir.setAccelerator(KeyStroke.getKeyStroke("control O"));
		optionOuvrir.setActionCommand("Ouvrir");
		optionOuvrir.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionOuvrir);
		
		this.addSeparator();
				
		// Création de l'item Enregistrer		
		JMenuItem optionEnregistrer = new JMenuItem(ParametresFenetre.enregistrer);
        optionEnregistrer.setMnemonic(KeyEvent.VK_S);
		optionEnregistrer.setAccelerator(KeyStroke.getKeyStroke("control S"));
		optionEnregistrer.setActionCommand(ParametresFenetre.enregistrer);
		optionEnregistrer.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionEnregistrer);
		
		// Création de l'item Enregistrer Sous		
		JMenuItem optionEnregistrerSous = new JMenuItem(ParametresFenetre.enregistrerSous);
        //optionEnregistrerSous.setMnemonic(KeyEvent.VK_S);
		//optionEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		//optionEnregistrerSous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));
		optionEnregistrerSous.setActionCommand(ParametresFenetre.enregistrerSous);
		optionEnregistrerSous.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionEnregistrerSous);
		
		this.addSeparator();
		
		// Création de l'item Imprimer		
		JMenuItem optionImprimer = new JMenuItem(ParametresFenetre.imprimer);
		optionImprimer.setMnemonic(KeyEvent.VK_P);
		optionImprimer.setAccelerator(KeyStroke.getKeyStroke("control P"));
		optionImprimer.setActionCommand(ParametresFenetre.imprimer);
		optionImprimer.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionImprimer);
		
		this.addSeparator();
	
		// Création de l'item Fermer
		JMenuItem optionfermer = new JMenuItem(ParametresFenetre.fermer);
        optionfermer.setMnemonic(KeyEvent.VK_W);
		optionfermer.setAccelerator(KeyStroke.getKeyStroke("control W"));
		optionfermer.setActionCommand(ParametresFenetre.fermer);
		optionfermer.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionfermer);
		
		// Création de l'item Quitter
		JMenuItem optionQuitter = new JMenuItem(ParametresFenetre.quitter);
		optionQuitter.setMnemonic(KeyEvent.VK_Q);
		optionQuitter.setAccelerator(KeyStroke.getKeyStroke("control Q"));
		optionQuitter.setActionCommand(ParametresFenetre.quitter);
		optionQuitter.addActionListener(controleur.getControleurMenuFichier());
		this.add(optionQuitter);		
	}	
}
