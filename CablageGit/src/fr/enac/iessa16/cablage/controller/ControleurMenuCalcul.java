package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe définissant le controleur du menu calcul
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurMenuCalcul implements ActionListener {

	// Le modele
	Modele modele;
	
	/**
	 * Constructeur de la classe ControleurMenuCalcul
	 *
	 * @param modele le modele de l'application
	 */
	public ControleurMenuCalcul(Modele modele) {
		super();
		this.modele = modele;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// On stocke l'action command du bouton cliqué dans une string.
		String s = e.getActionCommand();
		
		// Puis on compare cette string aux differentes options possibles
		if(s.equals(ParametresFenetre.dijkstra)) {
			
			// On appelle la methode calculerDjikstra implementée dans la classe Modele
			modele.calculerDijkstra();
		}
		
		if(s.equals(ParametresFenetre.kruskal)) {
			
			// On appelle la methode calculerKruskal implementée dans la classe Modele
			modele.calculerKruskal();
		}		
	}
}
