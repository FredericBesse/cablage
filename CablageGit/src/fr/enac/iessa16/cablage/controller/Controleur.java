package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionListener;

import fr.enac.iessa16.cablage.model.Modele;
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
	private ControleurAide controleAide;
	private ControleCreationGraphe controleCreationGraphe;
	

	/**
	 * Constructeur de la classe Controleur.java
	 * @param monModel
	 */
	public Controleur(Modele monModel) {
		
		// TODO Auto-generated constructor stub
		this.controleMenu = new ControleDuMenu(monModel);
		this.controleurClique = new ControleurCliqueSommet( monModel);
		this.controleCalcul = new ControleMenuCalcul(monModel);
		this.controleAide = new ControleurAide(monModel);
		//this.
		this.controleCreationGraphe = new ControleCreationGraphe(monModel);
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



	public ActionListener getControleAide() {
		// TODO Auto-generated method stub
		return controleAide;
	}



	
	public ControleCreationGraphe getControleCreationGraphe() {
		return controleCreationGraphe;
	}


	
	



	
	
	
	
	
	
	
	
	
	

}