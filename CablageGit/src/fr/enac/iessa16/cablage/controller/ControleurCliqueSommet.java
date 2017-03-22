package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.GrapheTheorique;
import fr.enac.iessa16.cablage.model.Sommet;
import fr.enac.iessa16.cablage.view.DessinDuGrapheParDefaut;

/**
 * Classe Controleur Clique sur le Sommet
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurCliqueSommet implements MouseListener{

	DonneesAAfficher model;
	Sommet sommet;
	DessinDuGrapheParDefaut dessin1;
	
	
	
	
	
	/**
	 * Constructeur de la classe ControleurCliqueSommet.java
	 * @param monmodel
	 * @param sommet
	 */
	public ControleurCliqueSommet(DonneesAAfficher monmodel, Sommet sommet) {
		super();
		this.model = monmodel;
		this.sommet = sommet;
	}









	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		double latitude;
		int xClic;
	
		xClic = e.getX();
		System.out.println("Clic souris x="+e.getX());
		
		for(int i=0 ; i<model.getGrapheàafficher().getEnsembleDeSommet().size();i++)
		{
			
			System.out.println("Latitude sommet "+i+" : "+model.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude());
			
			//latitude = 
			
			
			if(Math.abs(model.getGrapheàafficher().getEnsembleDeSommet().get(i).getLatitude()-e.getX())<25)
			
			
			{
				
				System.out.println("ca coincide avec le sommet "+model.getGrapheàafficher().getEnsembleDeSommet().get(i).getNom());
				
				
				this.sommet = model.getGrapheàafficher().getEnsembleDeSommet().get(i);
				
				System.out.println(sommet.getNom());
				//this.dessin1.paint(model.getGrapheàafficher());
		
			
			} else {
				System.out.println(" TEST NOK");
			}
			
		
		
		
		}
		
		
		if (sommet != null) {
			model.changement();
		}
		
		
		model.nouveauClicSouris(e.getX(), e.getY());
		
	}

	
	
	
	
	
	
	
	
	/**
	 * Getters et Setters
	 */
	public Sommet getSommet() {
		return sommet;
	}









	public void setSommet(Sommet sommet) {
		this.sommet = sommet;
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

}
