package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe définissant le controleur du menu Aide
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurMenuAide implements ActionListener {
	
	// Le modele
	private Modele model;

	/**
	 * Constructeur de la classe ControleurMenuAide
	 *
	 * @param monModel le modèle de l'application
	 */
	public ControleurMenuAide(Modele monModel) {
		
		super();
		
		this.model = monModel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(ParametresFenetre.javadoc)) {
			model.javaDoc();
		}
		
		if(s.equals(ParametresFenetre.apropos)) {
			model.aPropos();
		}		
	}
}
