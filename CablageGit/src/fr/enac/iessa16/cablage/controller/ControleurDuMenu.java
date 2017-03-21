package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Donnees;

/**
 * Classe MenuController permettant de gérer les intéractions avec le menu de la fenetre
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurDuMenu implements ActionListener {
	
	/**
	 * Attribut permettant de stocker le modèle de l'application
	 */
	private Donnees model;
	
	
	/**
	 * Constructeur de la classe MenuController
	 * 
	 * @param model le modèle de l'application
	 */
	public ControleurDuMenu(Donnees model) {
		
		// Stockage du modèle dans les attributs pour une utilisation ultérieure
		this.model = model;
	}
	

	/* (non-Javadoc)
	 * 
	 * Implémentation de l'interface ActionListener
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// On récupère l'actionCommand du bouton cliqué
		switch (e.getActionCommand()) {
		
			// Bouton "Charger défaut"
			case "loadDefaultGraph":
				model.loadDefaultGraph(); // on demande au modèle de charger le graphe par défaut
				break;
	
			// Bouton "Charger..."
			case "load":
				System.out.println("Load : TODO : "+e.getActionCommand());		
				// TODO : action charger
				break;
				
			// Bouton "Quitter"
			case "quit":
				System.exit(0); // on quitte l'application
				// TODO : ajouter une confirmation/sauvegarde...
				break;
				
			default:
				break;
		}
	}

}
