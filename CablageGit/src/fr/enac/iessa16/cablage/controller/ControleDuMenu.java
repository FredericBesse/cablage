package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;

/**
 * Classe 
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleDuMenu implements ActionListener { 

		
    DonneesAAfficher model;
    
	/**
	 * Constructeur de la classe ControleDuMenu.java
	 * @param monModel
	 * 
	 * 
	 * constructeur du ControleurDuMenu, il prend en argument le modele
	 */
	public ControleDuMenu(DonneesAAfficher monModel) { 
		this.model = monModel;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//actions que doivent effectuer les items du menu
		// TODO Auto-generated method stub
		//On recupere l'option sur laquelle on a cliqué dans le menu et on la stocke dans une string
		String s = e.getActionCommand();
		//On compare l'option stockée aux differents choix possibles du Menu
		if(s.equals("Quitter"))
		{
			System.out.println("hhfd");
			System.exit(0);
		}
		
		if(s.equals("ChargerGrapheParDefaut"))
			
		{
			//On appelle la methode ChargerLeGraphe implementé dans la classe DonneesAAfficher
			model.ChargerLeGraphe();
			
			
			
			
			System.out.println("d");
			
			
		}
		if(s.equals("ChargerGrapheDonné"))
		{
			//On appelle la methode ChargerGrapheDuFichier Texte implementer dans la classe DonneesAAfficher
			model.ChargerLeGrapheDuFicherTexte();
			System.out.println("charger graphe donné");
		}

	}
	
}
