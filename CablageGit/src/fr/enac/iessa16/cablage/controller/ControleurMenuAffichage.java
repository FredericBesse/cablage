package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

public class ControleurMenuAffichage implements ActionListener {
	
	
	private Modele model;

	public ControleurMenuAffichage(Modele monModel) {
		super();
		this.model = monModel;
		
		
		
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s = e.getActionCommand();
		if(s.equals(ParametresFenetre.centrage))
		{
			
			model.centrerVue();
			
		}
		if(s.equals(ParametresFenetre.zoomPlus))
		{
			
			model.zoomer();
		}
		
		if(s.equals(ParametresFenetre.zoomMoins))
		{
			
			model.dezoomer();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
