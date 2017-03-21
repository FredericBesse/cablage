package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.enac.iessa16.cablage.model.Donnees;

/**
 * Classe CliqueFenetreGraphController permettant de gérer les intéractions (clic souris)
 * avec le panneau d'affichage du graphe
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurZoomFenetreGraphe implements MouseMotionListener, MouseWheelListener {

 
	/**
	 * Attribut permettant de stocker le modèle de l'application
	 */
	private Donnees model;
	
  
	/**
	 * Constructeur de la classe CliqueFenetreGraphController
	 * 
	 * @param model le modèle de l'application
	 */
	public ControleurZoomFenetreGraphe(Donnees model) {
		
		// Stockage du modèle dans les attributs pour une utilisation ultérieure
		this.model = model;		
	}


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
