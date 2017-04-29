package fr.enac.iessa16.cablage.view.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe MenuAide
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class MenuAide extends JMenu {
	

	/**
	 * Constructeur de la classe MenuAide
	 * 
	 * @param controleur
	 */
	public MenuAide(Controleur controleur) {
		
		super(ParametresFenetre.menuAide);
		
		JMenuItem javadoc = new JMenuItem(ParametresFenetre.javadoc);
		javadoc.setActionCommand(ParametresFenetre.javadoc);
		javadoc.addActionListener(controleur.getControleMenuAide());
		this.add(javadoc);
		
	
		JMenuItem aPropos = new JMenuItem(ParametresFenetre.apropos);
		aPropos.setActionCommand(ParametresFenetre.apropos);
		aPropos.addActionListener(controleur.getControleMenuAide());
		this.add(aPropos);	
	}
}
