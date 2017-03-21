package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import fr.enac.iessa16.cablage.model.Donnees;

/**
 * Classe Controller permettant de gérer les intéractions entre la vue et le modèle
 * (gestion des actions utilisateur)
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Controleur {
	

	// Le sous-controleur permettant la gestion des intéractions avec le menu
	private ControleurDuMenu _controleurDuMenu;
	
	// Le sous-controleur permettant la gestion des clic souris sur le panneau d'affichage du graphe
	private ControleurCliqueFenetreGraphe _controleurCliqueFenetreGraphe;
	
	// Le sous-controleur permettant la gestion des interactions souris sur le panneau d'affichage du graphe
	private ControleurZoomFenetreGraphe _controleurZoomFenetreGraphe;
	
	
	/**
	 * Constructeur de la classe Controller
	 * 
	 * @param donnees l'objet données
	 */
	public Controleur(Donnees donnees) {	
			
		// Création des sous-controleurs (à qui on fournit l'objet modèle)
		this._controleurDuMenu = new ControleurDuMenu(donnees);
		this._controleurCliqueFenetreGraphe= new ControleurCliqueFenetreGraphe(donnees);
		this._controleurZoomFenetreGraphe = new ControleurZoomFenetreGraphe(donnees);
		
	}

	
	/**
	 * @return le sous-controleur gérant le menu
	 */
	public ActionListener getMenuController() {
		return _controleurDuMenu;
	}
	
	
	/**
	 * @return le sous-controleur gérant les clics souris sur le panneau d'affichage du graphe
	 */
	public MouseListener getCliqueFenetreGraphController() {
		return _controleurCliqueFenetreGraphe;
	}
	
	/**
	 * @return le sous-controleur gérant les zoom souris sur le panneau d'affichage du graphe
	 */
	public ControleurZoomFenetreGraphe getZoomFenetreGraphController() {
		return _controleurZoomFenetreGraphe;
	}
	

}
