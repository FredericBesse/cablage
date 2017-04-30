package fr.enac.iessa16.cablage.model.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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

	private ContenuFichierXML contenuFichierXML;
	
	


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
	
	
	public void enregistrerFichierXML(File fichier, ContenuFichierXML structureFichier) {
		
		
		if (jaxbContext != null) {
		
				
			try {
				Marshaller marshaller = jaxbContext.createMarshaller();
			
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						
				marshaller.marshal(structureFichier, fichier);
			
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			modele.erreur("XML", "Pas de contexte JAXB...");
		}
	}
	
	public File enregistrerSousFichierXML(ContenuFichierXML structureFichier) {
		
		int returnVal;
		File fichier = null;
		String s = null;

		// Création du JFileChooser
		JFileChooser fc = new JFileChooser("file/");
		returnVal = fc.showSaveDialog(null);
		fc.setVisible(true);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			fichier = fc.getSelectedFile();
			enregistrerFichierXML(fichier, structureFichier);
		
		}
		
		return fichier;
	}


	public File ouvrirFichierXML() {
		
		//modele.message("ouverture du fichier", "test");
		
		int returnVal;
		File fichier = null;
		String s = null;

		// Création du JFileChooser
		JFileChooser fc = new JFileChooser("file/");
		returnVal = fc.showOpenDialog(null);
		fc.setVisible(true);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			fichier = fc.getSelectedFile();
			//modele.message("ouverture du fichier", fichier.getAbsolutePath());

			if (jaxbContext != null) {
				
				
				try {
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
							
					this.contenuFichierXML = (ContenuFichierXML) unmarshaller.unmarshal(fichier);
					//modele.message("ouverture du fichier", "unmarshal done "+contenuFichierXML.getGraphe());
				
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				modele.erreur("XML", "Pas de contexte JAXB...");
			}
			
			
		
		}
		
		return fichier;
	}

	
	public ContenuFichierXML getContenuFichierXML() {
		return contenuFichierXML;
	}

}
