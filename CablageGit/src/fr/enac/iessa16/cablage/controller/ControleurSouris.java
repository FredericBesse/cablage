package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.PanneauDessinGraphe;
import fr.enac.iessa16.cablage.view.ParametresFenetre;


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
		
		modele.touverSommetLePlusProcheDuClicSouris(e.getX(), e.getY());
		
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

		// on récupère la rotation de la molette (le "-" permet de modifier le
		// sens de rotation pour zoomer/dézoomer)
		int delta = -5 * e.getWheelRotation();
		
		// on récupére la position de la souris pour zoomer sur la souris
		int xs = e.getX();
		int ys = e.getY();

		// on calcule la nouvelle échelle pour la vue
		double newEchelle = ParametresFenetre.echelle * 
				 Math.pow(1.01, delta);
		
		// on calcule les nouveaux offset en fonction de la position de la souris
		double newOffsetX = ParametresFenetre.offsetX
				+ PanneauDessinGraphe.getRealCoordX(xs) / ParametresFenetre.ECHELLE_BASE
				* (ParametresFenetre.echelle - newEchelle);
		double newOffsetY = ParametresFenetre.offsetY
				- PanneauDessinGraphe.getRealCoordY(ys) / ParametresFenetre.ECHELLE_BASE
				* (ParametresFenetre.echelle - newEchelle);
		
	
		ParametresFenetre.echelle = newEchelle;
		ParametresFenetre.offsetX = newOffsetX;
		ParametresFenetre.offsetY = newOffsetY;
				
		modele.changement();
	}
}
