package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.enac.iessa16.cablage.model.Modele;


/**
 * Classe Controleur Souris
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurSouris implements MouseListener, MouseMotionListener, MouseWheelListener {

	// Le modele
	private Modele modele;
	
	// La position précédente de la souris	
	private int x_old, y_old;
		
	
	/**
	 * Constructeur de la classe ControleurSouris
	 * 
	 * @param monModele
	 */
	public ControleurSouris(Modele monModele) {
		super();
		this.modele = monModele;		
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(modele.isModeAjouterSommet() == true) {
			modele.ajouterSommetEffectif(e.getX(),e.getY());
			
		}
		else {
			modele.touverSommetLePlusProcheDuClicSouris(e.getX(), e.getY());
			modele.touverAreteLPlusProcheDuClicSouris(e.getX(), e.getY());
		}
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		
		this.x_old = e.getX();
		this.y_old = e.getY();
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


	

	@Override
	public void mouseDragged(MouseEvent e) {

		int dx = e.getX()-x_old;
		int dy = e.getY()-y_old;
		
		modele.drag(dx, dy);
		
		this.x_old = e.getX();
		this.y_old = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}




	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		// on récupére la position de la souris pour zoomer sur la souris
		int xs = e.getX();
		int ys = e.getY();
		
		// on récupére la rotation de la molette
		int rotation = e.getWheelRotation();
		
		// on appelle la fonction zoom du modele
		modele.zoom(xs, ys, rotation);
	}
}
