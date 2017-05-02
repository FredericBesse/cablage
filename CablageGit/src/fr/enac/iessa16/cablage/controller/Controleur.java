package fr.enac.iessa16.cablage.controller;

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
	private ControleurMenuEdition controleurMenuEdition;
	private ControleurMenuAffichage controleurMenuAffichage;
	private ControleurMenuCalcul controleurMenuCalcul;
	private ControleurMenuAide controleurMenuAide;
	
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
		this.controleurMenuEdition = new ControleurMenuEdition(monModel);	
		this.controleurMenuAffichage = new ControleurMenuAffichage(monModel);
		this.controleurMenuCalcul = new ControleurMenuCalcul(monModel);
		this.controleurMenuAide = new ControleurMenuAide(monModel);
			
		this.controleurSouris = new ControleurSouris(monModel);

	}
	
	
	/*
	 * Getters
	 */
	public ControleurMenuFichier getControleurMenuFichier() {
		return controleurMenuFichier;
	}
	
	public ControleurMenuEdition getControleurMenuEdition() {
		return controleurMenuEdition;
	}
	
	public  ControleurMenuAffichage getControleurMenuAffichage() {
		return controleurMenuAffichage;
	}	

	public ControleurMenuCalcul getControleurMenuCalcul() {
		return controleurMenuCalcul;
	}

	public ControleurMenuAide getControleMenuAide() {
		return controleurMenuAide;
	}
	
	public ControleurSouris getControleurSouris() {
		return controleurSouris;
	}
}