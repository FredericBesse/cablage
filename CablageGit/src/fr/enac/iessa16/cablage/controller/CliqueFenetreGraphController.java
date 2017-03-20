package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.enac.iessa16.cablage.model.Model;

/**
 * Classe CliqueFenetreGraphController permettant de gérer les intéractions (clic souris)
 * avec le panneau d'affichage du graphe
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class CliqueFenetreGraphController implements MouseListener {

 
	/**
	 * Attribut permettant de stocker le modèle de l'application
	 */
	private Model model;
	
  
	/**
	 * Constructeur de la classe CliqueFenetreGraphController
	 * 
	 * @param model le modèle de l'application
	 */
	public CliqueFenetreGraphController(Model model) {
		
		// Stockage du modèle dans les attributs pour une utilisation ultérieure
		this.model = model;		
	}
	
	
	/* (non-Javadoc)
	 * Implémentation de l'interface MouseListener
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// Affichage d'un message sur la console avec les coordonnées du point cliqué
		System.out.println("Controller : clique "+e.getX()+" "+e.getY());
		
		// On demande au modèle de se mettre à jour et on lui passe les coordonnées du point cliqué
		model.cliqueFenetreGraphe(e.getX(),e.getY());
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {		
	}
}
