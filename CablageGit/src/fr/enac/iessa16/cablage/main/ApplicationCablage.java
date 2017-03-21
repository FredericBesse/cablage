package fr.enac.iessa16.cablage.main;
import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.model.Donnees;
import fr.enac.iessa16.cablage.view.Fenetre;



/**
 * Classe permettant de lancer l'application
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ApplicationCablage {

	/**
	 * Le Main de notre application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
				
		// Création du modèle
		Donnees donnees = new Donnees();
		
		// Création du controleur (à qui on passe le modèle précédemment créé)
		Controleur controller = new Controleur(donnees);
		
		// Création de la vue (à qui on passe le modèle et le controleur)
		Fenetre view = new Fenetre(donnees,controller);
		
		// Affichage de la fenetre
		view.start();
	}
}
