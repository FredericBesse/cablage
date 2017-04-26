package fr.enac.iessa16.cablage.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import fr.enac.iessa16.cablage.controller.Controleur;

public class MenuEdition extends JMenu{
	
	private JMenuBar jbar;
	private Controleur controleur;
	
	public MenuEdition(Controleur controleur) {
		
		super("\u00C9dition");
		
		this.controleur = controleur;
		
	//	JMenu optionNouveau = new JMenu("Nouveau...");
	//	optionNouveau.setMnemonic(KeyEvent.VK_N);
	//	this.add(optionNouveau);
		//optionNouveau.setAccelerator(KeyStroke.getKeyStroke("control N"));
	//	optionNouveau.setActionCommand("Nouveau");
	//	optionNouveau.addActionListener(controleur.getControleMenu());
		
		
		
		
		
		JMenuItem AjouterSommet = new JMenuItem("Ajouter un sommet");
		//AjouterSommet.setMnemonic(KeyEvent.VK_);
		AjouterSommet.setAccelerator(KeyStroke.getKeyStroke("control +"));
		AjouterSommet.setActionCommand("AjoutSommet");
	    AjouterSommet.addActionListener(controleur.getControleMenu());
		this.add(AjouterSommet);
		
		JMenuItem SupprimerSommet = new JMenuItem("Supprimer un sommet");
		//AjouterSommet.setMnemonic(KeyEvent.VK_);
		SupprimerSommet.setAccelerator(KeyStroke.getKeyStroke("control -"));
		SupprimerSommet.setActionCommand("SuppSommet");
		SupprimerSommet.addActionListener(controleur.getControleMenu());
		this.add(SupprimerSommet);
		this.addSeparator();
		
		
		
		JMenuItem AjouterArete = new JMenuItem("Ajouter une arete");
		AjouterArete.setMnemonic(KeyEvent.VK_A);
		AjouterArete.setAccelerator(KeyStroke.getKeyStroke("control A"));
		AjouterArete.setActionCommand("AjouterArete");
		AjouterArete.addActionListener(controleur.getControleMenu());
		this.add(AjouterArete);
		
		JMenuItem SupprimerArete = new JMenuItem("Supprimer une arete");
		SupprimerArete.setMnemonic(KeyEvent.VK_S);
		SupprimerArete.setAccelerator(KeyStroke.getKeyStroke("control S"));
		SupprimerArete.setActionCommand("SuppArete");
		SupprimerArete.addActionListener(controleur.getControleMenu());
		this.add(SupprimerArete);
		
		
		JMenuItem Preferences = new JMenuItem("Preferences");
		Preferences.setMnemonic(KeyEvent.VK_P);
		SupprimerArete.setAccelerator(KeyStroke.getKeyStroke("control P"));
		SupprimerArete.setActionCommand("SuppSommet");
		SupprimerArete.addActionListener(controleur.getControleMenu());
		this.add(SupprimerArete);
		
		
		
	


	
		

			
			
			
	
			
			
		}	
	
	
	
	
	
	

}
