package fr.enac.iessa16.cablage.main;
import fr.enac.iessa16.cablage.controller.Controller;
import fr.enac.iessa16.cablage.model.Model;
import fr.enac.iessa16.cablage.view.View;



/**
 * Classe permettant de lancer l'application
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class RunCablageApplication {

	/**
	 * Le Main de notre application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
				
		// Création du modèle
		Model model = new Model();
		
		// Création du controleur (à qui on passe le modèle précédemment créé)
		Controller controller = new Controller(model);
		
		// Création de la vue (à qui on passe le modèle et le controleur)
		View view = new View(model,controller);
		
		// Affichage de la fenetre
		view.start();
	}
}
