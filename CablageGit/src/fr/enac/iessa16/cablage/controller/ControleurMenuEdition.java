package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

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

		if (actionCommand.equals(ParametresFenetre.ajouterSommet)) {
			
			modele.ajouterSommet();

		}

		if (actionCommand.equals(ParametresFenetre.supprimerSommet)) {

			modele.supprimerSommet();

		}

		if (actionCommand.equals(ParametresFenetre.ajouterArete)) {

			modele.ajouterArete();

		}

		if (actionCommand.equals(ParametresFenetre.supprimerArete)) {

			modele.supprimerArete();

		}
		
		if (actionCommand.equals(ParametresFenetre.preferences)) {

			modele.preferences();

		}
	}
}
