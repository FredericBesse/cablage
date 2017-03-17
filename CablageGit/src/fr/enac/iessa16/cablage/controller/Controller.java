package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Model;

public class Controller {
	
	private Model model;
	
	// Listener du menu
	private MenuController menuController;
	
	
	
	public Controller(Model model) {
		
		this.model = model;
		
		this.menuController = new MenuController(model);
		
	}

	public ActionListener getMenuController() {
		// TODO Auto-generated method stub
		return menuController;
	}

	

}
