package fr.enac.iessa16.cablage.main;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.view.Fenetre;

/**
 * Classe principale permettant le lancement de l'application
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ApplicationCablage {
	
	/**
	 * Le constructeur de l'application
	 */
	public ApplicationCablage() {
		
		// Création du modèle
		DonneesAAfficher donnees = new DonneesAAfficher();
		
		// Création du controleur principal
		Controleur controleur = new Controleur(donnees);
				
		// Création de la fenetre
		Fenetre fen = new Fenetre(controleur,donnees);
		donnees.setParent(fen);
	}

	
	/**
	 * Main de l'application cablage
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		new ApplicationCablage();
		
	}
}