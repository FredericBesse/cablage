package fr.enac.iessa16.cablage.controller;

import fr.enac.iessa16.cablage.model.DonneesAAfficher;
import fr.enac.iessa16.cablage.model.core.Sommet;

/**
 * Classe Controleur , qui prend en attribut le Controle du menu,
 * le controleur du clique sur le Sommet,
 * ainsi que le controle du Menu permettant de determiner le chemin le plus court (Djikstra et Kruskal)
 *
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Controleur {

	
	/**
	 Attrin
	 */
	/**
	 * 
	 */
	private ControleDuMenu controleMenu;
	private ControleurCliqueSommet controleurClique;
	private ControleMenuCalcul controleCalcul;
	
	
	/**
	 * Constructeur de la classe Controleur.java
	 * @param monModel
	 */
	public Controleur(DonneesAAfficher monModel) {
		
		// TODO Auto-generated constructor stub
		this.controleMenu = new ControleDuMenu(monModel);
		this.controleurClique = new ControleurCliqueSommet( monModel);
		this.controleCalcul = new ControleMenuCalcul(monModel);
		//this.
	}



	/**
	 * @param 
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



	public ControleMenuCalcul getControleCalcul() {
		return controleCalcul;
	}



	public void setControleCalcul(ControleMenuCalcul controleCalcul) {
		this.controleCalcul = controleCalcul;
	}



	



	
	



	
	
	
	
	
	
	
	
	
	

}