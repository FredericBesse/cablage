package fr.enac.iessa16.cablage.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

public class MenuAffichage extends JMenu {

	public MenuAffichage(Controleur controleur) {
		super(ParametresFenetre.menuAffichage);
		
		
		
		JMenuItem optionCentrage = new JMenuItem(ParametresFenetre.centrage);
		this.add(optionCentrage);
		optionCentrage.setActionCommand(ParametresFenetre.centrage);
		optionCentrage.addActionListener(controleur.getControleurMenuAffichage());
		this.addSeparator();
	
		
		JMenuItem optionZoomer = new JMenuItem(ParametresFenetre.zoomPlus);
		this.add(optionZoomer);
		optionZoomer.setActionCommand(ParametresFenetre.zoomPlus);
		optionZoomer.addActionListener(controleur.getControleurMenuAffichage());

		
		JMenuItem optionDezoomer = new JMenuItem(ParametresFenetre.zoomMoins);
		optionDezoomer.setActionCommand(ParametresFenetre.zoomMoins);
		optionDezoomer.addActionListener(controleur.getControleurMenuAffichage());
		optionCentrage.setAccelerator(KeyStroke.getKeyStroke("-"));
		this.add(optionDezoomer);
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
