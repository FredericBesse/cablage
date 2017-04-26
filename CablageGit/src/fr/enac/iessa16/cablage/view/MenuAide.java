package fr.enac.iessa16.cablage.view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controleur;

public class MenuAide extends JMenu {
	
private Controleur controleur;	
	

	public MenuAide(Controleur controleur) {
		super("?");
		this.controleur = controleur;
		
		JMenuItem aide = new JMenuItem(ParametresNomFr.aide);
		aide.setActionCommand("aide");
		aide.addActionListener(controleur.getControleAide());
		this.add(aide);
		
		
		
		JMenuItem javadoc = new JMenuItem(ParametresNomFr.javadoc);
		javadoc.setActionCommand("laJavadoc");
		javadoc.addActionListener(controleur.getControleAide());
		this.add(javadoc);

		
		JMenuItem aPropos = new JMenuItem(ParametresNomFr.apropos);
		aPropos.setActionCommand("propos");
		aPropos.addActionListener(controleur.getControleAide());
		this.add(aPropos);
	
		
		
	
	}
	
	
	
	
	









}