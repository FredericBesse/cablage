package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe controleur du menu
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurMenuFichier implements ActionListener {

	// Le modèle
	private Modele model;

	/**
	 * Constructeur de la classe ControleurMenu
	 * 
	 * @param monModel
	 *            le modèle de l'application
	 */
	public ControleurMenuFichier(Modele monModel) {
		this.model = monModel;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// On recupere l'actionCommand du bouton sur lequel on a cliqué
		String s = e.getActionCommand();

		// On la compare aux differents choix possibles

		if (s.equals(ParametresFenetre.grapheVide)) {

			model.nouveauGrapheVide();
		}

		if (s.equals(ParametresFenetre.grapheDefaut)) {

			// On appelle la methode ChargerLeGraphe implementée dans la classe
			// Modele
			model.chargerGrapheParDefaut();
		}

		if (s.equals(ParametresFenetre.grapheAleatoire)) {

			model.nouveauGrapheAleatoire();
		}

		if (s.equals(ParametresFenetre.importer)) {

			// On appelle la methode chargerGrapheFichierTexte implementée dans
			// la classe Modele
			model.chargerGrapheFichierTexte();
		}

		if (s.equals(ParametresFenetre.ouvrir)) {

			model.ouvrir();
		}

		if (s.equals(ParametresFenetre.enregistrer)) {

			model.enregister();
		}

		if (s.equals(ParametresFenetre.enregistrerSous)) {

			model.enregisterSous();
		}
		
		if (s.equals(ParametresFenetre.imprimer)) {

			model.imprimer();
		}

		if (s.equals(ParametresFenetre.fermer)) {

			model.fermer();
		}		

		if (s.equals(ParametresFenetre.quitter)) {
		
			model.quitter();
		}
	}
}
