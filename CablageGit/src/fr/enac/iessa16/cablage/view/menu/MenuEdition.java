package fr.enac.iessa16.cablage.view.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.ParametresFenetre;

/**
 * Classe définissant le menu édition
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class MenuEdition extends JMenu {

	/**
	 * Constructeur de la classe MenuEdition
	 *
	 * @param controleur
	 */
	public MenuEdition(Controleur controleur) {

		// Constructeur parent
		super(ParametresFenetre.menuEdition);

		// Item Ajouter sommet
		JMenuItem ajouterSommet = new JMenuItem(ParametresFenetre.ajouterSommet);
		ajouterSommet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, ActionEvent.CTRL_MASK));
		ajouterSommet.setActionCommand(ParametresFenetre.ajouterSommet);
		ajouterSommet.addActionListener(controleur.getControleurMenuEdition());
		this.add(ajouterSommet);

		// Item Supprimer sommet
		JMenuItem supprimerSommet = new JMenuItem(ParametresFenetre.supprimerSommet);
		supprimerSommet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
		supprimerSommet.setActionCommand(ParametresFenetre.supprimerSommet);
		supprimerSommet.addActionListener(controleur.getControleurMenuEdition());
		this.add(supprimerSommet);
		
		this.addSeparator();

		// Item Ajouter arete
		JMenuItem ajouterArete = new JMenuItem(ParametresFenetre.ajouterArete);
		ajouterArete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, ActionEvent.ALT_MASK));
		ajouterArete.setActionCommand("AjouterArete");
		ajouterArete.addActionListener(controleur.getControleurMenuEdition());
		this.add(ajouterArete);

		// Item Supprimer arete
		JMenuItem supprimerArete = new JMenuItem(ParametresFenetre.supprimerArete);
		supprimerArete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.ALT_MASK));
		supprimerArete.setActionCommand(ParametresFenetre.supprimerArete);
		supprimerArete.addActionListener(controleur.getControleurMenuEdition());
		this.add(supprimerArete);

		this.addSeparator();
		
		// Item Preferences
		JMenuItem preferences = new JMenuItem(ParametresFenetre.preferences);
		preferences.setActionCommand(ParametresFenetre.preferences);
		preferences.addActionListener(controleur.getControleurMenuEdition());
		this.add(preferences);

	}

}
