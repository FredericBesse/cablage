package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;

public class ControleurAide implements ActionListener {
	private DonneesAAfficher model;

	public ControleurAide(DonneesAAfficher monModel) {
		super();
		
		this.model = monModel;
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("aide"))
		{
			
			model.aide();
			
		}
		if(s.equals("laJavadoc"))
		{
			
			model.javaDoc();
		}
		
		if(s.equals("propos"))
		{
			model.aPropos();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
