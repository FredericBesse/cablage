package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Model;

public class MenuController implements ActionListener {
	
	private Model model;
	
	public MenuController(Model model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch (e.getActionCommand()) {
		case "loadDefaultGraph":
			model.loadDefaultGraph();
			break;

		case "load":
			System.out.println("Load : TODO : "+e.getActionCommand());			
			break;
			
		case "quit":
			System.exit(0);
			break;
			
		default:
			break;
		}
	}

}
