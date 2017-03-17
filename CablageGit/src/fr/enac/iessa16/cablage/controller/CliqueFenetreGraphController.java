package fr.enac.iessa16.cablage.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.enac.iessa16.cablage.model.Model;

public class CliqueFenetreGraphController implements MouseListener {

 
	private Model model;
  
	public CliqueFenetreGraphController(Model model) {
		super();
		// TODO Auto-generated constructor stub
		this .model = model;
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("Controller : clique "+e.getX()+" "+e.getY());
		model.cliqueFenetreGraphe(e.getX(),e.getY());
		// TODO Auto-generated method stub
		
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
