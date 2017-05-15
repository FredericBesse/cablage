package fr.enac.iessa16.cablage.model.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.model.core.ContenuFichierXML;

/**
 * Classe permettant de lire et écrire un fichier XML
 * 
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ConstructeurGrapheFichierXML {

	// Le logger
	private Logger LOGGER = LogManager.getLogger(ConstructeurGrapheFichierXML.class);

	private Modele modele;
	
	private JAXBContext jaxbContext;

	private ContenuFichierXML contenuFichierXML;
	
	


	/**
	 * Constructeur
	 * 
	 * @param modele le modele
	 */
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
		
		this.contenuFichierXML = null;
	}
	
	
	/**
	 * Méthode permettant de créer un fichier XML
	 * 
	 * @param fichier le fichier a créer
	 * @param structureFichier le contenu
	 */
	public void enregistrerFichierXML(File fichier, ContenuFichierXML structureFichier) {
		
		
		if (jaxbContext != null) {
				
			try {
				Marshaller marshaller = jaxbContext.createMarshaller();
			
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						
				marshaller.marshal(structureFichier, fichier);
			
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} else {
			modele.erreur("XML", "Pas de contexte JAXB...");
			LOGGER.error("Erreur JAXB context");
		}
	}
	
	/**
	 * Méthode permettant de créer un nouveau fichier XML
	 * 
	 * @param structureFichier le contenu
	 * @return le fichier 
	 */
	public File enregistrerSousFichierXML(ContenuFichierXML structureFichier) {
		
		int returnVal;
		File fichier = null;
		
		// Création du JFileChooser
		JFileChooser fc = new JFileChooser("file/");
		
		// On définit les extensions que l'on accepte
		FileFilter xmlFilter = new FileNameExtensionFilter("Fichiers XML","xml");
		fc.addChoosableFileFilter(xmlFilter);
				
				
		returnVal = fc.showSaveDialog(null);
		fc.setVisible(true);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			fichier = fc.getSelectedFile();
			enregistrerFichierXML(fichier, structureFichier);
		
		}
		
		return fichier;
	}


	/**
	 * Méthode permettant d'ouvrir un fichier XML
	 * 
	 * @return le fichier ouvert
	 */
	public File ouvrirFichierXML() {
				
		int returnVal;
		File fichier = null;

		// Création du JFileChooser
		JFileChooser fc = new JFileChooser("file/");
		
		// On définit les extensions que l'on accepte
		FileFilter xmlFilter = new FileNameExtensionFilter("Fichiers XML","xml");
		fc.addChoosableFileFilter(xmlFilter);
		fc.setFileFilter(xmlFilter);
				
		returnVal = fc.showOpenDialog(null);
		fc.setVisible(true);
		

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			fichier = fc.getSelectedFile();

			if (jaxbContext != null) {
				
				
				try {
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
							
					this.contenuFichierXML = (ContenuFichierXML) unmarshaller.unmarshal(fichier);
				
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			} else {
				modele.erreur("XML", "Pas de contexte JAXB...");
			}
		}
		
		return fichier;
	}

	// Getter
	public ContenuFichierXML getContenuFichierXML() {
		return contenuFichierXML;
	}

}
