package fr.enac.iessa16.cablage.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import fr.enac.iessa16.cablage.model.Modele;
import fr.enac.iessa16.cablage.view.Parametres;
import fr.enac.iessa16.cablage.view.dialog.FenetreAPropos;

/**
 * Classe définissant le controleur du menu Aide
 *	
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class ControleurMenuAide implements ActionListener {
	
	// Le modele
	private Modele modele;

	/**
	 * Constructeur de la classe ControleurMenuAide
	 *
	 * @param monModel le modèle de l'application
	 */
	public ControleurMenuAide(Modele monModele) {
		
		super();
		
		this.modele = monModele;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String s = e.getActionCommand();
		
		if(s.equals(Parametres.javadoc)) {
			if(Desktop.isDesktopSupported()) {  
				if(Desktop.getDesktop().isSupported(java.awt.Desktop.Action.BROWSE)) {  
					try { 
						// FIXME copier les fichiers javadoc chez l'utilisateur pour que ca marche en local
						Desktop.getDesktop().browse(new URI("file:///home/eleve/IESSA/bessefc/git/cablageGit/CablageGit/doc/index.html"));
					} catch (Exception ex) {  
						modele.erreur("Javadoc", "Impossible de trouver la Javadoc...");
					} 
				} else {  
					modele.erreur("Javadoc", "La fonction n'est pas supportée par votre système d'exploitation...");  
				}  
			} else {  
				modele.erreur("Javadoc", "Desktop pas supporté par votre système d'exploitation...");  
			}		
		}
		
		if(s.equals(Parametres.apropos)) {
			new FenetreAPropos();
		}		
	}
}
