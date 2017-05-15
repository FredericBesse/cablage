package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.Parametres;

/**
 * Classe controleur du menu Fichier
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurMenuFichier implements ActionListener {

	// Le modèle
	private Modele model;

	/**
	 * Constructeur de la classe ControleurMenuFichier
	 * 
	 * @param monModel
	 *            le modèle de l'application
	 */
	public ControleurMenuFichier(Modele monModel) {
		this.model = monModel;
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// On recupere l'actionCommand du bouton sur lequel on a cliqué
		String s = e.getActionCommand();

		// On la compare aux differents choix possibles
		// TODO switch
		if (s.equals(Parametres.grapheVide)) {
			model.nouveauGrapheVide();
		}

		if (s.equals(Parametres.grapheDefaut)) {
			// On appelle la methode ChargerLeGraphe implementée dans la classe
			// Modele
			model.chargerGrapheParDefaut();
		}

		if (s.equals(Parametres.importer)) {
			// On appelle la methode chargerGrapheFichierTexte implementée dans
			// la classe Modele
			model.chargerGrapheFichierTexte();
		}

		if (s.equals(Parametres.ouvrir)) {
			model.ouvrir();
		}

		if (s.equals(Parametres.enregistrer)) {
			model.enregister();
		}

		if (s.equals(Parametres.enregistrerSous)) {
			model.enregisterSous();
		}
		
		if (s.equals(Parametres.imprimer)) {
			model.imprimer();
		}

		if (s.equals(Parametres.fermer)) {
			model.fermer();
		}		

		if (s.equals(Parametres.quitter)) {
			model.quitter();
		}
	}
}
