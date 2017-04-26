package fr.enac.iessa16.cablage.main;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Modele;
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
		Modele modele = new Modele();
		
		// Création du controleur principal
		Controleur controleur = new Controleur(modele);
				
		// Création de la fenetre
		Fenetre fenetre = new Fenetre(controleur, modele);
		modele.setParent(fenetre); // pour l'affichage des boites de dialogue
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