package fr.enac.iessa16.cablage.main;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.view.Fenetre;

/**
 * Classe 
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ApplicationCablage {

	/**
	 * Ma...
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// On créé les
		DonneesAAfficher donnees = new DonneesAAfficher();
		Controleur controleur1 = new Controleur(donnees);
		
		//ConstructionGrapheParDefaut constructeurdegraphepardefaut = new ConstructionGrapheParDefaut();
		//constructeurdegraphepardefaut.getGraphe();
		Fenetre toto = new Fenetre(controleur1,donnees);//, constructeurdegraphepardefaut.getGraphe());
		
		System.out.println("toto");
		
	}
}