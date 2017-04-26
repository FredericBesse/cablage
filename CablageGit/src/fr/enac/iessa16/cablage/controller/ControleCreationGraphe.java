package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;

public class ControleCreationGraphe implements ActionListener {
	
	
	
	Modele model;

	public ControleCreationGraphe(Modele monmodel) {
		super();
		this.model = monmodel;
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		if(s.equals("ajouterUnSommet"))
				{
			
					model.ajoutSommet(0, 0);
			
				}
		
		
		if(s.equals("supprimerSommet"))
				{
			
					model.suppSommet();
			
				}
				
		if(s.equals("ajoutArete"))
				{
			
					model.ajoutArete();
			
				}
		
		if(s.equals("suppArete"))
				{
			
					model.supprimerArete();
			
				}
		
		
	}
	
	
	
	
	
	
	

}
