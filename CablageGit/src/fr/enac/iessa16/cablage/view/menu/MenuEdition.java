package fr.enac.iessa16.cablage.view.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import fr.enac.iessa16.cablage.controller.Controleur;
import fr.enac.iessa16.cablage.view.Parametres;

/**
 * Classe définissant le menu édition
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
@SuppressWarnings("serial")
public class MenuEdition extends JMenu {

	private Controleur controleur;
	
	private JMenuItem ajouterSommet;
	private JMenuItem supprimerSommet;
	private JMenuItem ajouterArete;
	private JMenuItem supprimerArete;

	/**
	 * Constructeur de la classe MenuEdition
	 *
	 * @param controleur le controleur
	 */
	public MenuEdition(Controleur controleur) {

		// Constructeur parent
		super(Parametres.menuEdition);

		// on récupère le controleur
		this.controleur = controleur;

		// on crée les items
		this.creerItems();
	}

	private void creerItems() {
		
		// Item Ajouter sommet
		ajouterSommet = new JMenuItem(Parametres.ajouterSommet);
		ajouterSommet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, ActionEvent.CTRL_MASK));
		ajouterSommet.setActionCommand(Parametres.ajouterSommet);
		ajouterSommet.addActionListener(controleur.getControleurMenuEdition());
		ajouterSommet.setEnabled(false);
		this.add(ajouterSommet);

		// Item Supprimer sommet
		supprimerSommet = new JMenuItem(Parametres.supprimerSommet);
		supprimerSommet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
		supprimerSommet.setActionCommand(Parametres.supprimerSommet);
		supprimerSommet.addActionListener(controleur.getControleurMenuEdition());
		supprimerSommet.setEnabled(false);
		this.add(supprimerSommet);

		this.addSeparator();

		// Item Ajouter arete
		ajouterArete = new JMenuItem(Parametres.ajouterArete);
		ajouterArete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, ActionEvent.ALT_MASK));
		ajouterArete.setActionCommand("AjouterArete");
		ajouterArete.addActionListener(controleur.getControleurMenuEdition());
		ajouterArete.setEnabled(false);
		this.add(ajouterArete);

		// Item Supprimer arete
		supprimerArete = new JMenuItem(Parametres.supprimerArete);
		supprimerArete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.ALT_MASK));
		supprimerArete.setActionCommand(Parametres.supprimerArete);
		supprimerArete.addActionListener(controleur.getControleurMenuEdition());
		supprimerArete.setEnabled(false);
		this.add(supprimerArete);

		this.addSeparator();

		// Item Preferences
		JMenuItem preferences = new JMenuItem(Parametres.preferences);
		preferences.setActionCommand(Parametres.preferences);
		preferences.addActionListener(controleur.getControleurMenuEdition());
		this.add(preferences);

	}

	public void updateNouveauGraphe() {
		ajouterSommet.setEnabled(true);
		supprimerSommet.setEnabled(true);
		ajouterArete.setEnabled(true);
		supprimerArete.setEnabled(true);
	}

	public void updateFermerGraphe() {
		ajouterSommet.setEnabled(false);
		supprimerSommet.setEnabled(false);
		ajouterArete.setEnabled(false);
		supprimerArete.setEnabled(false);
		
	}

}
