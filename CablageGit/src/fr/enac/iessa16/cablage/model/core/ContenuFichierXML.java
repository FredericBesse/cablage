package fr.enac.iessa16.cablage.model.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe définissant le format d'un fichier XML
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
@XmlRootElement(name="ContenuFichierXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContenuFichierXML {
	
	private GrapheTheorique graphe;
	
	private Cablage cablage;
	
	/**
	 * Constructeur par défaut de la classe Cablage
	 */
	public ContenuFichierXML() {
		
		this.graphe = new GrapheTheorique();
		this.cablage = new Cablage();
		
	}
	
	/**
	 * Constructeur de la classe ContenuFichierXML
	 * 
	 * @param graphe le graphe
	 * @param cablage le cablage
	 */
	public ContenuFichierXML(GrapheTheorique graphe, Cablage cablage) {
		
		this.graphe = graphe;
		this.cablage = cablage;
	}
	

	/* 
	 * Getters et Setters
	 */
	
	public GrapheTheorique getGraphe() {
		return graphe;
	}

	public void setGraphe(GrapheTheorique graphe) {
		this.graphe = graphe;
	}

	public Cablage getCablage() {
		return cablage;
	}

	public void setCablage(Cablage cablage) {
		this.cablage = cablage;
	}	

}
