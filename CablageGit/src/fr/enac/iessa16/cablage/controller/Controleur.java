package fr.enac.iessa16.cablage.controller;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.Sommet;

/**
 * Classe Controleur , qui prend en attribut le Controle du menu et le controleur du clique sur le Sommet
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Controleur {

	
	private ControleDuMenu controleMenu;
	private ControleurCliqueSommet controleurClique;
	private Sommet sommet;
	
	
	/**
	 * Constructeur de la classe Controleur.java
	 * @param monModel
	 */
	public Controleur(DonneesAAfficher monModel) {
		
		// TODO Auto-generated constructor stub
		this.controleMenu = new ControleDuMenu(monModel);
		this.controleurClique = new ControleurCliqueSommet( monModel, sommet );
	
	}



	/**
	 * Getters et Setters
	 */
	public ControleurCliqueSommet getControleurClique() {
		return controleurClique;
	}



	public void setControleurClique(ControleurCliqueSommet controleurClique) {
		this.controleurClique = controleurClique;
	}



	public ControleDuMenu getControleMenu() {
		return controleMenu;
	}



	public void setControleMenu(ControleDuMenu controleMenu) {
		this.controleMenu = controleMenu;
	}



	



	
	



	
	
	
	
	
	
	
	
	
	

}