package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import fr.enac.iessa16.cablage.model.Model;

public class Controller {
	
	private Model model;
	
	
	private MenuController menuController;
	private CliqueFenetreGraphController cliquefenetreGraphcontroller;
	
	public Controller(Model model) {
		
		this.model = model;
		
		this.menuController = new MenuController(model);
		this.cliquefenetreGraphcontroller= new CliqueFenetreGraphController(model);
		
	}

	public ActionListener getMenuController() {
		// TODO Auto-generated method stub
		return menuController;
	}
	
	public MouseListener getCliqueFenetreGraphController() {
		// TODO Auto-generated method stub
		return cliquefenetreGraphcontroller;
	}

	

}
