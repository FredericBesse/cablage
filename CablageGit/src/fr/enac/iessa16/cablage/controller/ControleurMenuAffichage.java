package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import fr.enac.iessa16.cablage.model.EtatVue;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.Parametres;

/**
 * Classe permettant de gérer les interactions sur le menu Affichage
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurMenuAffichage implements ActionListener {

	// Le modele
	private Modele model;

	/**
	 * Le constructeur du menu affichage
	 * 
	 * @param monModel le modèle de l'application
	 */
	public ControleurMenuAffichage(Modele monModel) {
		super();
		this.model = monModel;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// on récupère l'action command pour identifier la source de l'evenement
		String s = e.getActionCommand();
		
		if (s.equals(Parametres.centrage)) {
			EtatVue.centreVueDemande=true;
			model.changement();
		}
		
		if (s.equals(Parametres.zoomPlus)) {
			model.zoom(Parametres.panneauDessinWidth/2 , Parametres.panneauDessinHeight/2 , -5);
		}

		if (s.equals(Parametres.zoomMoins)) {
			model.zoom(Parametres.panneauDessinWidth/2 , Parametres.panneauDessinHeight/2 ,5);
		}

		if (s.equals(Parametres.affichageSommet)) {
			JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
			Parametres.afficherSommet = source.isSelected();
			model.changement();
		}

		if (s.equals(Parametres.affichageNomSommet)) {
			JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
			Parametres.afficherNomSommet = source.isSelected();
			model.changement();
		}

		if (s.equals(Parametres.affichageArete)) {
			JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
			Parametres.afficherArete = source.isSelected();
			model.changement();
		}

		if (s.equals(Parametres.affichageDijkstra)) {
			JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
			Parametres.afficherDijkstra = source.isSelected();
			model.changement();
		}

		if (s.equals(Parametres.affichageKruskal)) {
			JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
			Parametres.afficherKruskal = source.isSelected();
			model.changement();
		}
	}
}
