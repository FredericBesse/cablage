package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import fr.enac.iessa16.cablage.model.Model;

/**
 * Classe Controller permettant de gérer les intéractions entre la vue et le modèle
 * (gestion des actions utilisateur)
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Controller {
	

	// Le sous-controleur permettant la gestion des intéractions avec le menu
	private MenuController menuController;
	
	// Le sous-controleur permettant la gestion des clic souris sur le panneau d'affichage du graphe
	private CliqueFenetreGraphController cliquefenetreGraphcontroller;
	
	
	/**
	 * Constructeur de la classe Controller
	 * 
	 * @param model l'objet modèle
	 */
	public Controller(Model model) {	
			
		// Création des sous-controleurs (à qui on fournit l'objet modèle)
		this.menuController = new MenuController(model);
		this.cliquefenetreGraphcontroller= new CliqueFenetreGraphController(model);
		
	}

	/**
	 * @return le sous-controleur gérant le menu
	 */
	public ActionListener getMenuController() {
		return menuController;
	}
	
	
	/**
	 * @return le sous-controleur gérant les clics souris sur le panneau d'affichage du graphe
	 */
	public MouseListener getCliqueFenetreGraphController() {
		return cliquefenetreGraphcontroller;
	}
	

}
