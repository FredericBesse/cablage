package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.Parametres;
import fr.enac.iessa16.cablage.view.dialog.FenetrePreferences;

/**
 * Classe définissant le controleur du menu Edition
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurMenuEdition implements ActionListener {

	// Le modèle
	private Modele modele;

	/**
	 * Constructeur de la classe ControleurMenuEdition
	 *
	 * @param monmodel le modèle de l'application
	 */
	public ControleurMenuEdition(Modele monmodel) {
		super();
		this.modele = monmodel;
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String actionCommand = e.getActionCommand();

		if (actionCommand.equals(Parametres.ajouterSommet)) {
			
			modele.ajouterSommet();

		}

		if (actionCommand.equals(Parametres.supprimerSommet)) {

			modele.supprimerSommet();

		}

		if (actionCommand.equals(Parametres.ajouterArete)) {

			modele.ajouterArete();

		}

		if (actionCommand.equals(Parametres.supprimerArete)) {

			modele.supprimerArete();

		}
		
		if (actionCommand.equals(Parametres.preferences)) {

			new FenetrePreferences();

		}
	}
}
