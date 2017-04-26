package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;
import fr.enac.iessa16.cablage.model.core.Sommet;
import fr.enac.iessa16.cablage.view.DessinDuGrapheParDefaut;
import fr.enac.iessa16.cablage.view.ParametresFenetre;


/**
 * Classe Controleur Clique sur le Sommet
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurCliqueSommet implements MouseListener, MouseMotionListener, MouseWheelListener {

	//
	private Modele model;
	private DessinDuGrapheParDefaut dessin1;
	
	
	
	private int x_old, y_old;
	
	
	
	
	/**
	 * Constructeur de la classe ControleurCliqueSommet.java
	 * @param monmodel
	 */
	public ControleurCliqueSommet(Modele monmodel) {
		super();
		this.model = monmodel;
		
	}









	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		//System.out.println("Clic souris x="+e.getX()+" y="+e.getY()+"lon="+DessinDuGrapheParDefaut.ConversionxEnLongitude(e.getX())+" lat="+DessinDuGrapheParDefaut.ConversionyEnLatitude(e.getY()));
		
		
		model.touverSommetLePlusProcheDuClicSouris(e.getX(), e.getY());
		
	}

	
	
	
	
	
	
	
	










	public ControleurCliqueSommet() {
		super();
		// TODO Auto-generated constructor stub
		
		
		
		
		
	}









	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		this.x_old = e.getX();
		this.y_old = e.getY();
		
		
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}









	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int dx = e.getX()-x_old;
		int dy = e.getY()-y_old;
		
		System.out.println("Mouse draged dx="+dx+" dy="+dy);
		
		model.drag(dx, dy);
		
		
		this.x_old = e.getX();
		this.y_old = e.getY();
		
	}









	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}









	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		// on récupère la rotation de la molette (le "-" permet de modifier le
				// sens de rotation pour zoomer/dézoomer)
				int delta = -5 * e.getWheelRotation();
				
				//System.out.println("delta = "+delta);

				// on récupére la position de la souris pour zoomer sur la souris
				int xs = e.getX();
				int ys = e.getY();

				// on calcule la nouvelle échelle pour la vue
				double newEchelle = ParametresFenetre.echelle * //Math.max(1,
						 Math.pow(1.01, delta);
				
				

				// on calcule les nouveaux offset en fonction de la position de la
				// souris
				double newOffsetX = ParametresFenetre.offsetX
						+ DessinDuGrapheParDefaut.getRealCoordX(xs) / ParametresFenetre.ECHELLE_BASE
						* (ParametresFenetre.echelle - newEchelle);
				double newOffsetY = ParametresFenetre.offsetY
						- DessinDuGrapheParDefaut.getRealCoordY(ys) / ParametresFenetre.ECHELLE_BASE
						* (ParametresFenetre.echelle - newEchelle);
				
			
				ParametresFenetre.echelle = newEchelle;
				ParametresFenetre.offsetX = newOffsetX;
				ParametresFenetre.offsetY = newOffsetY;
				

			model.changement();
	}

}
