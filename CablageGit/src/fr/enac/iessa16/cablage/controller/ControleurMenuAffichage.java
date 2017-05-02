package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.Parametres;

public class ControleurMenuAffichage implements ActionListener {

	// Le modele
	private Modele model;

	public ControleurMenuAffichage(Modele monModel) {
		super();
		this.model = monModel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String s = e.getActionCommand();
		if (s.equals(Parametres.centrage)) {

			model.centrerVue();

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
