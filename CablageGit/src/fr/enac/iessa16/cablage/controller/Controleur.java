package fr.enac.iessa16.cablage.controller;

import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.model.Modele;

/**
 * Classe Controleur, qui regroupe les différents sous-controleurs :
 * le controle de chaque menu et le controleur pour la souris
 *
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class Controleur {
	
	// Le logger
	private Logger LOGGER = LogManager.getLogger(Controleur.class);
		
	// Les controleurs des menus (et de la toolbar)
	private ControleurMenuFichier controleurMenuFichier;
	private ControleurMenuCalcul controleurMenuCalcul;
	private ControleurMenuAide controleurMenuAide;
	private ControleurMenuEdition controleurMenuEdition;
	
	// Le controleur de la souris
	private ControleurSouris controleurSouris;


	/**
	 * Constructeur de la classe Controleur
	 * 
	 * @param monModel le modele de l'application
	 */
	public Controleur(Modele monModel) {
		
		LOGGER.debug("Création du controleur");
		
		// Création des sous-controleurs
		this.controleurMenuFichier = new ControleurMenuFichier(monModel);
		this.controleurMenuCalcul = new ControleurMenuCalcul(monModel);
		this.controleurMenuAide = new ControleurMenuAide(monModel);
		this.controleurMenuEdition = new ControleurMenuEdition(monModel);		
		this.controleurSouris = new ControleurSouris(monModel);
	
	}
	
	
	/*
	 * Getters
	 */
	public ControleurMenuFichier getControleurMenuFichier() {
		return controleurMenuFichier;
	}

	public ControleurMenuCalcul getControleurMenuCalcul() {
		return controleurMenuCalcul;
	}

	public ControleurMenuAide getControleMenuAide() {
		return controleurMenuAide;
	}

	public ControleurMenuEdition getControleurMenuEdition() {
		return controleurMenuEdition;
	}
	
	public ControleurSouris getControleurSouris() {
		return controleurSouris;
	}
}