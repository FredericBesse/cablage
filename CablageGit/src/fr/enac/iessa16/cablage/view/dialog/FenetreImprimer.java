package fr.enac.iessa16.cablage.view.dialog;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import fr.enac.iessa16.cablage.view.PanneauDessinGraphe;

/**
 * Classe impression
 *
 * @author Racha HEDIDI et Frédéric BESSE
 */
public class FenetreImprimer {

	public FenetreImprimer(PanneauDessinGraphe dessin) {

		// Récupère un PrinterJob
		PrinterJob job = PrinterJob.getPrinterJob();
		
		// Définit son contenu à imprimer
		job.setPrintable(dessin);
		
		// Affiche une boîte de choix d'imprimante
		if (job.printDialog()) {
			
			try {				
				// Effectue l'impression
				job.print();
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}
		}
	}
}
