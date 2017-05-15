package fr.enac.iessa16.cablage.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.Parametres;

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
	 * @param controleur le controleur
	 */
	public MenuAide(Controleur controleur) {
		
		super(Parametres.menuAide);
		
		JMenuItem javadoc = new JMenuItem(Parametres.javadoc);
		javadoc.setActionCommand(Parametres.javadoc);
		javadoc.addActionListener(controleur.getControleMenuAide());
		this.add(javadoc);
		
	
		JMenuItem aPropos = new JMenuItem(Parametres.apropos);
		aPropos.setActionCommand(Parametres.apropos);
		aPropos.addActionListener(controleur.getControleMenuAide());
		this.add(aPropos);	
	}
}
