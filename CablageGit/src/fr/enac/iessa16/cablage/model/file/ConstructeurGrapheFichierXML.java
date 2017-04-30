package fr.enac.iessa16.cablage.model.file;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.ApplicationCablage;
import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.ContenuFichierXML;
import fr.enac.iessa16.cablage.model.core.GrapheTheorique;

/**
 * Classe permettant de lire et écrire un fichier XML
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ConstructeurGrapheFichierXML {

	// Le logger
	private Logger LOGGER = LogManager.getLogger(ConstructeurGrapheFichierXML.class);

	private Modele modele;
	
	private GrapheTheorique graphe;

	private JAXBContext jaxbContext;
	
	public ConstructeurGrapheFichierXML(Modele modele) {
		
		this.modele = modele;
		
		// Création du JAXBContext
		this.jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ContenuFichierXML.class);
		} catch (JAXBException e) {
			e.printStackTrace();
			modele.erreur("XML", "Erreur dans la création du contexte JAXB...");
		}
		
	}
	
	
	public void enregistrerFichierXML(String nomFichier, ContenuFichierXML structureFichier) {
		
		if (jaxbContext != null) {
			try {
				Marshaller marshaller = jaxbContext.createMarshaller();
			
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			
				marshaller.marshal(structureFichier, System.out);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
