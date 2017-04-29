package fr.enac.iessa16.cablage.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

public class MenuAffichage extends JMenu {

	public MenuAffichage(Controleur controleur) {
		super(ParametresFenetre.menuAffichage);
		
		
		
		JMenuItem optionCentrage = new JMenuItem(ParametresFenetre.centrage);
		this.add(optionCentrage);
		optionCentrage.setActionCommand(ParametresFenetre.centrage);
		optionCentrage.addActionListener(controleur.getControleurMenuAffichage());
		
		
		
		JMenuItem optionZoomer = new JMenuItem(ParametresFenetre.zoomPlus);
		this.add(optionZoomer);
		optionZoomer.setActionCommand(ParametresFenetre.zoomPlus);
		optionZoomer.addActionListener(controleur.getControleurMenuAffichage());
		
		JMenuItem optionDezoomer = new JMenuItem(ParametresFenetre.zoomMoins);
		this.add(optionDezoomer);
		optionDezoomer.setActionCommand(ParametresFenetre.zoomMoins);
		optionDezoomer.addActionListener(controleur.getControleurMenuAffichage());
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
