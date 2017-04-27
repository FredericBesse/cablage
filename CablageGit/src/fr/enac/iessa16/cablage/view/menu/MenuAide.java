package fr.enac.iessa16.cablage.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
