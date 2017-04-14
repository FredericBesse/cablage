package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.GrapheTheorique;
import fr.enac.iessa16.cablage.model.Sommet;
import fr.enac.iessa16.cablage.view.DessinDuGrapheParDefaut;

/**
 * Classe Controleur Clique sur le Sommet
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurCliqueSommet implements MouseListener, MouseMotionListener {

	//
	private DonneesAAfficher model;
	private DessinDuGrapheParDefaut dessin1;
	
	
	
	
	
	
	
	
	/**
	 * Constructeur de la classe ControleurCliqueSommet.java
	 * @param monmodel
	 */
	public ControleurCliqueSommet(DonneesAAfficher monmodel) {
		super();
		this.model = monmodel;
		
	}









	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		//System.out.println("Clic souris x="+e.getX()+" y="+e.getY()+"lon="+DessinDuGrapheParDefaut.ConversionxEnLongitude(e.getX())+" lat="+DessinDuGrapheParDefaut.ConversionyEnLatitude(e.getY()));
		
		
		model.nouveauClicSouris(e.getX(), e.getY());
		
	}

	
	
	
	
	
	
	
	










	public ControleurCliqueSommet() {
		super();
		// TODO Auto-generated constructor stub
		
		
		
		
		
	}









	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
		
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
		
		System.out.println("Mouse draged x="+e.getX());
		
	}









	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
