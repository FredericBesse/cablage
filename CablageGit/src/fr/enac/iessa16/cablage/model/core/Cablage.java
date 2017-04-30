package fr.enac.iessa16.cablage.model.core;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe Cablage
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Cablage {

	@XmlElementWrapper(name = "sommetsSelectionnes")
	@XmlElement(name = "sommet")
	private ArrayList<Sommet> sommetsSelectionnes;

	@XmlElementWrapper(name = "sommetsUtiles")
	@XmlElement(name = "sommet")
	private ArrayList<Sommet> sommetsUtiles;

	@XmlElementWrapper(name = "chemin")
	@XmlElement(name = "arete")
	private ArrayList<Arete> aretes;

	@XmlAttribute
	private double cout;

	@XmlTransient
	private HashMap<Sommet, ArrayList<Arete>> aretesParSommet;

	/**
	 * Constructeur par defaut de la classe Cablage
	 */
	public Cablage() {
		this.sommetsSelectionnes = new ArrayList<Sommet>();
		this.sommetsUtiles = new ArrayList<Sommet>();
		this.aretes = new ArrayList<Arete>();
		this.cout = 0;
		this.aretesParSommet = new HashMap<Sommet, ArrayList<Arete>>();
	}

	/**
	 * Constructeur de la classe Cablage
	 * 
	 * @param sommetsSelectionnes
	 * @param sommetsUtiles
	 * @param listeAretes
	 */
	public Cablage(ArrayList<Sommet> sommetsSelectionnes, ArrayList<Sommet> sommetsUtiles,
			ArrayList<Arete> listeAretes) {

		this.sommetsSelectionnes = sommetsSelectionnes;
		this.sommetsUtiles = sommetsUtiles;
		this.aretes = listeAretes;

		this.calculerCout();

		this.calculerMapAretesParSommet();

	}
	

	/**
	 * Méthode permettant de calculer le cout du cablage 
	 */
	public void calculerCout() {
		this.cout = 0;
		for (Arete arete : this.aretes) {
			this.cout += arete.getCout();
		}
	}

	/**
	 * Méthode permettant de calculer une map qui contient pour chaque sommet la
	 * liste des aretes qui passe par ce sommet
	 */
	private void calculerMapAretesParSommet() {

		Sommet origine, extremite;
		ArrayList<Arete> listeAretes;

		this.aretesParSommet = new HashMap<Sommet, ArrayList<Arete>>();

		for (Arete arete : this.aretes) {

			origine = arete.getSommetOrigine();
			extremite = arete.getSommetExtremité();

			if (!this.aretesParSommet.containsKey(origine)) {
				listeAretes = new ArrayList<Arete>();
				listeAretes.add(arete);
				aretesParSommet.put(origine, listeAretes);
			} else {
				aretesParSommet.get(origine).add(arete);
			}

			if (!this.aretesParSommet.containsKey(extremite)) {
				listeAretes = new ArrayList<Arete>();
				listeAretes.add(arete);
				aretesParSommet.put(extremite, listeAretes);
			} else {
				aretesParSommet.get(extremite).add(arete);
			}
		}

	}

	/*
	 * public Cablage(Cablage chemin) { this(chemin.getSommetsSelectionnes(),
	 * chemin.getSommetsUtiles(), chemin.getChemin()); }
	 */

	/*
	 * Getters
	 */

	public HashMap<Sommet, ArrayList<Arete>> getAretesParSommet() {
		return aretesParSommet;
	}

	public ArrayList<Arete> getAretes() {
		return aretes;
	}

	public double getCout() {
		return cout;
	}

	public ArrayList<Sommet> getSommetsUtiles() {
		return sommetsUtiles;
	}

	public ArrayList<Sommet> getSommetsSelectionnes() {
		return sommetsSelectionnes;
	}
}
