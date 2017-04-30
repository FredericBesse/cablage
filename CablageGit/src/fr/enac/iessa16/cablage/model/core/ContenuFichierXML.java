package fr.enac.iessa16.cablage.model.core;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
	
	@XmlElementWrapper(name = "cablages")
	@XmlElement(name = "cablage")
	private ArrayList<Cablage> cablages;
	
	public ContenuFichierXML() {
		
		
	}
	
	public ContenuFichierXML(GrapheTheorique graphe) {
		
		this.graphe = graphe;
		this.setCablages(new ArrayList<Cablage>());
		
	}
	
	public ContenuFichierXML(GrapheTheorique graphe, ArrayList<Cablage> cablages) {
		
		this.graphe = graphe;
		this.setCablages(cablages);
		
	}
	

	public GrapheTheorique getGraphe() {
		return graphe;
	}

	public void setGraphe(GrapheTheorique graphe) {
		this.graphe = graphe;
	}

	public ArrayList<Cablage> getCablages() {
		return cablages;
	}

	public void setCablages(ArrayList<Cablage> cablages) {
		this.cablages = cablages;
	}

	

}
